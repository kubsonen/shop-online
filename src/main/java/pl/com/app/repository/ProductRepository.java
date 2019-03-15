package pl.com.app.repository;

import org.springframework.data.repository.CrudRepository;
import pl.com.app.entity.Category;
import pl.com.app.entity.Product;

import java.util.Set;

/**
 * @author JNartowicz
 */
public interface ProductRepository extends CrudRepository<Product, Long> {
    Set<Product> findByCategoryIn(Set<Category> categories);
}
