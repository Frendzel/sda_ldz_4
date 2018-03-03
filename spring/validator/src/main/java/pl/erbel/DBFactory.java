package pl.erbel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DBFactory {

    @Value("${use.h2}")
    private Boolean useH2;

    @Value("${used.db}")
    private DB usedDb;

    @Autowired
    H2Repository h2Repository;

    @Autowired
    UserRepository userRepository;

    public IUserRepository getRepository() {
        return useH2 ? h2Repository : userRepository;
    }

    public DB getRepositoryByDB() {
        switch (usedDb) {
            case MEMORY:
                break;
            case MYSQL:
                break;
            case MARIADB:
                break;
            case MSSQL:
                break;
            case ORACLE:
                break;
            case POSTGRES:
                break;
            case CASSANDRA:
                break;
            case NEO4J:
                break;
            case COUCHBASE:
                break;
            case REDIS:
                break;
            case MONGODB:
                break;
        }
        return null;
    }

}
