package pl.com.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.app.entity.Category;
import pl.com.app.repository.CategoryRepository;
import pl.com.app.util.Import;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author JNartowicz
 */
@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    //Const
    private static final char CATEGORY_IMPORT_ROW_SEPARATOR = '\n';
    private static final char CATEGORY_IMPORT_COLUMN_SEPARATOR = '\t';
    private static final int CATEGORY_IMPORT_INDEX_COUNT = 4;
    private static final int CATEGORY_IMPORT_INDEX_PARENT_ID = 0;
    private static final int CATEGORY_IMPORT_INDEX_ACRONYM = 1;
    private static final int CATEGORY_IMPORT_INDEX_NAME = 2;
    private static final int CATEGORY_IMPORT_INDEX_DESCRIPTION = 3;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Category getCategoryById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }

    @Transactional
    public Set<Category> getCategoriesByParent(Category category){
        return categoryRepository.findByParent(category);
    }

    @Transactional
    public Set<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @Transactional
    public Category getCategoryByAcronym(String acronym){
        return categoryRepository.findByAcronym(acronym);
    }

    @Transactional
    public void importCategories(String importText) throws Throwable{

        List<String[]> data
                = Import.getRowsDataFromText(
                        CATEGORY_IMPORT_INDEX_COUNT,
                        CATEGORY_IMPORT_COLUMN_SEPARATOR,
                        CATEGORY_IMPORT_ROW_SEPARATOR, importText);

        for(String[] row: data){

            Category category = new Category();
            category.setCreateDate(new Date());

            String parentId = row[CATEGORY_IMPORT_INDEX_PARENT_ID].trim();
            if(!parentId.isEmpty()){
                Long id = Long.valueOf(parentId);
                Category categoryParent = categoryRepository.findById(id).get();
                category.setParent(categoryParent);
            }

            String acronym = row[CATEGORY_IMPORT_INDEX_ACRONYM].trim();
            if(!acronym.isEmpty()){
                category.setAcronym(acronym);
            } else {
                throw new Exception("Acronym must be not empty.");
            }

            String name = row[CATEGORY_IMPORT_INDEX_NAME].trim();
            if(!name.isEmpty()){
                category.setName(name);
            } else {
                throw new Exception("Name must be not empty.");
            }

            String description = row[CATEGORY_IMPORT_INDEX_DESCRIPTION].trim();
            if(!description.isEmpty()){
                category.setDescription(description);
            }

            categoryRepository.save(category);

        }
    }

    public void getSubCategories(Category category, Set<Category> categories){

        if(categories == null){
            categories = new HashSet<>();
        }
        categories.add(category);
        Set<Category> subCategories = categoryRepository.findByParent(category);
        if(subCategories == null || subCategories.isEmpty()){
            return;
        } else {
            for(Category c: subCategories){
                getSubCategories(c, categories);
            }
        }

    }

}

















