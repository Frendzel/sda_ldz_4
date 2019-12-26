package pl.erbel;

import org.apache.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.batch.core.BatchStatus.COMPLETED;

@Component
public class JednorozecJobListener extends JobExecutionListenerSupport {

    private static final Logger LOGGER = Logger.getLogger(JednorozecJobListener.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        LOGGER.info("Just before:" + jobExecution.getStatus());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        LOGGER.info("listener! ");
        if (COMPLETED.equals(jobExecution.getStatus())) { // BatchStatus.COMPLETED ustawiany po wykonaniu joba
            List<Jednorozec> zwierzaki = jdbcTemplate.query("SELECT * FROM jednorozec",
                    (rs, number) ->
                            new Jednorozec(rs.getInt(1),
                                    rs.getString(2),
                                    rs.getString(3),
                                    rs.getString(4),
                                    rs.getString(5)));
//            zwierzaki.forEach(System.out::println);
        }
    }

}
