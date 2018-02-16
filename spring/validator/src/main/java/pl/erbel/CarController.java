package pl.erbel;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class CarController {

    @RequestMapping("/car/random")
    public Car randomCar() {
        return new Car(
                randomAlphabetic(25),
                (int) (Math.random() * 5 + 2),
                randomAlphabetic(11),
                randomAlphabetic(10),
                randomAlphabetic(10)
        );
    }

    @RequestMapping("/car/random/invalid")
    @ResponseBody
    public @Valid
    Car invalidRandomCar() {
        return new Car(
                randomAlphabetic(35),
                (int) (Math.random() * 10 + 2),
                randomAlphabetic(35),
                randomAlphabetic(35),
                randomAlphabetic(35)
        );
    }

    @RequestMapping(value = "/validate", method = POST)
    public Car validate(@Valid @RequestBody Car car) {
        return car;
    }

}
