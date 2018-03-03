package pl.erbel;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.concurrent.Executor;

// TODO Properties @PropertySource
@Configuration
@EnableScheduling // Wlaczamy schedulera
@EnableBatchProcessing
@EnableAsync
@PropertySource(value = {"classpath:/cron.properties",
        "file:${user.home}/cron.properties"},
        ignoreResourceNotFound = true)
public class BatchConfiguration {


    /* JOB składa się z wielu STEPów
     * Każdy STEP składa się z implementacji:
     * - ItemReadera
     * - ItemWritera
     * - Processora
     */

    @Qualifier("dataSource") // nazwa klasy implementującej podany interfejs użyta do wstrzyknięcia beana
    @Autowired
    DataSource dataSource;

    @Autowired // builder użyty do utworzenia joba
            JobBuilderFactory jobBuilderFactory;

    @Autowired // builder użyty do utworzenia stepa
            StepBuilderFactory stepBuilderFactory;

    @Autowired
    JednorozecItemReadListener jednorozecItemReadListener;

    @Autowired
    JednorozecItemWriteListener JednorozecItemWriteListener;

    @Value("${save.jednorozec.cron}")
    String saveJednorozecCronConfiguration;

    public String getSaveJednorozecCronConfiguration() {
        return saveJednorozecCronConfiguration;
    }

    public void setSaveJednorozecCronConfiguration(String saveJednorozecCronConfiguration) {
        this.saveJednorozecCronConfiguration = saveJednorozecCronConfiguration;
    }

    @Scope()
    @Bean("batchThreadPool" )
    public Executor batchThreadPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.setCorePoolSize(8);
        threadPoolTaskExecutor.setThreadNamePrefix("sda:");
        threadPoolTaskExecutor.setThreadGroupName("batch");
        threadPoolTaskExecutor.setBeanName("batchThreadPool");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Bean
    JdbcBatchItemWriter<Jednorozec> jednorozecWriter() {
        JdbcBatchItemWriter<Jednorozec> writer = new JdbcBatchItemWriter(); // przykładowa implementacja ItemWritera
        writer.setDataSource(dataSource); // ustawienie namiarów na baze danych
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Jednorozec>());
        // ustawienie sposobu podstawiania danych do uzupełnionego niżej sqla
        writer.setSql("INSERT INTO jednorozec (id,imie,nazwisko,email,plec) " +
                "VALUES (:id," +
                ":imie," +
                ":nazwisko," +
                ":email," +
                ":plec)");
        writer.afterPropertiesSet(); // walidacja porawności uzupełnionych danych
        return writer;
    }

    @Bean
    FlatFileItemReader<Jednorozec> jednorozecReader() {
        // Przykładowa imlementacja iteam readaera zaczytująca z pliku csv
        FlatFileItemReader<Jednorozec> flatFileItemReader = new FlatFileItemReader<Jednorozec>();
        flatFileItemReader.setResource(new ClassPathResource("jednorozec.csv")); //zaczytanie pliku csv z classpatha
        flatFileItemReader.setLinesToSkip(1); // ominięcie nagłówka pliku csv
        // Ustawienie tokenizera mapującego wartości kolumn
        flatFileItemReader.setStrict(false);
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        // Nazwu kolumn
        delimitedLineTokenizer.setNames(new String[]
                {"id", "imie", "nazwisko", "email", "plec"});
        delimitedLineTokenizer.setStrict(false);

        // Ustawienie mappera tak aby wskazywał na docelową klasę
        BeanWrapperFieldSetMapper<Jednorozec> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Jednorozec.class);
        fieldSetMapper.setStrict(false);

        // Ustawienie tokenizera oraz mapera na domyślnej implementacji mapera dla implementacji itemReadera.
        DefaultLineMapper<Jednorozec> mapper = new DefaultLineMapper<>();
        mapper.setLineTokenizer(delimitedLineTokenizer);
        mapper.setFieldSetMapper(fieldSetMapper);

        flatFileItemReader.setLineMapper(mapper);
        return flatFileItemReader;
    }

    // Deklaracja procesora, który jest odpowiedzialny za główny proces kroku danego Joba
    @Bean
    ItemProcessor<Jednorozec, Jednorozec> processor() {
        return new JednorozecItemProcessor();
    }

    // Tworzymy Joba o danej nazwie i określonym flow, w którym podajem kroki, które będą wykonywane oraz listener
    // Listener dostarcza implementacje beforeJob oraz afterJob, które umożliwiwają wykonanie instrukcji
    // przed wykonaniem joba oraz po wykonaniu joba.
    @Bean
    Job saveJednorozec(JednorozecJobListener listener) {
        return jobBuilderFactory.get("saveJednorozec").
                flow(firstMigrationStep()).end().
                listener(listener).
                build();
    }

    //Tworzymy step
    @Bean
    Step firstMigrationStep() {
        return stepBuilderFactory.
                get("firstMigrationStep").
                <Jednorozec, Jednorozec>
                        chunk(10). //rozmiar pociętych danych
                reader(jednorozecReader()). //itemReader
                listener(jednorozecItemReadListener). //itemReaderListener
                listener(JednorozecItemWriteListener). //itemWriterListener
                processor(processor()). //processor
                writer(jednorozecWriter()). //itemWriter
                build();
    }

}
