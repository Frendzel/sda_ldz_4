package pl.erbel;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    ApplicationContext context;

    public static void main(String[] args) {
//        SpringApplication springApplication =
//                new SpringApplication(App.class);
//        Properties properties = new Properties();
//        properties.put("SERVER_PORT", 9999);
//        springApplication.setDefaultProperties(properties);
//        springApplication.run(args);
        SpringApplication.run(App.class);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println(context.getApplicationName());
    }
}
