package pl.erbel;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class PleaseLogMeAspect {

    @Around("@annotation(pl.erbel.PleaseLogMe)")
    public void log() {
        System.out.println("I'm here");
    }
    @Before("@annotation(pl.erbel.PleaseLogMe)")
    public void logBefore() {
        System.out.println("I'm here");
    }
    @After("@annotation(pl.erbel.PleaseLogMe)")
    public void logAfter() {
        System.out.println("I'm here");
    }

}
