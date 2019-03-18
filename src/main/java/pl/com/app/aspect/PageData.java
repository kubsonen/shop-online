package pl.com.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import pl.com.app.entity.Authority;
import pl.com.app.entity.User;

/**
 * @author JNartowicz
 */
@Aspect
@Component
public class PageData {

    private static final Logger logger = LoggerFactory.getLogger(PageData.class);
    private static final String LOGGED_IN_ADMIN = "adminLogged";
    private static final String ADMIN_AUTHORITY = "ADMIN";

    @Before("execution(* pl.com.app.controller.*.*(..))")
    public void before(JoinPoint joinPoint){

        try {
            //Get application user
            User user =
                    (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Object[] controllerArgs = joinPoint.getArgs();
            for (Object arg : controllerArgs) {
                if (arg instanceof Model) {

                    for(Authority authority: user.getAuthoritiesSet()){
                        //Add parameters log in admin
//                        logger.info("Authority: " + authority.getAuthority());
                        if(authority.getAuthority().equals(ADMIN_AUTHORITY)){
                            Model model = (Model) arg;
                            model.addAttribute(LOGGED_IN_ADMIN, true);
//                            logger.info("Param added.");
                        }
                    }
//                    logger.info("Model exists.");
                }
            }
        } catch (ClassCastException e){
            logger.info("Not found user in context.");
        } catch (Throwable t){
            logger.info("Problem with adding params to model.");
            t.printStackTrace();
        }
    }

}
