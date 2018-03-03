package pl.erbel;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JednorozecScheduledComponent {

    private static final Logger LOGGER =
            Logger.getLogger(JednorozecScheduledComponent.class);

    @Autowired
    JobLauncher jobLauncher; //klasa pozwalająca na wywołanie danego joba manualnie

    @Autowired
    Job job;

    @Autowired
    BatchConfiguration batchConfiguration;

    @Async("batchThreadPool")
    @Scheduled(cron = "${save.jednorozec.cron}") // konfuguracja Crona
    public void run() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Thread thread = Thread.currentThread();
        LOGGER.info(thread.getId() + ":::" + thread.getName());
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()).toJobParameters();
        // jobParameters zawierają dodatkowe informacje o naszym jobie
        jobLauncher.run(job, jobParameters);
    }
}
