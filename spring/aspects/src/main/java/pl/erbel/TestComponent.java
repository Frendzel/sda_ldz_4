package pl.erbel;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestComponent {

    @PostConstruct
    @PleaseLogMe
    public void pleaseTestMyAspect(){
        System.out.println("HI!");
    }
}
