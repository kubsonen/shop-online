package pl.com.app.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class ShopBasket {

    private static final Logger logger = LoggerFactory.getLogger(ShopBasket.class);

}
