package pl.erbel;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {

    @JmsListener(destination = "greeting")
    @PleaseLogMe
    public void readMessage(String message) {
//        System.out.println(message);
    }

    @PleaseLogMe
    @JmsListener(destination = "joke")
    public void readJoke(Joke joke) {
//        System.out.println("Oh very funny! " + joke.value.id);
    }

    @PleaseLogMe
    @JmsListener(destination = "joke")
    public void printJokeType(Joke joke) throws InterruptedException {
        Thread.sleep(200);
//        System.out.println("Type of a joke:" + joke.type);
    }
}
