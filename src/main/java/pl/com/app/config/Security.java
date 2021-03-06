package pl.com.app.config;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.com.app.controller.*;
import pl.com.app.service.UserService;

/**
 * @author JNartowicz
 */
@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(encoder());
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                authorizeRequests().
                antMatchers(IndexController.INDEX_PATH).permitAll().
                antMatchers(CategoryController.CATEGORY_PATH, CategoryController.CATEGORY_PATH + "/*").permitAll().
                antMatchers(ImageController.IMAGE_PATH + ImageController.IMAGE_PRODUCT_PATH + "/*").permitAll().
                antMatchers(ImageController.IMAGE_PATH + ImageController.IMAGE_PRODUCT_THUMB_PATH + "/*").permitAll().
                antMatchers(ShopBasketController.BASKET_PATH + "").permitAll().
                antMatchers(ProductController.PRODUCT_PATH + "/*").permitAll().
                antMatchers(ProductController.PRODUCT_PATH + ProductController.PRODUCT_ADD_TO_BASKET + "/*").permitAll().
                antMatchers(ProductController.PRODUCT_PATH + ProductController.PRODUCT_ADD_ONE_PIECE_FROM_BASKET + "/*").permitAll().
                antMatchers(ProductController.PRODUCT_PATH + ProductController.PRODUCT_DELETE_ONE_PIECE_FROM_BASKET + "/*").permitAll().
                antMatchers(ProductController.PRODUCT_PATH + ProductController.PRODUCT_DELETE_PRODUCT_FROM_BASKET + "/*").permitAll().
                antMatchers("/css/**", "/js/**", "/img/**", "/favicon.ico").permitAll().
                anyRequest().authenticated().and().formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

}
