package pl.erbel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserRepository {

    private static final Logger LOGGER =
            Logger.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCrudRepository userCrudRepository;

    @Override
    public User createUser(User user) {
        User result = userRepository.createUser(user);
        LOGGER.info(result);
        return result;
    }

    @Override
    public User findUser(String login) throws UserNotFoundException {
        return userRepository.findUser(login);
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        return userRepository.updateUser(user);
    }

    @Override
    public boolean deleteUser(String login) throws UserNotFoundException {
        return userRepository.deleteUser(login);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Cacheable("userByLogin")
    public User findUserByLogin(String login) {
        simulateDBLag();
        return userCrudRepository.findUserByLogin(login);
    }

    private void simulateDBLag() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }

    public User findUserByLoginOrFirstNameOrLastNameOrderByPassword(
            String login, String firstName, String password) {
        return userCrudRepository.findUserByLoginOrFirstNameOrLastNameOrderByPassword(
                login, firstName, password
        );
    }
}
