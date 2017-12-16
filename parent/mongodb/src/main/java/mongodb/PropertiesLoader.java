package mongodb;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Integer.valueOf;

public class PropertiesLoader {

    final static Logger logger = Logger.getLogger(PropertiesLoader.class);
    public static String PATH = "connection.properties";
    Properties prop = new Properties();

    public void init() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PATH);

        if (inputStream != null) {
            try {
                prop.load(inputStream);
            } catch (IOException e) {
                logger.error(e);
            } finally {
                inputStream.close();
                logger.debug("inputStream is closed");
            }
        } else {
            throw new FileNotFoundException("property file '" + PATH + "' not found in the classpath");
        }
    }

    public String getUser() {
        return getProperty("mongodb.user");
    }

    public String getPass() {
        return getProperty("mongodb.password");
    }

    public String getHost() {
        return getProperty("mongodb.host");
    }

    public int getPort() {
        return valueOf(getProperty("mongodb.port"));
    }

    public String getSchema() {
        return getProperty("mongodb.schema");
    }

    private String getProperty(String key) {
        return prop.getProperty(key);
    }

}