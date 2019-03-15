package pl.com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.app.entity.Category;
import pl.com.app.entity.Product;
import pl.com.app.repository.ProductRepository;
import pl.com.app.util.Util;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * @author JNartowicz
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Transactional
    public Set<Product> getProductsInCategory(Category category){
        Set<Category> categories = new HashSet<>();
        categoryService.getSubCategories(category, categories);
        return productRepository.findByCategoryIn(categories);
    }

    @Transactional
    public void saveProduct(Product product){

        if(product.getId() == null || product.getId() == 0){
            product.setProductCode(Util.generateProductCode());
        }

        productRepository.save(product);

    }

}
