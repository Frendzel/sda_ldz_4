package pl.erbel;


import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogExecutionTimeAspect {

    private static final Logger LOGGER = Logger.getLogger(LogExecutionTimeAspect.class);

    @Around("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.info("Invocation: " + proceedingJoinPoint.getSignature());
        long time = System.currentTimeMillis();
        proceedingJoinPoint.proceed();
        long executionTime = System.currentTimeMillis() - time;
        LOGGER.info("Method: " +
                proceedingJoinPoint.getSignature()
                + " Executed: "
                + executionTime);
    }
}
