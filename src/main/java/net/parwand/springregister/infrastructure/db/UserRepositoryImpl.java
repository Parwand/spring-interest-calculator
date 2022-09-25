package net.parwand.springregister.infrastructure.db;

import net.parwand.springregister.domain.User;
import net.parwand.springregister.applicationservice.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private UserDao userDao;

    public UserRepositoryImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findUserByUsername(String username) {
        UserDto userDto = userDao.findUserDtoByUsername(username);
        if (userDto == null) {
            return null;
        }
        return new User(userDto.getId(), userDto.getUsername(), userDto.getPassword(), userDto.getRole());
    }

    @Override
    public void save(User user) {
        userDao.save(new UserDto(null, user.getUsername(), user.getPassword(), user.getRole()));
    }

}
