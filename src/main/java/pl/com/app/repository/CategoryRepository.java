package pl.com.app.repository;

import org.springframework.data.repository.CrudRepository;
import pl.com.app.entity.Category;

import java.util.Set;

/**
 * @author JNartowicz
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByAcronym(String acronym);
    Set<Category>  findByParent(Category category);
    Set<Category>  findAll();
}
