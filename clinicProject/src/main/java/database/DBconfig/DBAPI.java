package database.DBconfig;

import database.tables.*;
import gui.dane;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
// serwer wykonuje operacje na bazie, przygotowuje string do zwrocenia klientowi
public class DBAPI { /*tutaj beda polaczenie z hibernate*/

    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    EntityTransaction entityTransaction;

    public DBAPI() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction= entityManager.getTransaction();

    }

    // select
    public boolean loginAsPatient(int login, String password)
    {
        entityTransaction.begin();
        List<Patients> patientlogindataList = entityManager.createQuery(
                "SELECT log from Patients log WHERE log.patientId = ?1 AND log.password = ?2").setParameter(1,login).setParameter(2,password).getResultList();
        entityTransaction.commit();

        if(patientlogindataList.size()>0){
            dane.typ = 1;
            dane.idUser = patientlogindataList.get(0).getPatientId();
            return true;
        }
        else return false;
    }
    public boolean loginAsDoctor(int login, String password){ // login to execution number
        entityTransaction.begin();
        List<Doctors> doctorlogindataList = entityManager.createQuery(
                "SELECT log from Doctors log WHERE log.doctorId = ?1 AND log.password = ?2").setParameter(1,login).setParameter(2,password).getResultList();
        entityTransaction.commit();

        if(doctorlogindataList.size()>0){
            dane.typ = 2;
            dane.idUser = doctorlogindataList.get(0).getDoctorId();
            return true;
        }
        else return false;
    }


    public List<Patients> getPatients(){
        entityTransaction.begin();
        List<Patients> patientsList = entityManager.createQuery("SELECT p FROM Patients p ").getResultList(); // TWORZY LISTE PATIENTOW
        entityTransaction.commit();

        return patientsList;
    }

    public int getPatientIDnewlycreated (String mail, String telephone)
    {
        entityTransaction.begin();

        List<Patients> resultList =  entityManager.createQuery("select  p from Patients p WHERE p.emailAddress LIKE ?1 and p.phoneNumber LIKE ?2 ORDER BY p.patientId DESC  ").setParameter(1,mail).setParameter(2,telephone).getResultList();
        entityTransaction.commit();

        return resultList.get(0).getPatientId();
    }

    public Patients getPatientsByID(int id){
        entityTransaction.begin();

        List<Patients> patientsList = entityManager.createQuery("SELECT p from Patients p where p.patientId = ?1").setParameter(1,id).getResultList();

        entityTransaction.commit();

        return patientsList.get(0);
    }

    public List<Patients> getPatientsBySurname(String surname){
        entityTransaction.begin();

        List<Patients> patientsList = entityManager.createQuery("SELECT p from Patients p where p.lastName = ?1").setParameter(1,surname).getResultList();

        entityTransaction.commit();

        return patientsList;
    }

    public List<Patients> getPatientsByName(String name){
        entityTransaction.begin();

        List<Patients> patientsList = entityManager.createQuery("SELECT p from Patients p where p.firstName = ?1").setParameter(1,name).getResultList();

        entityTransaction.commit();

        return patientsList;
    }

    public List<Patients> getPatientsByNameSurname(String name, String surname){
        entityTransaction.begin();

        List<Patients> patientsList = entityManager.createQuery("SELECT p from Patients p where p.firstName = ?1 and p.lastName = ?2").setParameter(1,name).setParameter(2,surname).getResultList();

        entityTransaction.commit();

        return patientsList;
    }

    public List<Doctors> getDoctors()  {
        entityTransaction.begin();

        List<Doctors> doctorsList = entityManager.createQuery("SELECT d FROM Doctors d ").getResultList();
        entityTransaction.commit();

        return doctorsList;
    }

    public List<Patients> getDoctorsByID(int id) {
        entityTransaction.begin();

        List<Patients> doctorsList = entityManager.createQuery("SELECT d FROM Doctors d  WHERE d.doctorId = ?1").setParameter(1,id).getResultList();
        entityTransaction.commit();

        return doctorsList;
    }
    public List<Patients> getDoctorsByJobExecutionnumb(int id) {
        entityTransaction.begin();

        List<Patients> doctorsList = entityManager.createQuery("SELECT d FROM Doctors d  WHERE d.jobExecutionNumber = ?1").setParameter(1,id).getResultList();
        entityTransaction.commit();

        return doctorsList;
    }

    public List<Prescriptions> getPrescriptions(){
        entityTransaction.begin();
        List<Prescriptions> prescriptionsList = entityManager.createQuery("SELECT pr from Prescriptions pr ").getResultList();
        entityTransaction.commit();

        return prescriptionsList;
    }
    public List<Prescriptions> getPrescriptionsBYprescID(int id){
        entityTransaction.begin();
        List<Prescriptions> prescriptionsList = entityManager.createQuery("SELECT pr from Prescriptions pr WHERE pr.prescriptionId = ?1").setParameter(1,id).getResultList();

        return prescriptionsList;
    }
    public List<Prescriptions> getPrescriptionsBYpatientID(int id){
        entityTransaction.begin();
        List<Prescriptions> prescriptionsList = entityManager.createQuery("SELECT pr from Prescriptions pr WHERE pr.patientId = ?1 ").setParameter(1,id).getResultList();

        return prescriptionsList;
    }
    public List<Prescriptions> getPrescriptionsBYvisitID(int id){
        entityTransaction.begin();
        List<Prescriptions> prescriptionsList = entityManager.createQuery("SELECT pr from Prescriptions pr WHERE pr.visitId = ?1").setParameter(1,id).getResultList();

        return prescriptionsList;
    }
    public List<Prescriptions> getPrescriptionsBYDate(Date date){
        entityTransaction.begin();
        List<Prescriptions> prescriptionsList = entityManager.createQuery("SELECT pr from Prescriptions pr WHERE pr.dateOfIssue = ?1").setParameter(1,date.toString()).getResultList();

        return prescriptionsList;
    }

    public List<Offices> getOffices ()
    {
        entityTransaction.begin();
        List<Offices> officesList = entityManager.createQuery("SELECT o from Offices o ").getResultList();

        return officesList;
    }


    public List<Prescriptions> getOfficesBYid (int id)
    {
        entityTransaction.begin();
        List<Prescriptions> officesList = entityManager.createQuery("SELECT o from Offices o WHERE o.officeNumber = ?1").setParameter(1,id).getResultList();

        return officesList;
    }
  /*  public JSONobject getOfficesBydate_freeoffices (int id)do zrobienia
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

    public List<Visits> getVisits ()
    {
        entityTransaction.begin();

        List<Visits> visitsList = entityManager.createQuery("SELECT vis from Visits vis ").getResultList();

        entityTransaction.commit();
        return visitsList;
    }
    public List<Visits> getVisitsBYvisID (int id)
    {
        entityTransaction.begin();
        List<Visits> visitsList = entityManager.createQuery("SELECT vis from Visits vis WHERE vis.visitId = ?1").setParameter(1,id).getResultList();
        entityTransaction.commit();

        return visitsList;
    }
    public List<Visits> getVisitsBYdocID (int id)
    {
        entityTransaction.begin();
        List<Visits> visitsList = entityManager.createQuery("SELECT vis from Visits vis WHERE vis.doctorId = ?1").setParameter(1,id).getResultList();
        entityTransaction.commit();

        System.out.println(visitsList.size());


        return visitsList;
    }

    public static Timestamp addDays(Timestamp date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);// w ww.  j ava  2  s  .co m
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return new Timestamp(cal.getTime().getTime());

    }

    public List<Visits> getVisitsBYdocIDDay (int id, java.sql.Timestamp day)
    {
        entityTransaction.begin();
        Timestamp tmp = addDays(day, 1);
        System.out.println("TIME");
        System.out.println(tmp);
        List<Visits> visitsList = entityManager.createQuery("SELECT vis from Visits vis WHERE vis.doctorId = ?1 and vis.dateOfVisit > ?2 and vis.dateOfVisit < ?3").setParameter(1,id).setParameter(2, day).setParameter(3, tmp).getResultList();
        entityTransaction.commit();

        System.out.println(visitsList.size());

        System.out.println("Hello");
        System.out.println(visitsList.toString());
        return visitsList;
    }
    public List<Visits> getVisitsBYpatID (int id)
    {
        entityTransaction.begin();
        List<Visits> visitsList = entityManager.createQuery("SELECT vis from Visits vis WHERE vis.patientId = ?1").setParameter(1,id).getResultList();
        entityTransaction.commit();

        System.out.println(visitsList.size());

        return visitsList;
    }

    public List<Visits> getVisitsEnded(int id)
    {
        entityTransaction.begin();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        List<Visits> visitsList = entityManager.createQuery("SELECT vis from Visits vis WHERE vis.dateOfVisit < ?1 and vis.patientId = ?2").setParameter(1,timestamp).setParameter(2, id ).getResultList();
        entityTransaction.commit();

        System.out.println(visitsList.size());

        return visitsList;
    }

    public List<Visits> getVisitsComing(int id)
    {
        entityTransaction.begin();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        List<Visits> visitsList = entityManager.createQuery("SELECT vis from Visits vis WHERE vis.dateOfVisit >= ?1 and vis.patientId = ?2").setParameter(1,timestamp).setParameter(2,id).getResultList();
        entityTransaction.commit();

        System.out.println(visitsList.size());

        return visitsList;
    }

    public List<Visits> getVisitsEndedDoc(int id)
    {
        entityTransaction.begin();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        List<Visits> visitsList = entityManager.createQuery("SELECT vis from Visits vis WHERE vis.dateOfVisit < ?1 and vis.doctorId = ?2").setParameter(1,timestamp).setParameter(2, id ).getResultList();
        entityTransaction.commit();

        System.out.println(visitsList.size());

        return visitsList;
    }

    public List<Visits> getVisitsComingDoc(int id)
    {
        entityTransaction.begin();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        List<Visits> visitsList = entityManager.createQuery("SELECT vis from Visits vis WHERE vis.dateOfVisit >= ?1 and vis.doctorId = ?2").setParameter(1,timestamp).setParameter(2,id).getResultList();
        entityTransaction.commit();

        System.out.println(visitsList.size());

        return visitsList;
    }


    // update
//    public void updateVisit (int id, Visits dozmiany){
//        entityTransaction.begin();
//        List<Visits> visitsList = entityManager.createQuery("SELECT vis from Visits vis WHERE vis.visitId = ?1").setParameter(1,id).getResultList();
//        Visits visit = visitsList.get(0);
//        StringBuilder query = new StringBuilder("UPDATE visits v SET ");
//        if (dozmiany.get("date_of_visit") != null )
//        {
//            query.append("date_of_visit = ");
//            query.append((String) dozmiany.get("date_of_visit"));
//            if (dozmiany.get("duration_in_min") != null || dozmiany.get("patient_id") !=null  || dozmiany.get("doctor_id") != null
//                    ||dozmiany.get("office_number") != null  )                     query.append(", ");
//
//        }
//        if (dozmiany.get("duration_in_min") != null )
//        {
//            query.append("duration_in_min = ");
//            query.append((String) dozmiany.get("duration_in_min"));
//            if ( dozmiany.get("patient_id") !=null  || dozmiany.get("doctor_id") != null
//                    ||dozmiany.get("office_number") != null  )   query.append(", ");
//        }
//        if (dozmiany.get("patient_id") != null )
//        {
//            query.append("patient_id = ");
//            query.append((String) dozmiany.get("patient_id"));
//            if (dozmiany.get("doctor_id") != null ||dozmiany.get("office_number") != null  )    query.append(", ");
//        }
//        if (dozmiany.get("doctor_id") != null )
//        {
//            query.append("doctor_id = ");
//            query.append((String) dozmiany.get("doctor_id"));
//            if (dozmiany.get("office_number") != null  )   query.append(", ");
//        }
//        if (dozmiany.get("office_number") != null )
//        {
//            query.append("office_number = ");
//            query.append((String) dozmiany.get("office_number"));
//        }
//        query.append("WHERE visit_id = ");
//        query.append(dozmiany.get("visit_id"));
//        entityManager.createNativeQuery(query.toString()).executeUpdate();
//
//        entityManager.flush();
//        System.out.println( query.toString());
//        entityTransaction.commit();
//    }

    public void updatePatient (int id, Patients dozmiany)
    {
        entityTransaction.begin();
        List<Patients> patientsList = entityManager.createQuery("SELECT p from Patients p where p.patientId = ?1").setParameter(1,id).getResultList();

        Patients patient = patientsList.get(0);
        StringBuilder query = new StringBuilder("UPDATE patients SET ");


        if (dozmiany.getPesel() != patient.getPesel()) {
            query.append("pesel = '");
            query.append(dozmiany.getPesel());
            query.append("' ");
            if (dozmiany.getFirstName() != patient.getFirstName() || dozmiany.getLastName() != patient.getLastName()  || dozmiany.getDateOfBirth() != patient.getDateOfBirth() || dozmiany.getAddress() != patient.getAddress() || dozmiany.getCity() != patient.getCity() || dozmiany.getPhoneNumber() != patient.getPhoneNumber() ||dozmiany.getAddress() != patient.getAddress())
                query.append(", ");

        }
        if (dozmiany.getFirstName() != patient.getFirstName() ) {
            query.append("first_name = '");
            query.append(dozmiany.getFirstName());
            query.append("' ");
            if (dozmiany.getLastName() != patient.getLastName()  || dozmiany.getDateOfBirth() != patient.getDateOfBirth() || dozmiany.getAddress() != patient.getAddress() || dozmiany.getCity() != patient.getCity() || dozmiany.getPhoneNumber() != patient.getPhoneNumber() || dozmiany.getEmailAddress() != patient.getEmailAddress())
                query.append(", ");
        }
        if (dozmiany.getLastName() != patient.getLastName()) {
            query.append("last_name = '");
            query.append(dozmiany.getLastName());
            query.append("' ");
            if (dozmiany.getDateOfBirth() != patient.getDateOfBirth() ||dozmiany.getAddress() != patient.getAddress() || dozmiany.getCity() != patient.getCity() || dozmiany.getPhoneNumber() != patient.getPhoneNumber() || dozmiany.getEmailAddress() != patient.getEmailAddress() )
                query.append(", ");
        }
        if (dozmiany.getDateOfBirth() != patient.getDateOfBirth()) {
            query.append("date_of_birth = '");
            query.append(dozmiany.getDateOfBirth());
            query.append("' ");
            if (dozmiany.getAddress() != patient.getAddress() ||dozmiany.getCity() != patient.getCity() || dozmiany.getPhoneNumber() != patient.getPhoneNumber() ||dozmiany.getEmailAddress() != patient.getEmailAddress())
                query.append(", ");              }
        if (dozmiany.getAddress() != patient.getAddress()) {
            query.append("address = '");
            query.append(dozmiany.getAddress());
            query.append("' ");
            if (dozmiany.getCity() != patient.getCity() || dozmiany.getPhoneNumber() != patient.getPhoneNumber() || dozmiany.getEmailAddress() != patient.getEmailAddress())
                query.append(", ");
        }
        if (dozmiany.getCity() != patient.getCity()) {
            query.append("city = '");
            query.append(dozmiany.getCity());
            query.append("' ");
            if (dozmiany.getPhoneNumber() != patient.getPhoneNumber() || dozmiany.getEmailAddress() != patient.getEmailAddress() )
                query.append(", ");
        }
        if (dozmiany.getPhoneNumber() != patient.getPhoneNumber()) {
            query.append("phone_number = '");
            query.append(dozmiany.getPhoneNumber());
            query.append("' ");
            if (dozmiany.getEmailAddress() != patient.getEmailAddress())
                query.append(", ");
        }
        if (dozmiany.getEmailAddress() != patient.getEmailAddress()) {
            query.append("email_address = '");
            query.append(dozmiany.getEmailAddress());
            query.append("' ");
        }
        query.append("WHERE patient_id = ");
        query.append(dozmiany.getPatientId());
        entityManager.createNativeQuery(query.toString()).executeUpdate();
        entityManager.flush();
        System.out.println(query.toString());
        entityTransaction.commit();
    }
    // insert
    public void insertVisit (Visits visit)
    {
        entityTransaction.begin();
        System.out.println(visit.toString());
        entityManager.merge(visit);
        entityTransaction.commit();
    }
    public void insertPatient (Patients patient)
    {
        entityTransaction.begin();
        System.out.println(patient.toString());
        entityManager.merge(patient);
        entityTransaction.commit();
    }
    public void insertPrescription(Prescriptions prescript)
    {
        entityTransaction.begin();
        System.out.println(prescript.toString());
        entityManager.merge(prescript);
        entityTransaction.commit();
    }


    // delete
    public void deleteVisit (int id) {
        entityTransaction.begin();
        StringBuilder queryd = new StringBuilder("DELETE FROM visits vis WHERE vis.visit_id =");
        queryd.append(id);
        System.out.println(queryd.toString());
        entityManager.createNativeQuery(queryd.toString()).executeUpdate();
        entityTransaction.commit();
    }
    public void deletePatient (int id) {
        entityTransaction.begin();
        StringBuilder queryd = new StringBuilder("DELETE FROM patients pat WHERE pat.patient_id =");
        queryd.append(id);
        System.out.println(queryd.toString());
        entityManager.createNativeQuery(queryd.toString()).executeUpdate();
        entityTransaction.commit();
    }
    public void close()
    {
        if (entityTransaction.isActive()) {
            entityTransaction.rollback();
        }
        entityManager.close();
        entityManagerFactory.close();
    }


}
