package pl.erbel;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PleaseLogMeAspect {

    //TODO Wypisac czas wykonywania metody
    @Around("@annotation(pl.erbel.PleaseLogMe)")
    public void log() {
        System.out.println("Around");
    }

    @After("@annotation(pl.erbel.PleaseLogMe)")
    public void logAfter() {
        System.out.println("After");
    }

}
