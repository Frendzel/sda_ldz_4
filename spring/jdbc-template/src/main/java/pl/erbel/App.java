package pl.erbel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ChuckNorrisJokesCollector chuckNorrisJokesCollector;

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

        selectUser(5);
        try {
            toMale(5);
        } catch (Exception e) {
            System.out.println(e);
        }
        selectUser(5);

        chuckNorrisJokesCollector.getRandomJoke();
    }


    @Transactional
    public void toMale(int id) {
        jdbcTemplate.update("UPDATE USER SET first_name = 'test' WHERE id = ?", id);
        jdbcTemplate.update("UPDATE USER SET gender = 'MaleMaleMale' WHERE id = ?", id);
        throw new RuntimeException("Nie moge tego zrobic");
    }

    private void selectUser(int id) {
        jdbcTemplate.query("SELECT * FROM USER WHERE id = ?",
                new Object[]{id},
                rs -> {
                    System.out.println("Twoja plec to: " + rs.getString("gender"));
                    System.out.println("Twoje imie to: " + rs.getString("first_name"));
                });
    }

    public void csvRecords() throws IOException {
        FileReader reader = new FileReader("/Users/marcinerbel/Documents/sda4/sda_ldz_4/spring/jdbc-template/src/main/resources/inserts.csv");
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

