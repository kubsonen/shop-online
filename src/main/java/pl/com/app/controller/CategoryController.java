package pl.com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.app.entity.Category;

import javax.validation.Valid;

@Controller
@RequestMapping(value = CategoryController.CATEGORY_PATH)
public class CategoryController {

    public static final String CATEGORY_PATH = "/category";
    public static final String CATEGORY_FORM = "/form";
    public static final String PARENT_ID_ATTRIBUTE = "parentId";
    public static final String CATEGORY_ACRONYM_ATTRIBUTE = "acronym";

    @GetMapping
    public String showCategory(Model model, @RequestParam(value = CATEGORY_ACRONYM_ATTRIBUTE, required = false) String acronym){
        return "category";
    }

    @GetMapping(CATEGORY_FORM)
    public String formCategory(Model model){
        model.addAttribute("category", new Category());
        return "category-form";
    }

    @PostMapping(CATEGORY_FORM)
    public String saveCategory(@Valid Category category, BindingResult bindingResult, Model model){

//        if(bindingResult.hasErrors()){
//
//        }
        model.addAttribute("category", category);
        return "category-form";
    }

}
