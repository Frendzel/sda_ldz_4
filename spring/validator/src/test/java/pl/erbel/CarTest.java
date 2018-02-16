package pl.erbel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class CarTest {

    @Test
    public void generateJson() throws JsonProcessingException {
        Car car = new Car(
                randomAlphabetic(35),
                (int) (Math.random() * 10 + 2),
                randomAlphabetic(35),
                randomAlphabetic(35),
                randomAlphabetic(35)
        );
        ObjectWriter ow =
                new ObjectMapper().
                        writer().
                        withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(car);
        System.out.println(json);
    }

}