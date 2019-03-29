package pl.com.app.model;

import com.google.gson.annotations.Expose;
import pl.com.app.entity.Product;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author JNartowicz
 */
public class LastProductCookie extends ShopCookie {

    @Expose
    private int maxProducts;

    @Expose
    private List<Product> lastProducts;

    public LastProductCookie(Integer maxProducts) {
        this.maxProducts = maxProducts;
    }

    public void addLastProduct(Product product){

        String productCode = product.getProductCode();
        if(productCode != null && !productCode.isEmpty()){

            if(lastProducts == null){
                lastProducts = new ArrayList<>();
            }

            for(Product p: lastProducts){
                String pCode = p.getProductCode();
                if(pCode != null && pCode.equals(productCode)){
                    Collections.swap(lastProducts, lastProducts.indexOf(p), 0 );
                    return;
                }
            }

            if(maxProducts != 0){
                //Remove old products
                for(;lastProducts.size() >= maxProducts;){
                    lastProducts.remove(lastProducts.size() - 1);
                }
                //Add last product to the first index
                lastProducts.add(0, product);
            }

        }
    }

    public Collection<Product> getLastProducts() {
        return lastProducts;
    }

    public static void handleLastProductCookie(HttpServletResponse response, String cookie, String cookieName, Integer cookieAge, Integer objectCount, Product lastProduct) {

        LastProductCookie lastProductCookie;
        if(lastProduct != null){

            if(cookie != null){

                try{
                    lastProductCookie = (LastProductCookie) getShopCookieFromBase64(cookie, LastProductCookie.class);
                    lastProductCookie.setMaxProducts(objectCount);
                } catch (Throwable t){
                    t.printStackTrace();
                    lastProductCookie = new LastProductCookie(objectCount);
                }

            } else {
                lastProductCookie = new LastProductCookie(objectCount);
            }

            if(lastProductCookie != null){
                lastProductCookie.addLastProduct(lastProduct);
                response.addCookie(ShopCookie.createCookie(cookieName, cookieAge, lastProductCookie));
            }

        }

    }

    public void setMaxProducts(int maxProducts) {
        this.maxProducts = maxProducts;
    }
}
