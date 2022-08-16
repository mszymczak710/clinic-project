package database.DBconfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.tables.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.json.simple.JSONObject;

import java.sql.Date;
import java.util.List;
// serwer wykonuje operacje na bazie, przygotowuje string do zwrocenia klientowi
public class DBAPI { /*tutaj beda polaczenie z hibernate*/

    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    EntityTransaction entityTransaction;
    JSONObject jsonObject ;
    int permissions=0; //0 brak  1 pacjent 2 lekarz
    public DBAPI() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager  entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();

    }

        // select
            public String loginAsPatient(int login,String password) throws JsonProcessingException
            {
                entityTransaction.begin();
                List<Patientlogindata> patientlogindataList = entityManager.createQuery(
                        "SELECT log from Patientlogindata log WHERE log.login = ?1").setParameter(1,login).getResultList();
                entityTransaction.commit();

                for (int i = 0; i < patientlogindataList.size(); i++) {
                    if (password == patientlogindataList.get(i).getPassword())
                    {
                        return "LOGGED";
                    }
                }
                return "ERROR";

    }
            public String loginAsDoctor(int login, String password) throws JsonProcessingException {
                entityTransaction.begin();

                List<Doctorlogindata> doctorlogindataList = entityManager.createQuery(
                        "SELECT log from Doctorlogindata log WHERE log.login = ?1").setParameter(1,login).getResultList();
                entityTransaction.commit();

                for (int i = 0; i < doctorlogindataList.size(); i++) {
                    if (password == doctorlogindataList.get(i).getPassword())
                    {
                        return "LOGGED";
                    }
                }
                return "ERROR";
            }


    public JSONObject getPatients() throws JsonProcessingException {
                entityTransaction.begin();

                List<Patients> patientsList = entityManager.createQuery("SELECT p FROM Patients p ").getResultList(); // TWORZY LISTE PATIENTOW
                entityTransaction.commit();
                 JSONObject jsonObject = new JSONObject();

                for (int i = 0; i < patientsList.size(); i++) {
                    jsonObject.put(Integer.toString(i),patientsList.get(i).toJSON());
                }
                return jsonObject;
        }

            public JSONObject getPatientsByID(int id) throws JsonProcessingException {
                entityTransaction.begin();

                List<Patients> patientsList = entityManager.createQuery("SELECT p from Patients p where p.patientId = ?1").setParameter(1,id).getResultList();

                entityTransaction.commit();

                JSONObject jsonObject = new JSONObject();

                for (int i = 0; i < patientsList.size(); i++) {
                    jsonObject.put(Integer.toString(i),patientsList.get(i).toJSON());
                }
            return jsonObject;
        }

    public JSONObject getDoctors() throws JsonProcessingException {
        entityTransaction.begin();

        List<Doctors> doctorsList = entityManager.createQuery("SELECT d FROM Doctors d ").getResultList();
        entityTransaction.commit();

        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < doctorsList.size(); i++) {
            jsonObject.put(Integer.toString(i),doctorsList.get(i).toJSON());
        }
        return jsonObject;
    }

    public JSONObject getDoctorsByID(int id) throws JsonProcessingException {
        entityTransaction.begin();

        List<Patients> doctorsList = entityManager.createQuery("SELECT d FROM Doctors d  WHERE d.doctorId = ?1").setParameter(1,id).getResultList();
        entityTransaction.commit();

        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < doctorsList.size(); i++) {
            jsonObject.put(Integer.toString(i),doctorsList.get(i).toJSON());
        }
        return jsonObject;
    }

    public JSONObject getPrescriptions() throws JsonProcessingException {
        entityTransaction.begin();
        List<Prescriptions> prescriptionsList = entityManager.createQuery("SELECT pr from Prescriptions pr ").getResultList();
        entityTransaction.commit();

        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < prescriptionsList.size(); i++) {
            jsonObject.put(Integer.toString(i),prescriptionsList.get(i).toJSON());
        }
        return jsonObject;
    }
    public JSONObject getPrescriptionsBYprescID(int id) throws JsonProcessingException {
        entityTransaction.begin();
        List<Prescriptions> prescriptionsList = entityManager.createQuery("SELECT pr from Prescriptions pr WHERE pr.prescriptionId = ?1").setParameter(1,id).getResultList();
        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < prescriptionsList.size(); i++) {
            jsonObject.put(Integer.toString(i),prescriptionsList.get(i).toJSON());
        }
        return jsonObject;
    }
    public JSONObject getPrescriptionsBYpatientID(int id) throws JsonProcessingException {
        entityTransaction.begin();
        List<Prescriptions> prescriptionsList = entityManager.createQuery("SELECT pr from Prescriptions pr WHERE pr.prescriptionId = ?1 ").setParameter(1,id).getResultList();
        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < prescriptionsList.size(); i++) {
            jsonObject.put(Integer.toString(i),prescriptionsList.get(i).toJSON());
        }
        return jsonObject;
    }
    public JSONObject getPrescriptionsBYvisitID(int id) throws JsonProcessingException {
        entityTransaction.begin();
        List<Prescriptions> prescriptionsList = entityManager.createQuery("SELECT pr from Prescriptions pr WHERE pr.visitId = ?1").setParameter(1,id).getResultList();
        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < prescriptionsList.size(); i++) {
            jsonObject.put(Integer.toString(i),prescriptionsList.get(i).toJSON());
        }
        return jsonObject;
    }
    public JSONObject getPrescriptionsBYDate(Date date) throws JsonProcessingException {
        entityTransaction.begin();
        List<Prescriptions> prescriptionsList = entityManager.createQuery("SELECT pr from Prescriptions pr WHERE pr.dateOfIssue = ?1").setParameter(1,date.toString()).getResultList();

        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < prescriptionsList.size(); i++) {
            jsonObject.put(Integer.toString(i),prescriptionsList.get(i).toJSON());
        }
        return jsonObject;
    }

    public JSONObject getOffices () throws JsonProcessingException
    {
        entityTransaction.begin();
        List<Offices> officesList = entityManager.createQuery("SELECT o from Offices o ").getResultList();
        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < officesList.size(); i++) {
            jsonObject.put(Integer.toString(i),officesList.get(i).toJSON());
        }
        return jsonObject;
    }

    public JSONObject getOfficesBYid (int id) throws JsonProcessingException
    {
        entityTransaction.begin();
        List<Prescriptions> officesList = entityManager.createQuery("SELECT o from Offices o WHERE o.officeNumber = ?1").setParameter(1,id).getResultList();
        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < officesList.size(); i++) {
            jsonObject.put(Integer.toString(i),officesList.get(i).toJSON());
        }
        return jsonObject;
    }
  /*  public JSONobject getOfficesBydate_freeoffices (int id) throws JsonProcessingException do zrobienia
    {
        entityTransaction.begin();
        List<Prescriptions> officesList = entityManager.createQuery("SELECT o from Offices o WHERE o.officeNumber = ?1").setParameter(1,id).getResultList();
        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < officesList.size(); i++) {
            jsonObject.put(Integer.toString(i),officesList.get(i).toJSON());
        }
        return jsonObject;
    }
    public JSONobject getOfficesBydate_takenoffices (int id) throws JsonProcessingException
    {
        entityTransaction.begin();
        List<Prescriptions> officesList = entityManager.createQuery("SELECT o from Offices o WHERE o.officeNumber = ?1").setParameter(1,id).getResultList();
     JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < officesList.size(); i++) {
            jsonObject.put(Integer.toString(i),officesList.get(i).toJSON());
        }
        return jsonObject;
    }*/

    public JSONObject getVisits ()
    {
        entityTransaction.begin();

        List<Visits> visitsList = entityManager.createQuery("SELECT vis from Visits vis ").getResultList();

        entityTransaction.commit();
        jsonObject = new JSONObject();

        System.out.println(visitsList.size());

        for (int i = 0; i < visitsList.size(); i++) {
            jsonObject.put(Integer.toString(i),visitsList.get(i).toJSON());
        }
        return jsonObject;
    }
    public JSONObject getVisitsBYvisID (int id) throws JsonProcessingException
    {
        entityTransaction.begin();
        List<Prescriptions> visitsList = entityManager.createQuery("SELECT vis from Visits vis WHERE vis.visitId = ?1").setParameter(1,id).getResultList();
        entityTransaction.commit();
        jsonObject = new JSONObject();

        System.out.println(visitsList.size());

        for (int i = 0; i < visitsList.size(); i++) {
            jsonObject.put(Integer.toString(i),visitsList.get(i).toJSON());
        }
        return jsonObject;
    }
    public JSONObject getVisitsBYdocID (int id) throws JsonProcessingException
    {
        entityTransaction.begin();
        List<Prescriptions> visitsList = entityManager.createQuery("SELECT vis from Visits vis WHERE vis.doctorId = ?1").setParameter(1,id).getResultList();
        entityTransaction.commit();
        jsonObject = new JSONObject();

        System.out.println(visitsList.size());

        for (int i = 0; i < visitsList.size(); i++) {
            jsonObject.put(Integer.toString(i),visitsList.get(i).toJSON());
        }
        return jsonObject;
    }
    public JSONObject getVisitsBYpatID (int id) throws JsonProcessingException
    {
        entityTransaction.begin();
        List<Prescriptions> visitsList = entityManager.createQuery("SELECT vis from Visits vis WHERE vis.patientId = ?1").setParameter(1,id).getResultList();
        entityTransaction.commit();
        jsonObject = new JSONObject();

        System.out.println(visitsList.size());

        for (int i = 0; i < visitsList.size(); i++) {
            jsonObject.put(Integer.toString(i),visitsList.get(i).toJSON());
        }
        return jsonObject;
    }


        // update

        // insert
        public void insertVisit (Visits visit)
        {
            // jak robimy inserty?
        }
    public void insertPatient (Patients patient)
    {
        // jak robimy inserty?
    }
    public void insertPrescription(Prescriptions prescript)
    {

    }


        // delete
        public void deleteVisit (int id)
        {
            entityTransaction.begin();
            entityManager.createQuery("DELETE FROM Visits vis WHERE vis.visitId = ?1  ").setParameter(1,id);
            entityTransaction.commit();
        }
    public void deletePatient (int id)
    {
        entityTransaction.begin();
        entityManager.createQuery("DELETE FROM Patients pat WHERE pat.patientId = ?1  ").setParameter(1,id);
        entityManager.createQuery("DELETE FROM Patientlogindata p WHERE p.login  = ?1  ").setParameter(1,id);

        entityTransaction.commit();
    }



}
