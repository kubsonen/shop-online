package pl.com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(name = CategoryController.CATEGORY_PATH)
public class CategoryController {

    public static final String CATEGORY_PATH = "/category";

    @GetMapping
    public String showCategory(){
        return "category";
    }

}
