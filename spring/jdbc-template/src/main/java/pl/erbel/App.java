package pl.erbel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
@EnableAspectJAutoProxy
public class App implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ChuckNorrisJokesCollector chuckNorrisJokesCollector;

    @Autowired
    UserService userService;

    @Value("${csv.file.path}")
    String csvFilePath;

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    public void run(String... args) throws Exception {
        System.out.println("JDBC TEMPLATE TEST");

        csvRecords();

        System.out.println("Wyniki:");
        jdbcTemplate.query("SELECT * FROM USER", rs -> {
            System.out.print(rs.getInt(1) + " ");
            System.out.print(rs.getString(2) + " ");
            System.out.print(rs.getString(3) + " ");
            System.out.print(rs.getString(4) + " ");
            System.out.print(rs.getString(5) + "\n");
        });

        chuckNorrisJokesCollector.getRandomJoke();

        //let's test transactions here:
//        userService.transform();

        try {
            userService.wrongInsert();
        } catch (Exception e) {
            System.out.println(e);
        }
        userService.selectUser(1000);
        userService.selectUser(1001);

        try {
            userService.selectUser(5);
            userService.toMale(5);
        } catch (Exception e) {
            System.out.println(e);
            userService.selectUser(5);
        }
    }

    public void csvRecords() throws IOException {
        FileReader reader = new FileReader(csvFilePath);
        CSVParser parse = CSVFormat.
                DEFAULT.
                withHeader("id", "first_name", "last_name", "email", "gender").
                withSkipHeaderRecord().
                parse(reader);
        Iterator<CSVRecord> result = parse.iterator();
        //id,first_name,last_name,email,gender
        String query = "INSERT INTO USER (id, first_name, last_name, email, gender) VALUES (?, ?, ?, ?, ?)";
        List<Object[]> queries = new ArrayList<>();
        while (result.hasNext()) {
            CSVRecord record = result.next();
            System.out.println(record);
            Object[] params = {record.get("id"),
                    record.get("first_name"),
                    record.get("last_name"),
                    record.get("email"),
                    record.get("gender")};
            queries.add(params);
        }
        jdbcTemplate.batchUpdate(query, queries);
    }
}

