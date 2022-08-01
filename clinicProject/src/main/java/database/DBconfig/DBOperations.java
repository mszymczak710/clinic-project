package database.DBconfig;

import database.tables.Patients;

import jakarta.persistence.*;
import java.sql.Date;

public class DBOperations {

    public void patientInsertOperation() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager  entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();

          try{
            entityTransaction.begin();

            String str = "2001-01-03";
            Date dateOfBirth = Date.valueOf(str);

            Patients patient = new Patients();
            System.out.println(patient.toString());
            patient.setFirstName("Adam");
            patient.setLastName("Probierz");
            patient.setDateOfBirth(dateOfBirth);
            patient.setZipCode("100-13");
            patient.setPesel("123456");
            patient.setCity("ryte blota");
            patient.setAddress("blotna 409");
            patient.setEmailAddress("www@xd.pl");
            patient.setPhoneNumber("441241244");
            System.out.println(patient.toString());
            patient.setPatientId(112);
            entityManager.merge(patient);
            entityTransaction.commit();
        } finally {
            if(entityTransaction.isActive())
            {
                entityTransaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();

        }
    }
    public void patienSelectOperation(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager  entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();

        try {
            Patients patient = entityManager.find(Patients.class, id);
            System.out.println("Patient ID : " + patient.getPatientId());
            System.out.println("PESEL :: " + patient.getPesel());
            System.out.println("First Name :: " + patient.getFirstName());
            System.out.println("Last Name :: " + patient.getLastName());
            System.out.println("Date of Birth :: " + patient.getDateOfBirth());
            System.out.println("City :: " + patient.getCity());
            System.out.println("ZIP Code :: " + patient.getZipCode());
            System.out.println("Phone number :: " + patient.getPhoneNumber());
            System.out.println("E-mail address :: " + patient.getEmailAddress());
            entityTransaction.commit();
        } finally {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
     }
    public void patientUpdateOperation(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager  entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();

        try {
            entityTransaction.begin();
            Patients patient = entityManager.find(Patients.class, id);
            System.out.println("Patient ID : " + patient.getPatientId());
            System.out.println("PESEL :: " + patient.getPesel());
            System.out.println("First Name :: " + patient.getFirstName());
            System.out.println("Last Name :: " + patient.getLastName());
            System.out.println("Date of Birth :: " + patient.getDateOfBirth());
            System.out.println("City :: " + patient.getCity());
            System.out.println("ZIP Code :: " + patient.getZipCode());
            System.out.println("Phone number :: " + patient.getPhoneNumber());
            System.out.println("E-mail address :: " + patient.getEmailAddress());

            StringBuilder query= new StringBuilder("UPDATE patients p SET ");
            query.append("first_name = ");
                query.append("'ZMIAAAAAAAA' ");
                    query.append(", last_name = ");
                            query.append("'DZIAAAAAAAAAJ' ");
                                 query.append("WHERE patient_id = ");
                                        query.append("6");
                System.out.println( query.toString());

                entityManager.createNativeQuery(query.toString()).executeUpdate();

                entityManager.flush();
                System.out.println( query.toString());
              entityTransaction.commit();

        } finally {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void PatientdeleteOperation(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager  entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();
            try {
                entityTransaction.begin();
                Patients patient = entityManager.find(Patients.class, id);
                System.out.println("Patient ID : " + patient.getPatientId());
                System.out.println("PESEL :: " + patient.getPesel());
                System.out.println("First Name :: " + patient.getFirstName());
                System.out.println("Last Name :: " + patient.getLastName());
                System.out.println("Date of Birth :: " + patient.getDateOfBirth());
                System.out.println("City :: " + patient.getCity());
                System.out.println("ZIP Code :: " + patient.getZipCode());
                System.out.println("Phone number :: " + patient.getPhoneNumber());
                System.out.println("E-mail address :: " + patient.getEmailAddress());
                StringBuilder queryd = new StringBuilder("DELETE FROM patients WHERE patient_id = ");
                queryd.append(id);
                        System.out.println(queryd.toString());
                entityManager.createNativeQuery(queryd.toString()).executeUpdate();

                entityManager.flush();
                entityTransaction.commit();
            } finally {
                if (entityTransaction.isActive()) {
                    entityTransaction.rollback();
                }
                entityManager.close();
                entityManagerFactory.close();
            }
    }
}
