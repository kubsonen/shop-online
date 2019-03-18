package pl.com.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.com.app.annotation.InsertConstant;
import pl.com.app.aspect.ConstantData;
import pl.com.app.entity.Product;
import pl.com.app.model.ImportModel;
import pl.com.app.service.ProductService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = ProductController.PRODUCT)
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    public static final String PRODUCT = "product";
    public static final String PRODUCT_PATH = "/product";
    public static final String DELETE_PATH = "/delete";
    private static final String PRODUCT_FORM = "/form";
    private static final String PRODUCT_IMPORT = "/import";
    private static final String PRODUCT_ATTRIBUTE = "product";
    private static final String PRODUCT_SAVE_SUCCESS = "productSaveSuccess";
    private static final String PRODUCT_SAVE_FAIL = "productSaveFail";
    private static final String PRODUCT_SELECTED_PHOTO = "showPhoto";

    private static final String IMPORT_ERROR = "importError";
    private static final String IMPORT_SUCCESS = "importSuccess";

    private static final String IMPORT_ACTION = "importAction";
    private static final String IMPORT_WHAT = "whatImport";
    private static final Map<String, Object> importParameterMap = new HashMap<>();
    static {
        importParameterMap.put(IMPORT_ACTION, PRODUCT_PATH + PRODUCT_IMPORT);
        importParameterMap.put(IMPORT_WHAT, "products");
    }

    @Autowired
    private ProductService productService;

    @GetMapping("/{productCode}")
    public String showProduct(Model model,
                              @PathVariable("productCode") String productCode,
                              @RequestParam(value = "showImg", required = false) String photoId){

        if(photoId != null){
            model.addAttribute(PRODUCT_SELECTED_PHOTO, photoId);
        }

        Product product = productService.findByProductCode(productCode);
        model.addAttribute(PRODUCT, product);
        return "product-show";
    }

    @GetMapping(PRODUCT_FORM)
    @InsertConstant(ConstantData.CATEGORIES_DATA)
    public String showProductForm(Model model){
        model.addAttribute(PRODUCT_ATTRIBUTE, new Product());
        return "product-form";
    }

    @PostMapping(PRODUCT_FORM)
    @InsertConstant(ConstantData.CATEGORIES_DATA)
    public String saveProduct(Model model, @Valid Product product, BindingResult bindingResult){

        try {
            if(!bindingResult.hasErrors()){
                productService.saveProduct(product);
                model.addAttribute(PRODUCT, new Product());
                model.addAttribute(PRODUCT_SAVE_SUCCESS, "Add success.");
            }
        }catch (Throwable t){
            t.printStackTrace();
            model.addAttribute(PRODUCT_SAVE_FAIL, t.getMessage());
        }

        return "product-form";
    }

    @GetMapping(DELETE_PATH + "/{productId}")
    public String deleteProduct(Model model,
                                @PathVariable("productId") String productId,
                                @RequestHeader(value = "referer", required = false) String referer){

        productService.deleteProductById(Long.valueOf(productId));

        if(referer != null){
            return "redirect:" + referer;
        }

        return "redirect:/category";

    }

    @GetMapping(PRODUCT_IMPORT)
    public String importProducts(Model model){
        model.addAllAttributes(importParameterMap);
        return "data-import";
    }

    @PostMapping(PRODUCT_IMPORT)
    public String postImportProducts(Model model, ImportModel importModel){
        model.addAllAttributes(importParameterMap);
        try{
            productService.importProducts(importModel.getImportText());
            model.addAttribute(IMPORT_SUCCESS, "Products added correctly.");
        } catch (Throwable t){
            t.printStackTrace();
            model.addAttribute(IMPORT_ERROR, t.getMessage());
        }
        return "data-import";
    }

}
