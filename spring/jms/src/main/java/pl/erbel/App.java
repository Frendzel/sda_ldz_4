package pl.erbel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJms
@EnableScheduling
@EnableAspectJAutoProxy
public class App implements CommandLineRunner {

    @Autowired
    JmsTemplate jmsTemplate;

    RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTypeIdPropertyName("type");
        return converter;
    }

    @Override
    public void run(String... strings) throws Exception {
        jmsTemplate.convertAndSend("greeting", "Hello World");
    }

    @Scheduled(cron = "* * * * * *")
    public void sendJoke() {
        jmsTemplate.convertAndSend("joke", getRandomJoke());
    }

    @PleaseLogMe
    public Joke getRandomJoke() {
        Joke joke = restTemplate.
                getForObject("http://api.icndb.com/jokes/random",
                        Joke.class);

//        System.out.println(joke);
        return joke;
    }
}
