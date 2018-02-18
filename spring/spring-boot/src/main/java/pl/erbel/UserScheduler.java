package pl.erbel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserScheduler {

    private static final Logger LOGGER = Logger.
            getLogger(UserScheduler.class);

    @Autowired
    UserCrudRepository userCrudRepository;

    @Scheduled(cron = "* * * * * *")
    public void log() {
        LOGGER.info("Number of users: " +
                userCrudRepository.count());
    }
}
