package pl.com.app.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JNartowicz
 */
public class Import {

    public static List<String[]> getRowsDataFromText(int columnCount, char columnSeparator, char rowSeparator, String text) throws Exception {

        List<String[]> strings = new ArrayList<>();
        String[] rows = text.split("" + rowSeparator);

        for(String row: rows){

            String[] columns = row.split("" + columnSeparator);
            if(columns.length != columnCount){
                throw new Exception("Incorrect column count.");
            } else {
                strings.add(columns);
            }

        }

        return strings;
    }

}
