package pl.erbel;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private static final Logger LOGGER = Logger.getLogger(Scheduler.class);

    @Scheduled(cron = "* * * * * *")
    public void log() {
        LOGGER.info("ThreadID: " + Thread.currentThread().getId());
    }
}
