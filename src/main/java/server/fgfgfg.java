package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.DBconfig.DBAPI;
import database.tables.Patients;
import database.tables.Prescriptions;
import database.tables.Visits;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.json.simple.JSONObject;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class fgfgfg {

    public static void main(String[] args) {
        /*
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();
        entityTransaction.begin();

        List<Visits> visitsList = entityManager.createQuery("SELECT vis from Visits vis ").getResultList();


        JSONObject jsonObject = new JSONObject();
        entityTransaction.commit();
        System.out.println(visitsList.size());

            for (int i = 0; i < visitsList.size(); i++) {
                jsonObject.put(Integer.toString(i),visitsList.get(i).toJSON());
            }
        System.out.println("to string    "+jsonObject.toString());

            System.out.println(jsonObject.toJSONString());
*/


        DBAPI dbapi = new DBAPI();
     /*   System.out.println(dbapi.getPatientIDnewlycreated("www@xd.pl","441241244"));
        System.out.println(dbapi.getDoctors());
        System.out.println(dbapi.getPatients());
        System.out.println(dbapi.getPatientsByID(1));
        System.out.println(dbapi.getPrescriptionsBYprescID(1));*/

    /*    Prescriptions prescriptions = new Prescriptions();
        prescriptions.setPrescriptionId(2);
        prescriptions.setCodeOfPrescription(1234);
        prescriptions.setDescription("test");
        prescriptions.setVisitId(1);
        prescriptions.setExpirationDate(Date.valueOf("1997-03-10"));
        prescriptions.setDateOfIssue(Date.valueOf("1997-03-10"));
        dbapi.insertPrescription(prescriptions);
        */

        Visits visitTOadd = new Visits();
        visitTOadd.setVisitId(7);

        Timestamp timestamp = new Timestamp(1662071210);
        visitTOadd.setDateOfVisit(timestamp);
        visitTOadd.setDurationInMinutes(83);
        visitTOadd.setPatientId(6);
        visitTOadd.setDoctorId(3);
        visitTOadd.setOfficeNumber(2);


        dbapi.insertVisit(visitTOadd);
    }

}
