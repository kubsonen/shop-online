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
import pl.com.app.service.CategoryService;
import pl.com.app.service.ProductService;

import javax.validation.Valid;
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
    public static final String SHOW_PRODUCTS_IN_CATEGORY = "products";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String showCategory(Model model, @RequestParam(value = CATEGORY_ACRONYM_ATTRIBUTE, required = false) String acronym){

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
        return "category-import";
    }

    @PostMapping(CATEGORY_IMPORT)
    public String saveImport(ImportModel importModel, Model model){

        try{
            categoryService.importCategories(importModel.getImportText());
            model.addAttribute(IMPORT_SUCCESS, "Everything ok.");
        } catch (Throwable t) {
            t.printStackTrace();
            model.addAttribute(IMPORT_ERROR, t.getMessage());
        }

        return "category-import";
    }

    @GetMapping("/{categoryAcronym}")
    public String showProductsInCategory(Model model,
                                         @PathVariable("categoryAcronym") String categoryAcronym){

        Category category = null;
        try{
            category = categoryService.getCategoryByAcronym(categoryAcronym);
        } catch (Throwable t){
            t.printStackTrace();
        }

        if(category != null){
            Set<Product> products = productService.getProductsInCategory(category);
            model.addAttribute(SHOW_PRODUCTS_IN_CATEGORY, products);
        }

        return "product-list";
    }

}
