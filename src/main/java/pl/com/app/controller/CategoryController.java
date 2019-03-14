package pl.com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = CategoryController.CATEGORY_PATH)
public class CategoryController {

    public static final String CATEGORY_PATH = "/category";
    public static final String CATEGORY_ADD = "/add";
    public static final String PARENT_ID_ATTRIBUTE = "parentId";
    public static final String CATEGORY_ACRONYM_ATTRIBUTE = "acronym";

    @GetMapping
    public String showCategory(Model model, @RequestParam(value = CATEGORY_ACRONYM_ATTRIBUTE, required = false) String acronym){

        if(acronym == null || acronym.trim().isEmpty()){
            model.addAttribute(PARENT_ID_ATTRIBUTE, "");
        } else {
            model.addAttribute(PARENT_ID_ATTRIBUTE, "1");
        }

        return "category";
    }

}
