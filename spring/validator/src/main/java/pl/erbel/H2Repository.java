package pl.erbel;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class H2Repository implements IUserRepository {

    //USE JPA

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User findUser(String login) throws UserNotFoundException {
        return null;
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        return null;
    }

    @Override
    public boolean deleteUser(String login) throws UserNotFoundException {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
