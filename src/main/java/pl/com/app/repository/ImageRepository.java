package pl.com.app.repository;

import org.springframework.data.repository.CrudRepository;
import pl.com.app.entity.Image;

/**
 * @author JNartowicz
 */
public interface ImageRepository extends CrudRepository<Image, Long> {
}
