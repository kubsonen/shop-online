package pl.com.app.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.Cookie;
import java.util.Base64;

public abstract class ShopCookie {

    protected static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public static ShopCookie getShopCookieFromBase64(String base64Cookie, Class<? extends ShopCookie> cookieClass){
        byte[] bytes = base64Cookie.getBytes();
        byte[] decodeBase = Base64.getDecoder().decode(bytes);
        return GSON.fromJson(new String(decodeBase), cookieClass);
    }

    public static String convertShopCookieToBase64(ShopCookie cookie){
        String cookieJson = GSON.toJson(cookie);
        byte[] base64 = Base64.getEncoder().encode(cookieJson.getBytes());
        return new String(base64);
    }

    public static Cookie createCookie(String cookieName, Integer cookieAge, ShopCookie instance){
        Cookie c = new Cookie(cookieName, ShopCookie.convertShopCookieToBase64(instance));
        c.setMaxAge(cookieAge);
        c.setPath("/");
        return c;
    }

}
