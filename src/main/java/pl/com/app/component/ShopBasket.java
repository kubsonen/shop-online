package pl.com.app.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import pl.com.app.entity.Order;
import pl.com.app.entity.Product;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Set;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShopBasket {

    private static final Logger logger = LoggerFactory.getLogger(ShopBasket.class);

    @PostConstruct
    public void initBasket(){
        logger.info("Basket create.");
    }

    private Order order = new Order();

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getOrderSum(){
        Set<Product> products = order.getProducts();

        if(products != null){
            double sum = 0.0;
            for(Product product: products){
                sum = sum + product.getPrice().doubleValue();
            }
            return new BigDecimal(sum);
        } else {
            return new BigDecimal("0.0");
        }
    }

}
