package pl.com.app.repository;

import org.springframework.data.repository.CrudRepository;
import pl.com.app.entity.Category;

/**
 * @author JNartowicz
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
