package pl.com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author JNartowicz
 */
@Controller
@RequestMapping(value = AdminController.ADMIN_PATH)
public class AdminController {

    public static final String ADMIN_PATH = "/admin";

    @GetMapping
    private String showAdmin(){
        return "admin";
    }

}
