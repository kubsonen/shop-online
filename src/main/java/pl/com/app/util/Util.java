package pl.com.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author JNartowicz
 */
public class Util {

    public static String generateProductCode(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmssZ");
        return simpleDateFormat.format(new Date());
    }

}
