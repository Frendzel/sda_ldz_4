package mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;

public class Connector implements ConnectorApi {

    final static Logger logger = getLogger(Connector.class);

    private PropertiesLoader propertiesLoader = new PropertiesLoader();

    private MongoClient client;

    private void prepareConfiguration() {
        ServerAddress address = new ServerAddress(propertiesLoader.getHost(), propertiesLoader.getPort());
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        MongoCredential credential = MongoCredential.createCredential(propertiesLoader.getUser(),
                propertiesLoader.getSchema(), propertiesLoader.getPass().toCharArray());
        credentials.add(credential);
        client = new MongoClient(address, credentials);
    }

    private void init() {
        try {
            propertiesLoader.init();
            prepareConfiguration();
        } catch (IOException e) {
            logger.error(e);
        }
    }

    private MongoDatabase connect() {
        init();
        return client.getDatabase(propertiesLoader.getSchema());
    }

    public FindIterable<Document> findDocuments(String collectionName,
                                                Bson filter,
                                                Bson projection) {
        MongoCollection<Document> collection = toMongoCollection(collectionName);
        return collection.find(filter).projection(projection);
    }

    public AggregateIterable<Document> aggregateDocuments(String collectionName,
                                                          List<Bson> pipeline) {
        MongoCollection<Document> collection = toMongoCollection(collectionName);
        return collection.aggregate(pipeline);
    }

    private MongoCollection<Document> toMongoCollection(String collectionName) {
        MongoDatabase connect = connect();
        return connect.getCollection(collectionName, Document.class);
    }

}
