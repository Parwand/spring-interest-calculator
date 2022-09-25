package net.parwand.springregister.applicationservice;

import net.parwand.springregister.domain.User;

public interface UserRepository {

    User findUserByUsername(String username);

    void save(User user);
}
