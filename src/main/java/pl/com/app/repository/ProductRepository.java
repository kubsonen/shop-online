package pl.com.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.com.app.entity.Category;
import pl.com.app.entity.Product;

import java.util.Set;

/**
 * @author JNartowicz
 */
public interface ProductRepository extends CrudRepository<Product, Long> {
    Set<Product> findByCategoryIn(Set<Category> categories);
    Product findByProductCode(String productCode);
    @Query("select p from Product p where p.productName like CONCAT('%',:productName,'%')")
    Set<Product> searchProductByName(@Param("productName") String productName);

}
