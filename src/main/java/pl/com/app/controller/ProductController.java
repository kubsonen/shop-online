package pl.com.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.com.app.App;
import pl.com.app.annotation.InsertConstant;
import pl.com.app.aspect.ConstantData;
import pl.com.app.component.ShopBasket;
import pl.com.app.entity.Product;
import pl.com.app.model.ImportModel;
import pl.com.app.model.LastProductCookie;
import pl.com.app.model.ShopCookie;
import pl.com.app.service.ProductService;
import pl.com.app.util.AppConst;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
    public static final String PRODUCT_FORM = "/form";
    public static final String PRODUCT_IMPORT = "/import";
    public static final String PRODUCT_ATTRIBUTE = "product";
    public static final String PRODUCT_SAVE_SUCCESS = "productSaveSuccess";
    public static final String PRODUCT_SAVE_FAIL = "productSaveFail";
    public static final String PRODUCT_SELECTED_PHOTO = "showPhoto";
    public static final String PRODUCT_ADD_TO_BASKET = "/addToBasket";
    public static final String PRODUCT_SEARCH = "/searchProduct";
    public static final String PRODUCT_SEARCH_CAPTION = "searchCaption";

    public static final String PRODUCT_ADD_ONE_PIECE_FROM_BASKET = "/addOnePieceFromBasket";
    public static final String PRODUCT_DELETE_ONE_PIECE_FROM_BASKET = "/deleteOnePieceFromBasket";
    public static final String PRODUCT_DELETE_PRODUCT_FROM_BASKET = "/deleteProductFromBasket";

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
    private ShopBasket basket;

    @Autowired
    private ProductService productService;

    @GetMapping("/{productCode}")
    public String showProduct(Model model,
                              HttpServletResponse response,
                              @PathVariable("productCode") String productCode,
                              @CookieValue(value = AppConst.COOKIE_SHOP_ONLINE_LAST_PRODUCTS, required = false) String lastProductsCookie,
                              @RequestParam(value = "showImg", required = false) String photoId){

        try{
            productService.addProductView(productCode);
        } catch (Throwable t){
            t.printStackTrace();
            logger.info("Cannot add view time to product.");
        }

        if(photoId != null){
            model.addAttribute(PRODUCT_SELECTED_PHOTO, photoId);
        }

        Product product = productService.findByProductCode(productCode);
        model.addAttribute(PRODUCT, product);
        LastProductCookie.handleLastProductCookie(response, lastProductsCookie,
                AppConst.COOKIE_SHOP_ONLINE_LAST_PRODUCTS, AppConst.COOKIE_MAX_AGE_LAST_PRODUCTS, AppConst.MAX_LAST_VIEW_PRODUCTS, product);

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

    @GetMapping(PRODUCT_ADD_TO_BASKET + "/{productCode}")
    public String addProductToBasket(Model model,
                                    @PathVariable(value = "productCode") String productCode,
                                    @RequestHeader(value = "referer", required = false) String referer){

        Product productToAdd = productService.findByProductCode(productCode);
        basket.getOrder().addProduct(productToAdd);

        if(referer != null){
            return "redirect:" + referer;
        } else {
            return "redirect:/category";
        }
    }

    @PostMapping(PRODUCT_SEARCH)
    public String productSearch(Model model, @ModelAttribute("search-field") String filterSearch){
        model.addAttribute(PRODUCT_SEARCH_CAPTION, filterSearch);
        model.addAttribute(CategoryController.SHOW_PRODUCTS_IN_PAGE, productService.searchProductByName(filterSearch));
        return "product-list";
    }

    @GetMapping(PRODUCT_DELETE_ONE_PIECE_FROM_BASKET + "/{productCode}")
    public String deleteOnePieceFromBasket(Model model,
                                           @PathVariable(value = "productCode") String productCode,
                                           @RequestHeader(value = "referer", required = false) String referer){

        basket.deleteProduct(productCode, true);
        if(referer != null){
            return "redirect:" + referer;
        } else {
            return "redirect:/category";
        }

    }

    @GetMapping(PRODUCT_ADD_ONE_PIECE_FROM_BASKET + "/{productCode}")
    public String addOnePieceFromBasket(Model model,
                                        @PathVariable(value = "productCode") String productCode,
                                        @RequestHeader(value = "referer", required = false) String referer){

        basket.addProduct(productCode);
        if(referer != null){
            return "redirect:" + referer;
        } else {
            return "redirect:/category";
        }

    }

    @GetMapping(PRODUCT_DELETE_PRODUCT_FROM_BASKET + "/{productCode}")
    public String deletePRoductFromBasket(Model model,
                                          @PathVariable(value = "productCode") String productCode,
                                          @RequestHeader(value = "referer", required = false) String referer){

        basket.deleteProduct(productCode, false);
        if(referer != null){
            return "redirect:" + referer;
        } else {
            return "redirect:/category";
        }

    }

}
