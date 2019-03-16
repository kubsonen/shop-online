package pl.com.app.service;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.com.app.entity.Category;
import pl.com.app.entity.Image;
import pl.com.app.entity.Product;
import pl.com.app.repository.ProductRepository;
import pl.com.app.util.AppConst;
import pl.com.app.util.Import;
import pl.com.app.util.Util;
import sun.nio.ch.IOUtil;

import javax.transaction.Transactional;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

/**
 * @author JNartowicz
 */
@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private static final char PRODUCT_IMPORT_ROW_SEPARATOR = '\n';
    private static final char PRODUCT_IMPORT_COLUMN_SEPARATOR = '\t';
    private static final char PRODUCT_IMPORT_IMAGE_LINK_SEPARATOR = ',';

    private static final int PRODUCT_IMPORT_INDEX_COUNT = 5;
    private static final int PRODUCT_IMPORT_INDEX_CATEGORY_ID = 0;
    private static final int PRODUCT_IMPORT_INDEX_NAME = 1;
    private static final int PRODUCT_IMPORT_INDEX_PRICE = 2;
    private static final int PRODUCT_IMPORT_INDEX_DESCRIPTION = 3;
    private static final int PRODUCT_IMPORT_INDEX_IMG_LINKS = 4;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Transactional
    public Product findByProductCode(String productCode){
        return productRepository.findByProductCode(productCode);
    }

    @Transactional
    public Set<Product> getProductsInCategory(Category category){

        Set<Category> categories = new HashSet<>();
        categoryService.getSubCategories(category, categories);
        Set<Product> products = productRepository.findByCategoryIn(categories);

        for(Product product: products){
            Set<Image> images = product.getImages();
            images.size();
            if(!images.isEmpty()){
                product.setProductThumbNailId(images.iterator().next().getId().toString());
            }
        }

        return products;
    }

    @Transactional
    public void saveProduct(Product product) throws IOException {

        if(product.getId() == null || product.getId() == 0){
            product.setCreateDate(new Date());
            product.setProductCode(Util.generateProductCode());
        }

        Set<Image> savedImages = new HashSet<>();

        //Check files to save
        List<MultipartFile> filesToSave = product.getTempFiles();
        if(!(filesToSave == null && filesToSave.isEmpty())){
            for(MultipartFile multipartFile: filesToSave){

                //Create file in path
                File file = new File(AppConst.PRODUCT_IMG_FILES_PATH + Util.generateImgName());

                Image image = new Image();
                image.setName(file.getName());
                image.setServerPath(AppConst.PRODUCT_IMG_FILES_PATH);
                image.setDescription("Product file.");
                //Save image
                imageService.saveImage(image);
                //Save file on disk
                multipartFile.transferTo(file);
                //Add image to the list
                savedImages.add(image);

            }
        }

        productRepository.save(product);
        product.setImages(savedImages);

    }

    @Transactional
    public void deleteProductById(Long id){
        productRepository.delete(productRepository.findById(id).get());
    }

    @Transactional
    public void importProducts(String importText) throws Throwable{
        List<String[]> importRows = Import.getRowsDataFromText(PRODUCT_IMPORT_INDEX_COUNT,
                PRODUCT_IMPORT_COLUMN_SEPARATOR, PRODUCT_IMPORT_ROW_SEPARATOR, importText);

        List<Product> productsToSave = new ArrayList<>();
        List<Image> imagesToSave = new ArrayList<>();
        Map<Long, Category> categories = new HashMap<>();

        for(String[] row: importRows){

            Product product = new Product();

            String categoryId = row[PRODUCT_IMPORT_INDEX_CATEGORY_ID].trim();
            if(!categoryId.isEmpty()){

                Long id = Long.valueOf(categoryId);

                if(categories.containsKey(id)){
                    product.setCategory(categories.get(id));
                } else {
                    Category category = categoryService.getCategoryById(id);
                    categories.put(id, category);
                    product.setCategory(category);
                }

            } else {
                throw new RuntimeException("Category id is empty.");
            }

            String productName = row[PRODUCT_IMPORT_INDEX_NAME].trim();
            if(!productName.isEmpty()){
                product.setProductName(productName);
            } else {
                throw new RuntimeException("Product name is empty.");
            }

            String productPrice = row[PRODUCT_IMPORT_INDEX_PRICE].trim();
            if(!productPrice.isEmpty()){
                product.setPrice(new BigDecimal(productPrice));
            } else {
                throw new RuntimeException("Product price is empty.");
            }

            String productDescription = row[PRODUCT_IMPORT_INDEX_DESCRIPTION].trim();
            if(!productDescription.isEmpty()){
                product.setDescription(productDescription);
            }

            String productLinks = row[PRODUCT_IMPORT_INDEX_IMG_LINKS].trim();
            if(!productLinks.isEmpty()){
                String[] links = productLinks.split("" + PRODUCT_IMPORT_IMAGE_LINK_SEPARATOR);

                for(String link: links){

                    InputStream inputStream = null;
                    OutputStream outputStream = null;
                    File file;
                    try{

                        URL url = new URL(link);
                        inputStream = url.openStream();
                        file = new File(AppConst.PRODUCT_IMG_FILES_PATH + Util.generateImgName());
                        file.createNewFile();
                        outputStream = new FileOutputStream(file);
                        IOUtils.copy(inputStream, outputStream);

                        Image image = new Image();
                        image.setName(file.getName());
                        image.setServerPath(AppConst.PRODUCT_IMG_FILES_PATH);

                        //Add images to save list
                        imagesToSave.add(image);
                        //Add image to product
                        product.addImage(image);

                    } catch (Throwable t){
                        logger.info("Fail read img.");
                    } finally {
                        try{
                            if(inputStream != null){
                                inputStream.close();
                            }
                        } catch (Throwable t){
                            t.printStackTrace();
                        }

                        try{
                            if(outputStream != null){
                                outputStream.close();
                            }
                        } catch (Throwable t){
                            t.printStackTrace();
                        }
                    }
                }
            }

            //Add product to save list
            productsToSave.add(product);

        }


        //Save all images
        for(Image image: imagesToSave){
            imageService.saveImage(image);
        }

        //Save all products
        for(Product product: productsToSave){
            productRepository.save(product);
        }

    }

}
