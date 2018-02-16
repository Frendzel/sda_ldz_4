package pl.erbel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * wykorzystać JDBCTemplate do:
 * zdropowania tabeli jeżeli istnieje
 * stworzenia tabeli
 * uzupełnienia tabeli
 * wykonania selecta
 * wykonania batchowego inserta
 * przepisania danych z tabeli do modelu javowego
 */

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    public void run(String... args) throws Exception {

    }
}
