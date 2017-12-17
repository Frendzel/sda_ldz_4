package mongodb;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import model.Grade;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static org.apache.log4j.Logger.getLogger;

public class ConnectorTest {

    private static final String GRADES = "grades";

    private static final Logger logger = getLogger(ConnectorTest.class);

    Connector mongoDBConnector = new Connector();

    @Test
    public void findAllDocuments() {
        //when
        FindIterable<Document> documents = mongoDBConnector.findDocuments(GRADES, new Document(), new Document());

        //then
        MongoCursor<Document> iterator = documents.iterator();
        JsonWriterSettings.Builder withIndents = JsonWriterSettings.builder().indent(true);

        while (iterator.hasNext()) {
            Document document = iterator.next();
            logger.info(document.toJson(withIndents.build()));
        }
        iterator.close();
    }

    @Test
    public void findOneDocument() {
        //given
        Bson where = eq("student_id", 100);

        //when
        FindIterable<Document> documents = mongoDBConnector.findDocuments(GRADES, where, new Document());

        //then
        MongoCursor<Document> iterator = documents.iterator();
        JsonWriterSettings.Builder withIndents = JsonWriterSettings.builder().indent(true);

        while (iterator.hasNext()) {
            Document document = iterator.next();
            logger.info(document.toJson(withIndents.build()));
        }
        iterator.close();
    }

    @Test
    public void findStudentsIdGreaterThan100() {
        //given
        Bson where = gt("student_id", 100);

        //when
        FindIterable<Document> documents = mongoDBConnector.findDocuments(GRADES, where, new Document());

        //then
        MongoCursor<Document> iterator = documents.iterator();
        JsonWriterSettings.Builder withIndents = JsonWriterSettings.builder().indent(true);

        while (iterator.hasNext()) {
            Document document = iterator.next();
            logger.info(document.toJson(withIndents.build()));
        }
        iterator.close();
    }

    //db.grades.find({ $and: [{ student_id: { $gt: 100 } }, { type: "exam" } ] })
    @Test
    public void findStudentsIdGreaterThan100WhereGradeTypeIsExam() {
        //when
        FindIterable<Document> documents = mongoDBConnector.findDocuments(GRADES,
                and(
                        gt("student_id", 100),
                        eq("type", "exam")),
                new Document());

        //then
        MongoCursor<Document> iterator = documents.iterator();
        JsonWriterSettings.Builder withIndents = JsonWriterSettings.builder().indent(true);

        while (iterator.hasNext()) {
            Document document = iterator.next();
            logger.info("Nasz dokument: " + document.toJson(withIndents.build()));
        }
        iterator.close();
    }

    @Test
    public void findStudentsIdsWithAvg() {
        //given
        Bson where = Aggregates.group("$student_id", Accumulators.avg("avgScore", "$score"));
        List<Bson> pipeline = new ArrayList<Bson>();
        pipeline.add(where);

        //when
        AggregateIterable<Document> documents = mongoDBConnector.aggregateDocuments(GRADES, pipeline);

        //then
        MongoCursor<Document> iterator = documents.iterator();
        JsonWriterSettings.Builder withIndents = JsonWriterSettings.builder().indent(true);

        while (iterator.hasNext()) {
            Document document = iterator.next();
            logger.info("Nasz dokument: " + document.toJson(withIndents.build()));
        }
        iterator.close();

    }

    @Test
    public void sortStudents() {
        //given
        Bson sort = Aggregates.sort(new Document("student_id", -1));
        ArrayList<Bson> bsons = new ArrayList<Bson>();
        bsons.add(sort);

        //when
        AggregateIterable<Document> documents = mongoDBConnector.aggregateDocuments(GRADES, bsons);

        //then
        MongoCursor<Document> iterator = documents.iterator();
        JsonWriterSettings.Builder withIndents = JsonWriterSettings.builder().indent(true);

        List<Grade> grades = new ArrayList<Grade>();
        while (iterator.hasNext()) {
            Document document = iterator.next();
            Object id = document.get("_id");
            Integer student_id = document.get("student_id", Integer.class);
            String type = document.getString("type");
            Double score = document.getDouble("score");
            Grade grade = new Grade(id, student_id, type, score);
            grades.add(grade);
        }
        iterator.close();

        grades.forEach(System.out::println);

    }

}