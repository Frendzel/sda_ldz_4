package pl.erbel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserGeneratorTest {

    @Test
    public void pleaseTestMe() {
        //given
        List<String> randoms = new ArrayList<>();
        //when
        for (int i = 0; i < 10; i++) {
            String random = UserGenerator.generate(10);
            randoms.add(random);
        }
        //then
        randoms.forEach(System.out::println);
        //TODO assertion
    }

    @Test
    public void generateUsers() {
        //when
        List<User> users = UserGenerator.generateUsers();
        //then
        users.forEach(System.out::println);
    }

}