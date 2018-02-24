package pl.erbel;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ChuckNorrisJokesCollector {

    private static final Logger LOG = Logger.getLogger(ChuckNorrisJokesCollector.class);

    RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("random")
    public Joke getRandomJoke(){
        Joke joke = restTemplate.
                getForObject("http://api.icndb.com/jokes/random",
                        Joke.class);
        System.out.println(joke);
        return joke;
    }

}
