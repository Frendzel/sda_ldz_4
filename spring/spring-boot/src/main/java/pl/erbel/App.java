package pl.erbel;

import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    User user;

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    public void run(java.lang.String... args) throws Exception {
        //Spojrzec w kontekst
        System.out.println(applicationContext.getBeanDefinitionNames());
        System.out.println(user);

    }
}
