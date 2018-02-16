package pl.erbel;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarManager {

    List<Car> cars = new ArrayList<Car>();

    public void saveCar(Car car) {
        cars.add(car);
    }

    public List<Car> findAll() {
        return cars;
    }

    public Car find(Car car) throws CarException {
        return cars.stream().
                filter(c -> c.equals(car)).findAny().
//                orElseThrow(() -> new CarException());
                orElseThrow(CarException::new);
    }
}
