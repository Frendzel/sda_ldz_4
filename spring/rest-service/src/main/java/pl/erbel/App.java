package pl.erbel;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(App.class);
        Properties properties = new Properties();
        properties.put("SERVER_PORT", 9999);
        springApplication.setDefaultProperties(properties);
        springApplication.run(args);
    }
}
