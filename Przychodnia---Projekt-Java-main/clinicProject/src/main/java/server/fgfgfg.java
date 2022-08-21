package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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


    }

}
