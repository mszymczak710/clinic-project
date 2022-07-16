package org.clinic.database.functions;

import org.clinic.database.tables.Patient;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.sql.Date;

import static org.clinic.database.functions.JPAUtil.getEntityManagerFactory;

// definicja operacji na bazie
public class DatabaseOperations {
    public void insertOperation() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        String str = "2001-01-03";
        Date dateOfBirth = Date.valueOf(str);
        Patient patient = new Patient(1, "01210307910", "Mateusz", "Szymczak", dateOfBirth, "Jana Sobieskiego 8/26", "Torun", "87-100", "578224122", null);
        entityManager.persist(patient);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void selectOperation() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Patient patient = entityManager.find(Patient.class, 1);
        System.out.println("Patient ID : " + patient.getPatientId());
        System.out.println("PESEL :: " + patient.getPesel());
        System.out.println("First Name :: " + patient.getFirstName());
        System.out.println("Last Name :: " + patient.getLastName());
        System.out.println("Date of Birth :: " + patient.getDateOfBirth());
        System.out.println("City :: " + patient.getCity());
        System.out.println("ZIP Code :: " + patient.getZipCode());
        System.out.println("Phone number :: " + patient.getPhoneNumber());
        System.out.println("E-mail address :: " + patient.getEmailAddress());

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateOperation() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Patient patient = entityManager.find(Patient.class, 1);
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
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteOperation() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Patient patient = entityManager.find(Patient.class, 1);
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
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
