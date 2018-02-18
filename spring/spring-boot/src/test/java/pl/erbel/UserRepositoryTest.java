package pl.erbel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserRepositoryTest {

    UserRepository userRepository;

    @Before
    public void init() {
        userRepository = new UserRepository();
        userRepository.initUsers();
    }

    @Test
    public void countUsers() {
        List<User> users = userRepository.findAll();
        Assert.assertEquals(users.size(), UserGenerator.USERS_NUMBER);
    }

    //TODO
    @Test
    public void findUser() {

    }

    //TODO
    @Test
    public void createUser() {

    }

    //TODO
    @Test
    public void updateUser() {

    }

    //TODO
    @Test
    public void deleteUser() {

    }

}