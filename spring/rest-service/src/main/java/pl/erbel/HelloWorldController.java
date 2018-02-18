package pl.erbel;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping("hello")
    public Greeting helloWorld(
            @RequestParam(defaultValue = "world") String name) {
        return new Greeting(name);
    }

//    @RequestMapping("greetings")
//    public Greeting greetings() {
//        return new Greeting("Marcin");
//    }

    @RequestMapping("greetings")
    public Greeting greetings(
            @RequestParam(defaultValue = "Jan") String name) {
        return new Greeting(name);
    }

    @RequestMapping(path = "testpost",
            method = RequestMethod.POST
    )
    public Greeting testpost() {
        return
                new Greeting("Request method has been set to POST");
    }


    // 1. ZADANIE
    // METODA POST I WYWOŁAĆ Z POSTMANA
    // 2. ZADANIE
    // ZIELONE TESTY INTEGRACYJNE

}
