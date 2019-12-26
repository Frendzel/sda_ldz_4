package pl.erbel;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class PleaseLogMeAspect {

    //TODO Wypisac czas wykonywania metody
    @Around("@annotation(pl.erbel.PleaseLogMe)")
    public void log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // wlacz stoper
        long invocationTime = System.currentTimeMillis();
        // uruchom metode
        proceedingJoinPoint.proceed();
        // oblicz czas wykonywania metody
        long executionTime = System.currentTimeMillis() - invocationTime;
        // zaloguj
        System.out.println(
                "Signature="+
                        proceedingJoinPoint.
                getSignature() // nazwa metody
                + "Args="
                + Arrays.toString(proceedingJoinPoint.getArgs()) // argumenty
                + "ExecTime"
                + executionTime); // czas wykonywania
    }

}
