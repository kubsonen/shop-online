package pl.com.app.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import pl.com.app.annotation.InsertConstant;
import pl.com.app.service.CategoryService;

import java.lang.reflect.Method;

/**
 * @author JNartowicz
 */
@Aspect
@Controller
public class ConstantData {

    private static final Logger logger = LoggerFactory.getLogger(ConstantData.class);
    public static final String CATEGORIES_DATA = "availableCategories";

    @Autowired
    private CategoryService categoryService;

    @Around("@annotation(pl.com.app.annotation.InsertConstant)")
    public Object logAOP(ProceedingJoinPoint point) throws Throwable {

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        InsertConstant importData = method.getAnnotation(InsertConstant.class);

        Object[] controllerArgs = point.getArgs();
        for (Object arg : controllerArgs) {
            if (arg instanceof Model) {

                Model model = (Model) arg;
                for (String data: importData.value()){

                    switch (data){
                        case CATEGORIES_DATA:
                            logger.info("Categories data added.");
                            model.addAttribute(CATEGORIES_DATA, categoryService.getAllCategories());
                            break;
                        default:
                            break;
                    }

                }
            }
        }

        return point.proceed();
    }
}
