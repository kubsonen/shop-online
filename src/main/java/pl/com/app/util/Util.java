package pl.com.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author JNartowicz
 */
public class Util {

    private static final String PATTERN = "yyMMddHHmmssSSS";
    private static final String PRODUCT_PREFIX = "P";
    private static final String IMG_PREFIX = "IMG";

    public static String generateProductCode(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN);
        StringBuilder stringBuilder = new StringBuilder();
        for(char c: simpleDateFormat.format(new Date()).toCharArray()){
            stringBuilder.append(MapLetter.getLetter(Integer.valueOf(c + "")));
        }
        return PRODUCT_PREFIX + stringBuilder.toString();
    }

    public static String generateImgName(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN);
        return IMG_PREFIX + simpleDateFormat.format(new Date());
    }

    private enum MapLetter{
        A(1),
        B(2),
        C(3),
        D(4),
        E(5),
        F(6),
        G(7),
        H(8),
        I(9);

        private int number;

        MapLetter(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public static String getLetter(int num){
            for(MapLetter letter: MapLetter.values()){
                if(letter.number == num){
                    if(bigLetter()){
                        return letter.name().toUpperCase();
                    } else {
                        return letter.name().toLowerCase();
                    }
                }
            }
            return "Z";
        }

    }

    private static boolean bigLetter(){
        return Math.abs(new Random().nextLong()) % 2 == 0 ;
    }

}
