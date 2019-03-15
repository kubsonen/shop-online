package pl.com.app.service;

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
import pl.com.app.util.Util;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author JNartowicz
 */
@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

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

}
