package net.parwand.springregister.infrastructure.db;

import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<UserDto, Long> {

    UserDto findUserDtoByUsername(String username);

}
