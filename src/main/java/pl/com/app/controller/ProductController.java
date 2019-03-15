package pl.com.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import pl.com.app.annotation.InsertConstant;
import pl.com.app.aspect.ConstantData;
import pl.com.app.entity.Product;

import java.util.List;

@Controller
@RequestMapping(value = ProductController.PRODUCT)
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    public static final String PRODUCT = "product";
    public static final String PRODUCT_FORM = "/form";
    public static final String PRODUCT_ATTRIBUTE = "product";


    @GetMapping("/{productCode}")
    public String showProduct(Model model,
                              @PathVariable("productCode") String productCode){
        return "product";
    }

    @GetMapping(PRODUCT_FORM)
    @InsertConstant(ConstantData.CATEGORIES_DATA)
    public String showProductForm(Model model){
        model.addAttribute(PRODUCT_ATTRIBUTE, new Product());
        return "product-form";
    }

    @PostMapping(PRODUCT_FORM)
    @InsertConstant(ConstantData.CATEGORIES_DATA)
    public String saveProduct(Model model, Product product){

        logger.info(product.toString());

        List<MultipartFile> files = product.getTempFiles();

        if(files != null){
            logger.info("Files not null.");

            for (MultipartFile multipartFile: files){
                logger.info("Name: " + multipartFile.getOriginalFilename());
            }

        } else {
            logger.info("Files null");
        }

        return "product-form";
    }


}
