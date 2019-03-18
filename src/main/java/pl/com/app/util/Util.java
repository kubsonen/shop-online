package pl.com.app.util;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author JNartowicz
 */
public class Util {

    private static final String PATTERN = "yyMMddHHmmssSSS";
    private static final String PRODUCT_PREFIX = "P";
    private static final String IMG_PREFIX = "IMG";

    public static String generateProductCode(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN);
        return Base64.encode((PRODUCT_PREFIX + simpleDateFormat.format(new Date())).getBytes());
    }

    public static String generateImgName(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN);
        return IMG_PREFIX + simpleDateFormat.format(new Date());
    }

}
