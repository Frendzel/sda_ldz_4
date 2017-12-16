package mongodb;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

public interface ConnectorApi {

    public FindIterable<Document>
    findDocuments(String collectionName,
                  Bson filter,
                  Bson projection);

    public AggregateIterable<Document>
    aggregateDocuments(String collectionName,
                       List<Bson> pipeline);

}
