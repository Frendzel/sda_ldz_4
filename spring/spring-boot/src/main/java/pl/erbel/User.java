package pl.erbel;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class User {

    private String name;

    public User() {
    }

    @PostConstruct
    public void setHello() {
        this.name = "Andrzej";
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
