package pl.com.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class CookieData {

    private static final Logger logger = LoggerFactory.getLogger(CookieData.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Before("execution(* pl.com.app.controller.*.*(..))")
    public void before(JoinPoint joinPoint){

        logger.info("Request autowired: " + request.getSession().getId());
        logger.info("Response autowired: " + response.getContentType());

    }

}
