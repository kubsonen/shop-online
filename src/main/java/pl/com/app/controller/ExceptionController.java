package pl.com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ExceptionController {

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {

        ex.printStackTrace();

        ModelAndView mav = new ModelAndView();
        return mav;
    }

}
