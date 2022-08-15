package database.DBconfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.tables.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.sql.Date;
import java.util.List;
// serwer wykonuje operacje na bazie, przygotowuje string do zwrocenia klientowi
public class DBAPI { /*tutaj beda polaczenie z hibernate*/

    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    EntityTransaction entityTransaction;
    ObjectMapper objectMapper;
    int permissions=0; //0 brak  1 pacjent 2 lekarz
    public DBAPI() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager  entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();

    }

        // select
            public String loginAsPatient(int login,String password) throws JsonProcessingException
            {
                List<PatientLoginData> patientLoginDataList = entityManager.createQuery(
                        "SELECT log from PatientLoginData log WHERE log.login = ?1").setParameter(1,login).getResultList();
                objectMapper = new ObjectMapper();

                for (int i = 0; i < patientLoginDataList.size(); i++) {
                    if (password == patientLoginDataList.get(i).getPassword())
                    {
                        return "LOGGED";
                    }
                }
                return "ERROR";

    }
            public String loginAsDoctor(int login, String password) throws JsonProcessingException {
                List<DoctorLoginData> doctorLoginDataList = entityManager.createQuery(
                        "SELECT log from DoctorLoginData log WHERE log.login = ?1").setParameter(1,login).getResultList();
                objectMapper = new ObjectMapper();

                for (int i = 0; i < doctorLoginDataList.size(); i++) {
                    if (password == doctorLoginDataList.get(i).getPassword())
                    {
                        return "LOGGED";
                    }
                }
                return "ERROR";
            }


    public String getPatients() throws JsonProcessingException {
                entityTransaction.begin();

                List<Patients> patientsList = entityManager.createQuery("SELECT p FROM Patients p ").getResultList(); // TWORZY LISTE PATIENTOW
                objectMapper = new ObjectMapper();
                entityTransaction.commit();
                return (objectMapper.writeValueAsString(patientsList));
                }

            public String getPatientsByID(int id) throws JsonProcessingException {
                entityTransaction.begin();

                List<Patients> patientsList = entityManager.createQuery("SELECT p from Patients p where p.patientId = ?1").setParameter(1,id).getResultList();
                objectMapper = new ObjectMapper();
                entityTransaction.commit();

                return (objectMapper.writeValueAsString(patientsList));
            }

    public String getDoctors() throws JsonProcessingException {
        entityTransaction.begin();

        List<Doctors> doctorsList = entityManager.createQuery("SELECT d FROM Doctors d ").getResultList();
        objectMapper = new ObjectMapper();
        entityTransaction.commit();

        return (objectMapper.writeValueAsString(doctorsList));
    }

    public String getDoctorsByID(int id) throws JsonProcessingException {
        entityTransaction.begin();

        List<Patients> doctorsList = entityManager.createQuery("SELECT d FROM Doctors d  WHERE d.doctorId = ?1").setParameter(1,id).getResultList();
        objectMapper = new ObjectMapper();
        entityTransaction.commit();

        return (objectMapper.writeValueAsString(doctorsList));
    }

    public String getPrescriptions() throws JsonProcessingException {
        entityTransaction.begin();
        List<Prescriptions> prescriptionsList = entityManager.createQuery("SELECT pr from Prescriptions pr ").getResultList();
        objectMapper = new ObjectMapper();
        entityTransaction.commit();
        return (objectMapper.writeValueAsString(prescriptionsList));
    }
    public String getPrescriptionsBYprescID(int id) throws JsonProcessingException {
        entityTransaction.begin();
        List<Prescriptions> prescriptionsList = entityManager.createQuery("SELECT pr from Prescriptions pr WHERE pr.prescriptionId = ?1").setParameter(1,id).getResultList();
        objectMapper = new ObjectMapper();
        entityTransaction.commit();
        return (objectMapper.writeValueAsString(prescriptionsList));
    }
    public String getPrescriptionsBYpatientID(int id) throws JsonProcessingException {
        entityTransaction.begin();
        List<Prescriptions> prescriptionsList = entityManager.createQuery("SELECT pr from Prescriptions pr WHERE pr.prescriptionId = ?1 ").setParameter(1,id).getResultList();
        objectMapper = new ObjectMapper();
        entityTransaction.commit();
        return (objectMapper.writeValueAsString(prescriptionsList));
    }
    public String getPrescriptionsBYvisitID(int id) throws JsonProcessingException {
        entityTransaction.begin();
        List<Prescriptions> prescriptionsList = entityManager.createQuery("SELECT pr from Prescriptions pr WHERE pr.visitId = ?1").setParameter(1,id).getResultList();
        objectMapper = new ObjectMapper();
        entityTransaction.commit();
        return (objectMapper.writeValueAsString(prescriptionsList));
    }
    public String getPrescriptionsBYDate(Date date) throws JsonProcessingException {
        entityTransaction.begin();
        List<Prescriptions> prescriptionsList = entityManager.createQuery("SELECT pr from Prescriptions pr WHERE pr.dateOfIssue = ?1").setParameter(1,date.toString()).getResultList();

        objectMapper = new ObjectMapper();
        entityTransaction.commit();
        return (objectMapper.writeValueAsString(prescriptionsList));
    }

    public String getOffices () throws JsonProcessingException
    {
        entityTransaction.begin();
        List<Prescriptions> officesList = entityManager.createQuery("SELECT o from Offices o ").getResultList();
        objectMapper = new ObjectMapper();
        entityTransaction.commit();
        return (objectMapper.writeValueAsString(officesList));
    }

    public String getOfficesBYid (int id) throws JsonProcessingException
    {
        entityTransaction.begin();
        List<Prescriptions> officesList = entityManager.createQuery("SELECT o from Offices o WHERE o.officeNumber = ?1").setParameter(1,id).getResultList();
        objectMapper = new ObjectMapper();
        entityTransaction.commit();
        return (objectMapper.writeValueAsString(officesList));
    }
  /*  public String getOfficesBydate_freeoffices (int id) throws JsonProcessingException do zrobienia
    {
        entityTransaction.begin();
        List<Prescriptions> officesList = entityManager.createQuery("SELECT o from Offices o WHERE o.officeNumber = ?1").setParameter(1,id).getResultList();
        objectMapper = new ObjectMapper();
        entityTransaction.commit();
        return (objectMapper.writeValueAsString(officesList));
    }
    public String getOfficesBydate_takenoffices (int id) throws JsonProcessingException
    {
        entityTransaction.begin();
        List<Prescriptions> officesList = entityManager.createQuery("SELECT o from Offices o WHERE o.officeNumber = ?1").setParameter(1,id).getResultList();
        objectMapper = new ObjectMapper();
        entityTransaction.commit();
        return (objectMapper.writeValueAsString(officesList));
    }*/

    public String getVisits () throws JsonProcessingException
    {
        entityTransaction.begin();
        List<Prescriptions> visitsList = entityManager.createQuery("SELECT vis from Visits vis ").getResultList();
        objectMapper = new ObjectMapper();
        entityTransaction.commit();
        return (objectMapper.writeValueAsString(visitsList));
    }
    public String getVisitsBYvisID (int id) throws JsonProcessingException
    {
        entityTransaction.begin();
        List<Prescriptions> visitsList = entityManager.createQuery("SELECT vis from Visits vis WHERE vis.visitId = ?1").setParameter(1,id).getResultList();
        objectMapper = new ObjectMapper();
        entityTransaction.commit();
        return (objectMapper.writeValueAsString(visitsList));
    }
    public String getVisitsBYdocID (int id) throws JsonProcessingException
    {
        entityTransaction.begin();
        List<Prescriptions> visitsList = entityManager.createQuery("SELECT vis from Visits vis WHERE vis.doctorId = ?1").setParameter(1,id).getResultList();
        objectMapper = new ObjectMapper();
        entityTransaction.commit();
        return (objectMapper.writeValueAsString(visitsList));
    }
    public String getVisitsBYpatID (int id) throws JsonProcessingException
    {
        entityTransaction.begin();
        List<Prescriptions> visitsList = entityManager.createQuery("SELECT vis from Visits vis WHERE vis.patientId = ?1").setParameter(1,id).getResultList();
        objectMapper = new ObjectMapper();
        entityTransaction.commit();
        return (objectMapper.writeValueAsString(visitsList));
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
        entityManager.createQuery("DELETE FROM PatientLoginData p WHERE p.login  = ?1  ").setParameter(1,id);

        entityTransaction.commit();
    }



}
