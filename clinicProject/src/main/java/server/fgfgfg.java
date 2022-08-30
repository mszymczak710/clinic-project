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
        System.out.println(dbapi.getPatientIDnewlycreated("www@xd.pl","441241244"));
        System.out.println(dbapi.getDoctors());
        System.out.println(dbapi.getPatients());
        System.out.println(dbapi.getPatientsByID(1));
        System.out.println(dbapi.getPrescriptionsBYprescID(1));
    }

}
