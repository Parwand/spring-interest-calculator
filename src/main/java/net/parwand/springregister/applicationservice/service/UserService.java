package net.parwand.springregister.applicationservice.service;

import net.parwand.springregister.applicationservice.repository.UserRepository;
import net.parwand.springregister.domain.model.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User getUser(String username) {
        return repository.findUserByUsername(username);
    }

    public void save(User user) {
        repository.save(user);
    }
}
