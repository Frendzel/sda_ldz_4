package pl.erbel;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static pl.erbel.UserGenerator.generateUsers;

@Repository
public class UserRepository implements IUserRepository {

    private static Logger LOG = Logger.getLogger(UserRepository.class);

    List<User> users = new ArrayList<>();

    @PostConstruct
    public void initUsers() {
        users = generateUsers();
    }

    @Override
    public User createUser(User user) {
        users.add(user);
        LOG.info("User: " + user + "has been added to list: ");
        return user;
    }

    @Override
    public User findUser(String login) throws UserNotFoundException {
        return users.stream().
                filter(user -> StringUtils.equals(user.getLogin(), login)).
                findAny().
                orElseThrow(() -> new UserNotFoundException(login));
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        User expectedUser = findUser(user.getLogin());

//        // STREAM
//        IntStream.range(0, users.size()).
//                filter(i ->
//                        users.get(i).getLogin().
//                                equals(expectedUser.getLogin())).
//                findFirst().
//                ifPresent(i -> {
//                    users.set(i, user);
//                });

        // TRADITIONAL :)
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getLogin().equals(expectedUser.getLogin())) {
                return users.set(i, user);
            }
        }
        return null;
    }

    @Override
    public boolean deleteUser(String login) throws UserNotFoundException {
        User expectedUser = findUser(login);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getLogin().equals(expectedUser.getLogin())) {
                return users.remove(expectedUser);
            }
        }
        return false;
    }

    @Override
    public List<User> findAll() {
        users.forEach(LOG::info);
        return users;
    }
}
