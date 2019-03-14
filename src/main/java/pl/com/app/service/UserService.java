package pl.com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.com.app.entity.User;
import pl.com.app.repository.UserRepository;

import javax.transaction.Transactional;

/**
 * @author JNartowicz
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        user.getAuthorities().size();

        if(user != null){
            return user;
        }
        throw new UsernameNotFoundException(username);
    }

}
