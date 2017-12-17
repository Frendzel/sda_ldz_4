package model;

import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import java.io.Serializable;

/**
 * {
 * "_id" : {
 * "$oid" : "50906d7fa3c412bb040eb893"
 * },
 * "student_id" : 199,
 * "type" : "exam",
 * "score" : 67.33828604577803
 * }
 */
public class Grade implements Serializable, Bson {

    Object _id;

    Integer student_id;

    String type;

    Double score;

    public Grade() {
    }

    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Grade(Object _id, Integer student_id, String type, Double score) {
        this._id = _id;
        this.student_id = student_id;
        this.type = type;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "_id=" + _id +
                ", student_id=" + student_id +
                ", type='" + type + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
        return new BsonDocumentWrapper(this, codecRegistry.get(Grade.class));
    }
}
