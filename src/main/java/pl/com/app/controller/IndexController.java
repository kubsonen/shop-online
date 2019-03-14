package pl.com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = IndexController.INDEX_PATH)
public class IndexController {

    public static final String INDEX_PATH = "/";

    @GetMapping
    public String showIndex(Model model){
        return "index";
    }

}
