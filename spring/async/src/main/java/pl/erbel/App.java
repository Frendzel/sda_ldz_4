package pl.erbel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBootApp
 * Komponent który implementuje CommandLinera
 * Komponent który wywoła zewnętrzny serwis za pomocą RestTemplate'a
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
