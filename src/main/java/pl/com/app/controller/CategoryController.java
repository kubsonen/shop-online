package pl.com.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.com.app.entity.Category;
import pl.com.app.entity.Product;
import pl.com.app.model.ImportModel;
import pl.com.app.model.MostVisitCategoryCookie;
import pl.com.app.service.CategoryService;
import pl.com.app.service.ProductService;
import pl.com.app.util.AppConst;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = CategoryController.CATEGORY_PATH)
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    public static final String CATEGORY_PATH = "/category";
    public static final String CATEGORY_FORM = "/form";
    public static final String CATEGORY_IMPORT = "/import";
    public static final String CATEGORY_ACRONYM_ATTRIBUTE = "acronym";
    public static final String IMPORT_ERROR = "importError";
    public static final String IMPORT_SUCCESS = "importSuccess";
    public static final String SHOW_CATEGORY_CATEGORIES = "categories";
    public static final String SHOW_CATEGORY_CATEGORY_PARENT = "categoryParent";
    public static final String SHOW_PRODUCTS_IN_PAGE = "productsInPage";
    public static final String IMPORT_ACTION = "importAction";
    public static final String IMPORT_WHAT = "whatImport";

    private static final Map<String, Object> importParameterMap = new HashMap<>();
    static {
        importParameterMap.put(IMPORT_ACTION, CATEGORY_PATH + CATEGORY_IMPORT);
        importParameterMap.put(IMPORT_WHAT, "categories");
    }

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String showCategory(Model model,
                               @RequestParam(value = CATEGORY_ACRONYM_ATTRIBUTE, required = false) String acronym){

        Category parent = null;
        if(acronym != null){
            parent = categoryService.getCategoryByAcronym(acronym);
        }

        Set<Category> categories = categoryService.getCategoriesByParent(parent);
        model.addAttribute(SHOW_CATEGORY_CATEGORIES, categories);
        model.addAttribute(SHOW_CATEGORY_CATEGORY_PARENT, parent);

        return "category";
    }

    @GetMapping(CATEGORY_FORM)
    public String formCategory(Model model){
        model.addAttribute("category", new Category());
        return "category-form";
    }

    @PostMapping(CATEGORY_FORM)
    public String saveCategory(@Valid Category category, BindingResult bindingResult, Model model){
        model.addAttribute("category", category);
        return "category-form";
    }

    @GetMapping(CATEGORY_IMPORT)
    public String importCategories(Model model){
        model.addAllAttributes(importParameterMap);
        return "data-import";
    }

    @PostMapping(CATEGORY_IMPORT)
    public String saveImport(ImportModel importModel, Model model){
        model.addAllAttributes(importParameterMap);
        try{
            categoryService.importCategories(importModel.getImportText());
            model.addAttribute(IMPORT_SUCCESS, "Everything ok.");
        } catch (Throwable t) {
            t.printStackTrace();
            model.addAttribute(IMPORT_ERROR, t.getMessage());
        }

        return "data-import";
    }

    @GetMapping("/{categoryAcronym}")
    public String showProductsInCategory(Model model,
                                         HttpServletResponse response,
                                         @PathVariable("categoryAcronym") String categoryAcronym,
                                         @CookieValue(value = AppConst.COOKIE_SHOP_ONLINE_THE_MOST_VISITED_CATEGORIES, required = false) String mostVisitCategoryCookie){

        Category category = null;
        try{
            category = categoryService.getCategoryByAcronym(categoryAcronym);
        } catch (Throwable t){
            t.printStackTrace();
        }

        if(category != null){
            Set<Product> products = productService.getProductsInCategory(category);
            model.addAttribute(SHOW_PRODUCTS_IN_PAGE, products);
        }

        MostVisitCategoryCookie.handleMostVisitCategoryCookie(response, mostVisitCategoryCookie, AppConst.COOKIE_SHOP_ONLINE_THE_MOST_VISITED_CATEGORIES,
                AppConst.COOKIE_MAX_AGE_THE_MOST_VISITED_CATEGORIES, AppConst.MAX_MOST_VISITED_CATEGORIES, category);

        return "product-list";
    }

}
