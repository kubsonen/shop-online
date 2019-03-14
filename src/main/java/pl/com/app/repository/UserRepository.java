package pl.com.app.repository;

import org.springframework.data.repository.CrudRepository;
import pl.com.app.entity.User;

/**
 * @author JNartowicz
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
