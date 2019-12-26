package pl.erbel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;


//TODO clean up
@SpringBootApplication
public class App implements CommandLineRunner {

    private static final Logger LOGGER =
            Logger.getLogger(CommandLineRunner.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private BatchConfiguration batchConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        LOGGER.info(batchConfiguration.getSaveJednorozecCronConfiguration());
    }
}
