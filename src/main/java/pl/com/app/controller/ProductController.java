package pl.com.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.com.app.annotation.InsertConstant;
import pl.com.app.aspect.ConstantData;
import pl.com.app.entity.Product;
import pl.com.app.service.ProductService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = ProductController.PRODUCT)
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    public static final String PRODUCT = "product";
    public static final String DELETE_PATH = "/delete";
    private static final String PRODUCT_FORM = "/form";
    private static final String PRODUCT_ATTRIBUTE = "product";
    private static final String PRODUCT_SAVE_SUCCESS = "productSaveSuccess";
    private static final String PRODUCT_SAVE_FAIL = "productSaveFail";
    private static final String PRODUCT_DELETE_SUCCESS = "productDeleteSuccess";
    private static final String PRODUCT_DELETE_FAIL = "productDeleteSuccess";

    @Autowired
    private ProductService productService;

    @GetMapping("/{productCode}")
    public String showProduct(Model model,
                              @PathVariable("productCode") String productCode){

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


}
