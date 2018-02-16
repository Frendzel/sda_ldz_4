package pl.erbel;

import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import static org.apache.log4j.Logger.getLogger;

@SpringBootApplication
@EnableCaching
public class App implements CommandLineRunner {

    private static final Logger LOGGER = getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
