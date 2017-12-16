package mongodb;

import org.apache.log4j.Logger;
import org.junit.Test;

import static org.apache.log4j.Logger.getLogger;

public class ConnectorTest {

    private static final String GRADES = "grades";

    private static final Logger logger = getLogger(ConnectorTest.class);

    Connector mongoDBConnector = new Connector();

    @Test
    public void findAllDocuments() {
        //given

        //when

        //then
    }

    @Test
    public void findOneDocument() {
        //given

        //when

        //then
    }

    @Test
    public void findStudentsIdGreaterThan100() {
        //given

        //when

        //then
    }

    @Test
    public void findStudentsIdGreaterThan100WhereGradeTypeIsExam() {
        //given

        //when

        //then
    }

    @Test
    public void findStudentsIdsWithAvg() {
        //given

        //when

        //then
    }

}