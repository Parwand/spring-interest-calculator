package net.parwand.springregister.infrastructure.app;

import net.parwand.springregister.applicationservice.service.UserService;
import net.parwand.springregister.domain.Role;
import net.parwand.springregister.domain.model.user.User;
import net.parwand.springregister.applicationservice.UserRepository;
import net.parwand.springregister.applicationservice.exceptions.UserAlreadyExistException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UserService userService;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new UserDetailsImpl(user);
    }


    public void registerUser(User user) throws UserAlreadyExistException{
        if (existsUser(user)) {
            throw new UserAlreadyExistException("Username exits already");
        }
        user.setRole(Role.ROLE_USER);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.save(user);
    }

    public void registerStudent(User user) throws UserAlreadyExistException{

        if (existsUser(user)) {
            throw new UserAlreadyExistException("An Account for that user already exits");
        }
        user.setRole(Role.ROLE_STUDENT);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.save(user);
    }

    public boolean existsUser(User user){
        return  userService.getUser(user.getUsername()) != null;
    }
}
