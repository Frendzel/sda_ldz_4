package pl.erbel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class UserService implements IUserRepository {

    private static final Logger LOGGER =
            Logger.getLogger(UserService.class);

    IUserRepository unknownRepository;

    @Autowired
    DBFactory dbFactory;

    @PostConstruct
    private void init(){
        this.unknownRepository = dbFactory.getRepository();
    }

    @Override
    public User createUser(User user) {
        User result = unknownRepository.createUser(user);
        LOGGER.info(result);
        return result;
    }

    @Override
    public User findUser(String login) throws UserNotFoundException {
        return unknownRepository.findUser(login);
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        return unknownRepository.updateUser(user);
    }

    @Override
    public boolean deleteUser(String login) throws UserNotFoundException {
        return unknownRepository.deleteUser(login);
    }

    @Override
    public List<User> findAll() {
        return unknownRepository.findAll();
    }

    private void simulateDBLag() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }

}
