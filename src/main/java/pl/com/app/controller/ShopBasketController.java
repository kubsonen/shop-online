package pl.com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.com.app.component.ShopBasket;

@Controller
@RequestMapping(value = ShopBasketController.BASKET_PATH)
public class ShopBasketController {

    public static final String BASKET_PATH = "/basket";
    public static final String PRODUCTS_IN_BASKET = "productsInBasket";

    @Autowired
    private ShopBasket shopBasket;

    @GetMapping
    public String showBasket(Model model){
        model.addAttribute(PRODUCTS_IN_BASKET, shopBasket.getProductInBasket());
        return "basket";
    }

}
