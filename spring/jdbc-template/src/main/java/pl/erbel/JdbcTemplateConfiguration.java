package pl.erbel;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:jdbc-template.properties")
@PropertySource(value = "C:\\temp\\jdbc-template.properties", ignoreResourceNotFound = true)
@PropertySource(value = "/home/jdbc-template.properties", ignoreResourceNotFound = true)
public class JdbcTemplateConfiguration {

}
