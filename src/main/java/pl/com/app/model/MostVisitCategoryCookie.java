package pl.com.app.model;

import com.google.gson.annotations.Expose;
import pl.com.app.entity.Category;
import pl.com.app.entity.Product;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author JNartowicz
 */
public class MostVisitCategoryCookie extends ShopCookie {

    @Expose
    private Integer maxCountCategories;

    @Expose
    private List<Category> mostVisitCategories;

    public MostVisitCategoryCookie(Integer maxCountCategories) {
        this.maxCountCategories = maxCountCategories;
    }

    public void addCategoryVisit(Category category){

        if(category != null){

            String categoryAcronym = category.getAcronym();

            if(mostVisitCategories == null){
                mostVisitCategories = new ArrayList<>();
            }

            for(Category c: mostVisitCategories){
                String cAcronym = c.getAcronym();
                if(cAcronym.equals(categoryAcronym)){
                    int count = c.getCountOfVisit() + 1;
                    c.setCountOfVisit(count);
                    return;
                }
            }

            category.setCountOfVisit(1);
            mostVisitCategories.add(category);

        }

    }

    public List<Category> getMostVisitCategoriesSorted() {
        Collections.sort(mostVisitCategories, (o1, o2) -> o1.getCountOfVisit() > o2.getCountOfVisit() ? -1 : (o1.getCountOfVisit() < o2.getCountOfVisit()) ? 1 : 0);
        return mostVisitCategories.size() > maxCountCategories ? mostVisitCategories.subList(0, maxCountCategories) : mostVisitCategories;
    }

    public static void handleMostVisitCategoryCookie(HttpServletResponse response, String cookie, String cookieName,
                                                     Integer cookieAge, Integer objectCount, Category lastCategory) {

        MostVisitCategoryCookie mostVisitCategoryCookie;
        if(lastCategory != null){

            if(cookie != null){

                try{
                    mostVisitCategoryCookie = (MostVisitCategoryCookie) getShopCookieFromBase64(cookie, MostVisitCategoryCookie.class);
                    mostVisitCategoryCookie.setMaxCountCategories(objectCount);
                } catch (Throwable t){
                    t.printStackTrace();
                    mostVisitCategoryCookie = new MostVisitCategoryCookie(objectCount);
                }

            } else {
                mostVisitCategoryCookie = new MostVisitCategoryCookie(objectCount);
            }

            if(mostVisitCategoryCookie != null){
                mostVisitCategoryCookie.addCategoryVisit(lastCategory);
                response.addCookie(ShopCookie.createCookie(cookieName, cookieAge, mostVisitCategoryCookie));
            }

        }

    }

    public void setMaxCountCategories(Integer maxCountCategories) {
        this.maxCountCategories = maxCountCategories;
    }
}
