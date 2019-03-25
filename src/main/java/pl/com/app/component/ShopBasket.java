package pl.com.app.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import pl.com.app.entity.Order;
import pl.com.app.entity.Product;
import pl.com.app.service.ProductService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShopBasket {

    private static final Logger logger = LoggerFactory.getLogger(ShopBasket.class);

    @PostConstruct
    public void initBasket(){
        logger.info("Basket create.");
    }

    @Autowired
    private ProductService productService;

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

    public Set<Product> getProductInBasket(){

        Map<Long, Product> idProductMap = new HashMap<>();

        if(!(getOrder() == null || getOrder().getProducts() == null)){
            Set<Product> products = getOrder().getProducts();
            for(Product product: products){
                if (idProductMap.containsKey(product.getId())){
                    idProductMap.get(product.getId()).addCountInBasket();
                } else {
                    idProductMap.put(product.getId(), product);
                    product.setCountInBasket(1);
                }
            }
        }
        return new HashSet<>(idProductMap.values());
    }

    public void addProduct(String productCode){
        if(getOrder() != null){
            getOrder().addProduct(productService.findByProductCode(productCode));
        }
    }

    public void deleteProduct(String productCode, boolean onePiece){
        List<Product> productsToRemove = new ArrayList<>();
        if(getOrder() != null && productCode != null && !productCode.isEmpty()){
            Set<Product> products = getOrder().getProducts();
            if(products != null && !products.isEmpty()){
                for(Product product: products){
                   if(product.getProductCode().equals(productCode)){
                        productsToRemove.add(product);
                        if(onePiece) break;
                   }
                }
                products.removeAll(productsToRemove);
            }
        }
    }

}
