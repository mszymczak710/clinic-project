package database.DBconfig;

import database.tables.Patients;

import jakarta.persistence.*;
import java.sql.Date;

public class DBOperations {

    public void patientInsertOperation() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();

            String str = "2001-01-03";
            Date dateOfBirth = Date.valueOf(str);

            Patients patient = new Patients();
            System.out.println(patient);
            patient.setFirstName("Testowy");
            patient.setLastName("dzialaj");
            patient.setDateOfBirth(dateOfBirth);
            patient.setZipCode("10-513");
            patient.setPesel("97862084754");
            patient.setCity("ryte blota");
            patient.setAddress("blotna 409");
            patient.setEmailAddress("www@xd.pl");
            patient.setPhoneNumber("441241244");
            System.out.println(patient);
            patient.setPatientId(112);
            entityManager.merge(patient);
            entityTransaction.commit();
        } finally {
            if(entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();

        }
    }

    public void patientSelectOperation(int id) {
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
        EntityManager entityManager = entityManagerFactory.createEntityManager();
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

            patient.setFirstName("Mariusz");
            patient.setLastName("Szymczak");
            patient.setEmailAddress("mszymczak710@o2.pl");
            entityTransaction.commit();
        } finally {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void patientDeleteOperation(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
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

            entityManager.remove(patient);
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
