package pl.erbel;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserService {

    private final JdbcTemplate jdbcTemplate;

    public UserService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void toMale(int id){
        jdbcTemplate.update("UPDATE USER SET first_name = 'test' WHERE id = ?", id);
        jdbcTemplate.update("UPDATE USER SET gender = 'MaleMaleMale' WHERE id = ?", id);
    }

    @Transactional
    public void wrongInsert(){
        jdbcTemplate.update("INSERT INTO USER (id, first_name, last_name, email, gender) " +
                "VALUES (?, ?, ?, ?, ?)", 1000, "a", "c", "123@wp.pl", "MALE");
        jdbcTemplate.update("INSERT INTO USER (id, first_name, last_name, email, gender) " +
                "VALUES (?, ?, ?, ?, ?)", 1001, "b", "c", "123@wp.pl", "SHEMALEMALEMALE");
    }

    public void selectUser(int id) {
        jdbcTemplate.query("SELECT * FROM USER WHERE id = ?",
                new Object[]{id},
                rs -> {
                    System.out.println("Twoje imie to: " + rs.getString("first_name"));
                    System.out.println("Twoja plec to: " + rs.getString("gender"));
                });
    }

    public void transform(){

//        selectUser(5);
//        try {
//            toMale(5);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        selectUser(5);
        try{
            wrongInsert();
        } catch(Exception e){

        }
        selectUser(998);
        selectUser(999);
    }

}
