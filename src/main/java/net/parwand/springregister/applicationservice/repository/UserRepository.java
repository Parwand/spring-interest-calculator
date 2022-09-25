package net.parwand.springregister.applicationservice.repository;

import net.parwand.springregister.domain.model.user.User;

public interface UserRepository {

    User findUserByUsername(String username);

    void save(User user);
}
