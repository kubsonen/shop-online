package pl.com.app.util;

public class AppConst {

    public static final String PRODUCT_IMG_FILES_PATH = "C:\\Users\\Jakub\\Desktop\\SHOP_APP\\";
//    public static final String PRODUCT_IMG_FILES_PATH = "C:\\Users\\jnartowicz\\Desktop\\SHOP\\";

    //Cookies
    public static final String COOKIE_SHOP_ONLINE_LAST_PRODUCTS = "_shop_online_last_products";
    public static final String COOKIE_SHOP_ONLINE_THE_MOST_VISITED_CATEGORIES = "_shop_online_most_visited_categories";

    public static final Integer MAX_LAST_VIEW_PRODUCTS = 4;
    public static final Integer MAX_MOST_VISITED_CATEGORIES = 6;

    public static final Integer COOKIE_MAX_AGE_LAST_PRODUCTS = 60 * 60 * 24 * 2; //Two days
    public static final Integer COOKIE_MAX_AGE_THE_MOST_VISITED_CATEGORIES = 60 * 60 * 24 * 180; //Half a year

}
