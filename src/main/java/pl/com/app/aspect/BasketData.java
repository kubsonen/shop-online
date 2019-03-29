package pl.com.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import pl.com.app.component.ShopBasket;

@Aspect
@Component
public class BasketData {

    private static final Logger logger = LoggerFactory.getLogger(BasketData.class);
    private static final String BASKET_SHOP_ATTRIBUTE = "basketShopAttribute";

    @Autowired
    private ShopBasket shopBasket;

    @Before("execution(* pl.com.app.controller.*.*(..))")
    public void before(JoinPoint joinPoint){

//        logger.info("Getting shop basket.");

        try {
            //Get application user
            Object[] controllerArgs = joinPoint.getArgs();
            for (Object arg : controllerArgs) {
                if (arg instanceof Model) {

                    shopBasket.getOrderSum();
                    Model model = (Model) arg;
                    model.addAttribute(BASKET_SHOP_ATTRIBUTE, shopBasket);

                }
            }
        } catch (ClassCastException e){
//            logger.info("Not found user in context.");
        } catch (Throwable t){
            logger.info("Problem with adding params to model.");
            t.printStackTrace();
        }
    }

}
