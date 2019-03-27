package pl.com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.app.entity.Product;
import pl.com.app.service.ProductService;

import java.util.Set;

@Controller
@RequestMapping(value = IndexController.INDEX_PATH)
public class IndexController {

    public static final String INDEX_PATH = "/";

    public static final String THE_MOST_VIEWED_PRODUCTS = "theMostViewedProducts";

    @Autowired
    private ProductService productService;

    @GetMapping
    public String showIndex(Model model){

        //Show the most viewed product
        Set<Product> products = productService.findTop3MostViewedProducts();
        model.addAttribute(THE_MOST_VIEWED_PRODUCTS, products);

        return "index";
    }

}
