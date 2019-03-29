package pl.com.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import pl.com.app.model.LastProductCookie;
import pl.com.app.model.MostVisitCategoryCookie;
import pl.com.app.model.ShopCookie;
import pl.com.app.util.AppConst;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class CookieData {

    private static final Logger logger = LoggerFactory.getLogger(CookieData.class);
    private static final String FAIL_ON_RECOGNIZED_COOKIE = "Fail on recognized cookie.";

    private static final String LAST_PRODUCTS_VIEW_ATTRIBUTE = "lastProductsViewed";
    private static final String MOST_VIEW_CATEGORIES_ATTRIBUTE = "mostViewCategories";

    @Autowired
    private HttpServletRequest request;

    @Before("execution(* pl.com.app.controller.*.*(..))")
    public void before(JoinPoint joinPoint){

        LastProductCookie lastProductCookie = null;
        MostVisitCategoryCookie mostVisitCategoryCookie = null;

        Model aspectModel = null;

        //First check the model exists
        Object[] controllerArgs = joinPoint.getArgs();
        for (Object arg : controllerArgs) {
            if (arg instanceof Model) {
                aspectModel = (Model) arg;
            }
        }

        if(aspectModel == null) return;

        //Get the data from cookie
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals(AppConst.COOKIE_SHOP_ONLINE_LAST_PRODUCTS)){
                    try{
                        lastProductCookie = (LastProductCookie) ShopCookie.getShopCookieFromBase64(cookie.getValue(), LastProductCookie.class);
                    } catch (Throwable t){
                        logger.info(FAIL_ON_RECOGNIZED_COOKIE);
                    }
                } else if (cookie.getName().equals(AppConst.COOKIE_SHOP_ONLINE_THE_MOST_VISITED_CATEGORIES)){
                    try{
                        mostVisitCategoryCookie = (MostVisitCategoryCookie) ShopCookie.getShopCookieFromBase64(cookie.getValue(), MostVisitCategoryCookie.class);
                    } catch (Throwable t){
                        logger.info(FAIL_ON_RECOGNIZED_COOKIE);
                    }
                }
            }

            //Set last product cookie to the model
            if(lastProductCookie != null){
                aspectModel.addAttribute(LAST_PRODUCTS_VIEW_ATTRIBUTE, lastProductCookie.getLastProducts());
            }

            //Set most viewed cookie to the model
            if(mostVisitCategoryCookie != null){
                aspectModel.addAttribute(MOST_VIEW_CATEGORIES_ATTRIBUTE, mostVisitCategoryCookie.getMostVisitCategoriesSorted());
            }

        }
    }

}
