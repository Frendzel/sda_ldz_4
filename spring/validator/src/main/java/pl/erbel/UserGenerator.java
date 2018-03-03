package pl.erbel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class UserGenerator {

    public static final int USERS_NUMBER = 5;
    private static final int NAME_LENGTH = 8;

    public static List<User> generateUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < USERS_NUMBER; i++) {
            User randomUser = createRandomUser(i);
            users.add(randomUser);
        }
        return users;
    }

    private static User createRandomUser(int i) {
        User user = new User();
        user.setId((long) i);
        user.setFirstName(randomAlphabetic(NAME_LENGTH));
        user.setLastName(randomAlphabetic(NAME_LENGTH));
        user.setPassword(randomAlphabetic(NAME_LENGTH));
        user.setLogin(randomAlphabetic(NAME_LENGTH));
        user.setBirthDate(new Date());
        return user;
    }
}
