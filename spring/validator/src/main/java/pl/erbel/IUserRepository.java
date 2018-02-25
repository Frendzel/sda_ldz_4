package pl.erbel;

import java.util.List;

public interface IUserRepository {

    User createUser(User user);

    User findUser(String login) throws UserNotFoundException;

    User updateUser(User user) throws UserNotFoundException;

    boolean deleteUser(String login) throws UserNotFoundException;

    List<User> findAll();

}
