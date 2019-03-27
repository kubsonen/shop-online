package pl.com.app.model;

import com.google.gson.Gson;
import pl.com.app.entity.Category;
import pl.com.app.entity.Product;

import java.util.*;

public class ShopCookie {

    private static final Gson GSON = new Gson();
    private static final Integer DEFAULT_MAX_LAST_VIEW_PRODUCTS = 4;
    private Integer maxLastViewProducts;

    public ShopCookie(Integer maxLastViewProducts) {
        this.maxLastViewProducts = maxLastViewProducts;
    }

    private LastViewCategory lastViewCategory;
    private List<LastViewProduct> lastViewProducts;

    public void addLastViewCategory(Category category){
        if(category != null){
            LastViewCategory lastViewCategory = new LastViewCategory();
            lastViewCategory.setCategoryName(category.getName());
            lastViewCategory.setCategoryAcronym(category.getAcronym());
            this.lastViewCategory = lastViewCategory;
        }
    }

    public void addLastViewProduct(Product product){
        if(product != null){

            String productCode = product.getProductCode();
            if(lastViewProducts == null){
                lastViewProducts = new ArrayList<>();
            }

            LastViewProduct lastViewProduct = null;

            for(LastViewProduct lvp: lastViewProducts){
                if(lvp.productCode.equals(productCode)){
                    lastViewProduct = lvp;
                }
            }

            if(lastViewProduct != null){
                Collections.swap(lastViewProducts,lastViewProducts.indexOf(lastViewProduct), 0 );
            } else {

                //Remove old products
                for(;lastViewProducts.size() >= getMaxLastViewProducts();){
                    lastViewProducts.remove(lastViewProducts.size() - 1);
                }

                //Add last product to the first index
                lastViewProducts.add(0, new LastViewProduct(productCode, product.getProductName()));

            }

        }
    }

    private Integer getMaxLastViewProducts() {
        if(maxLastViewProducts == null || maxLastViewProducts == 0){
            return DEFAULT_MAX_LAST_VIEW_PRODUCTS;
        }
        return maxLastViewProducts;
    }

    public class LastViewProduct{

        private String productCode;
        private String productName;

        public LastViewProduct(String productCode, String productName) {
            this.productCode = productCode;
            this.productName = productName;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
    }

    public class LastViewCategory{

        private String categoryAcronym;
        private String categoryName;

        public String getCategoryAcronym() {
            return categoryAcronym;
        }

        public void setCategoryAcronym(String categoryAcronym) {
            this.categoryAcronym = categoryAcronym;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }
    }

    public static ShopCookie getShopCookieFromBase64(String base64Cookie){
        return GSON.fromJson(Base64.getDecoder().decode(base64Cookie.getBytes()).toString(), ShopCookie.class);
    }

    public static String convertShopCookieToBase64(ShopCookie cookie){
        return Base64.getEncoder().encode(GSON.toJson(cookie).getBytes()).toString();
    }

}
