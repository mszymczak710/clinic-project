package server;
// polaczenie serwer klient

import database.DBconfig.DBAPI;
import database.tables.*;

import java.io.*;
import java.net.Socket;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;


import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class ServerThread implements Runnable {

    Socket socket;
    String clientMessage;
    JSONObject jsonObject;
    DataInputStream inputStream;
    DataOutputStream outputStream;
    DBAPI dbapi;

    int accesslevel = 0; // 1 pat 2 doc
    int patid = -1;
    int docid = -1;

    public ServerThread(Socket socket) throws IOException {
        try {
            this.socket = socket;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        System.out.println("* ------ *");
        System.out.println("[ Usluga ]: polaczenia z: " + socket.getInetAddress() + "/" + socket.getPort());

        try {
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            dbapi = new DBAPI();

            clientCommunication(); // Otwieramy mozliwosc wymieniania informacji z clientem

            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("[ Usluga ]: Exception: " + e);
        }
        System.out.println("[ Usluga ]: rozlaczanie: " + socket.getInetAddress() + "/" + socket.getPort());
        System.out.println("* ------ *");
    }

    private void clientCommunication() throws IOException {
        /* czy sprawdzamy odrazu status accesu? */
        /*czy szukamy cos na pierwsze spojrzenie? */

        /*petla obslugujaca zapytania z klienta*/
        while (true) {


            if (jsonObject.get("command").equals("break"))
            {
                dbapi.close();
                break;
            }


            if (accesslevel == 0) // logowanie
            {
                clientMessage = inputStream.readUTF();
                jsonObject = (JSONObject) JSONValue.parse(clientMessage);

                JSONObject jsonObject2 = new JSONObject();

                if (jsonObject.get("usertype").equals("doc")) {

                    jsonObject2 = dbapi.loginAsDoctor((Integer) jsonObject.get("login"), (String) jsonObject.get("password"));
                    docid = (int) jsonObject2.get("doctor_id");
                    accesslevel = 2;
                }
                if (jsonObject.get("usertype").equals("pat")) {
                    jsonObject2 = dbapi.loginAsPatient((Integer) jsonObject.get("login"), (String) jsonObject.get("password"));
                    patid = (int) jsonObject2.get("patient_id");
                    accesslevel = 1;
                }
            }

            if (accesslevel > 0) // zalogowany
            {
                try {
                    clientMessage = inputStream.readUTF();
                    jsonObject = (JSONObject) JSONValue.parse(clientMessage);
                    clientMessage = (String) jsonObject.get("command");
                    if (clientMessage.equals("break")) break;

                    if (accesslevel >0 ) {
                        switch (clientMessage) {
                            case "getPatients":
                                getPatients();
                                break;
                            case "getPatientsByID":
                                getPatientsByID((int)jsonObject.get("args"));
                                break;
                            case "getDoctors":
                                getDoctors();
                                break;
                            case "getDoctorsByID":
                                getDoctorsByID((int)jsonObject.get("args"));
                                break;
                            case "getDoctorsByJobExecutionnumb":
                                getDoctorsByJobExecutionnumb((int)jsonObject.get("args"));
                                break;
                            case "getPrescriptions":
                               getPrescriptions();
                                break;
                            case "getPrescriptionsBYprescID":
                                getPrescriptionsBYprescID((int)jsonObject.get("args"));
                                break;
                            case "getPrescriptionsBYpatientID":
                                getPrescriptionsBYpatientID((int)jsonObject.get("args"));
                                break;
                            case "getPrescriptionsBYvisitID":
                                getPrescriptionsBYvisitID((int)jsonObject.get("args"));
                                break;
                            case "getPrescriptionsBYDate":
                                getPrescriptionsBYDate((Date)jsonObject.get("args"));
                                break;
                            case "getVisits":
                                getVisits();
                                break;
                            case "getVisitsBYvisID":
                                getVisitsBYvisID((int)jsonObject.get("args"));
                                break;
                            case "getVisitsBYdocID":
                                getVisitsBYdocID((int)jsonObject.get("args"));
                                break;
                            case "getVisitsBYpatID":
                                getVisitsBYpatID((int)jsonObject.get("args"));
                                break;


                        }
                        if (accesslevel ==2)
                            switch (clientMessage) {
                                case "updateVisit":
                                    updateVisit(jsonObject);
                                    break;
                                case "updatePatient":
                                    updatePatient(jsonObject);
                                    break;
                                case "insertVisit":
                                    Visits visitTOadd = new Visits();
                                    visitTOadd.setVisitId(0);
                                    visitTOadd.setDateOfVisit((Timestamp) jsonObject.get("dateOfVisit"));
                                    visitTOadd.setDurationInMinutes((int)jsonObject.get("durationInMinutes"));
                                    visitTOadd.setPatientId((int)jsonObject.get("patientId"));
                                    visitTOadd.setDoctorId((int)jsonObject.get("doctorId"));
                                    visitTOadd.setOfficeNumber((int)jsonObject.get("officeNumber"));

                                    insertVisit(visitTOadd);
                                    break;
                                case "insertPatient":
                                    Patients patientstoadd = new Patients();
                                    Patientlogindata patientlogindata = new Patientlogindata();

                                    if (jsonObject.get("pesel")!= null ) patientstoadd.setPesel((String)jsonObject.get("pesel"));
                                    patientstoadd.setPatientId(0);
                                    patientstoadd.setFirstName((String) jsonObject.get("firstName"));
                                    patientstoadd.setLastName((String) jsonObject.get("lastName"));
                                    patientstoadd.setDateOfBirth((Date)jsonObject.get("dateOfBirth"));
                                    patientstoadd.setAddress((String) jsonObject.get("address"));
                                    patientstoadd.setCity((String) jsonObject.get("city"));
                                    patientstoadd.setZipCode((String) jsonObject.get("zipCode"));
                                    patientstoadd.setPhoneNumber((String) jsonObject.get("phoneNumber"));
                                    patientstoadd.setEmailAddress((String) jsonObject.get("emailAddress"));

                                    insertPatient(patientstoadd,patientlogindata);
                                    int Patid;
                                    try
                                    {
                                        Patid = dbapi.getPatientIDnewlycreated(patientstoadd.getEmailAddress(),patientstoadd.getPhoneNumber());
                                    } catch (Exception e)
                                    {
                                        TimeUnit.SECONDS.sleep(10);
                                        Patid = dbapi.getPatientIDnewlycreated(patientstoadd.getEmailAddress(),patientstoadd.getPhoneNumber());
                                    }
                                    patientlogindata.setLogin(Patid);
                                    patientlogindata.setPassword((String) jsonObject.get("password"));

                                    break;
                                case "insertPrescription":
                                    Prescriptions prToadd = new Prescriptions();
                                    prToadd.setPrescriptionId(0);
                                    prToadd.setVisitId((int)jsonObject.get("visitId"));
                                    prToadd.setDescription((String) jsonObject.get("description"));
                                    prToadd.setCodeOfPrescription((int)jsonObject.get("codeOfPrescriptiion"));
                                    prToadd.setDateOfIssue((Date) jsonObject.get("dateOfIssue"));

                                    prToadd.setExpirationDate((Date) jsonObject.get("expirationDate"));

                                    Date date = getVisitsBYvisID2(prToadd.getVisitId());
                                    date.setMonth(date.getMonth()+2);
                                    prToadd.setExpirationDate(date);
                                    insertPrescription(prToadd);
                                    break;
                                case "deletePatient":
                                    deletePatient(  (int)jsonObject.get("id"));
                                    break;
                                case "deleteVisit":
                                    deleteVisit(  (int)jsonObject.get("id"));
                                    break;

                            }
                    }  else {
                        jsonObject.clear();
                        jsonObject.put("error", "1");
                        outputStream.writeUTF(jsonObject.toString());
                    }

                }catch (Exception e )
                {
                    break;
                }
            }

        }

    }
    private void getPatients ()
    {
        jsonObject.clear();
        jsonObject = dbapi.getPatients();
        try {
            outputStream.writeUTF(jsonObject.toString());
        } catch (IOException e) {
            System.out.println("error outputstrean.writeUTF");
        }
    }
    private void getPatientsByID ( int id)
    {
        jsonObject.clear();
        jsonObject = dbapi.getPatientsByID(id);
        try {
            outputStream.writeUTF(jsonObject.toString());
        } catch (IOException e) {
            System.out.println("error outputstrean.writeUTF");
        }
    }
    private void getDoctors ()
    {
        jsonObject.clear();
        jsonObject = dbapi.getDoctors();
        try {
            outputStream.writeUTF(jsonObject.toString());
        } catch (IOException e) {
            System.out.println("error outputstrean.writeUTF");
        }
    }
    private void getDoctorsByID ( int id)
    {
        jsonObject.clear();
        jsonObject = dbapi.getDoctorsByID(id);
        try {
            outputStream.writeUTF(jsonObject.toString());
        } catch (IOException e) {
            System.out.println("error outputstrean.writeUTF");
        }
    }
    private void getDoctorsByJobExecutionnumb (int id)
    {
        jsonObject.clear();
        jsonObject = dbapi.getDoctorsByJobExecutionnumb(id);
        try {
            outputStream.writeUTF(jsonObject.toString());
        } catch (IOException e) {
            System.out.println("error outputstrean.writeUTF");
        }
    }
    private void getPrescriptions ()
    {
        jsonObject.clear();
        jsonObject = dbapi.getPrescriptions();
        try {
            outputStream.writeUTF(jsonObject.toString());
        } catch (IOException e) {
            System.out.println("error outputstrean.writeUTF");
        }
    }
    private void getPrescriptionsBYprescID ( int id)
    {
        jsonObject.clear();
        jsonObject = dbapi.getPrescriptionsBYprescID(id);
        try {
            outputStream.writeUTF(jsonObject.toString());
        } catch (IOException e) {
            System.out.println("error outputstrean.writeUTF");
        }
    }
    private void getPrescriptionsBYpatientID ( int id)
    {
        jsonObject.clear();
        jsonObject = dbapi.getPrescriptionsBYpatientID(id);
        try {
            outputStream.writeUTF(jsonObject.toString());
        } catch (IOException e) {
            System.out.println("error outputstrean.writeUTF");
        }
    }
    private void getPrescriptionsBYvisitID ( int id)
    {
        jsonObject.clear();
        jsonObject = dbapi.getPrescriptionsBYvisitID(id);
        try {
            outputStream.writeUTF(jsonObject.toString());
        } catch (IOException e) {
            System.out.println("error outputstrean.writeUTF");
        }
    }
    private void getPrescriptionsBYDate (Date date)
    {
        jsonObject.clear();
        jsonObject = dbapi.getPrescriptionsBYDate(date);
        try {
            outputStream.writeUTF(jsonObject.toString());
        } catch (IOException e) {
            System.out.println("error outputstrean.writeUTF");
        }
    }
    private void getOffices ()
    {
        jsonObject.clear();
        jsonObject = dbapi.getOffices();
        try {
            outputStream.writeUTF(jsonObject.toString());
        } catch (IOException e) {
            System.out.println("error outputstrean.writeUTF");
        }
    }
    private void getOfficesBYid ( int id)
    {
        jsonObject.clear();
        jsonObject = dbapi.getOfficesBYid(id);
        try {
            outputStream.writeUTF(jsonObject.toString());
        } catch (IOException e) {
            System.out.println("error outputstrean.writeUTF");
        }
    }
   /* private void getOfficesBydate_freeoffices ( int id)
    {

    }
    private void getOfficesBydate_takenoffices ( int id)
    {

    }*/
    private void getVisits ()
    {
        jsonObject.clear();
        jsonObject = dbapi.getVisits();
        try {
            outputStream.writeUTF(jsonObject.toString());
        } catch (IOException e) {
            System.out.println("error outputstrean.writeUTF");
        }
    }
    private void getVisitsBYvisID ( int id)
    {
        jsonObject.clear();
        jsonObject = dbapi.getVisitsBYvisID(id);
        try {
            outputStream.writeUTF(jsonObject.toString());
        } catch (IOException e) {
            System.out.println("error outputstrean.writeUTF");
        }
    }
    private Date getVisitsBYvisID2 ( int id)
    {
        jsonObject.clear();
        jsonObject = dbapi.getVisitsBYvisID(id);

           Date date;
           date = (Date) jsonObject.get("DateOfissue");
        return date;
    }
    private void getVisitsBYdocID ( int id)
    {
        jsonObject.clear();
        jsonObject = dbapi.getVisitsBYdocID(id);
        try {
            outputStream.writeUTF(jsonObject.toString());
        } catch (IOException e) {
            System.out.println("error outputstrean.writeUTF");
        }
    }
    private void getVisitsBYpatID ( int id)
    {
        jsonObject.clear();
        jsonObject = dbapi.getVisitsBYpatID(id);
        try {
            outputStream.writeUTF(jsonObject.toString());
        } catch (IOException e) {
            System.out.println("error outputstrean.writeUTF");
        }
    }
    private void updateVisit (JSONObject jsonObject)
    {
        dbapi.updateVisit(jsonObject);
    }
    private void updatePatient (JSONObject jsonObject)
    {
        dbapi.updatePatient(jsonObject);
    }
    private void insertVisit (Visits visit)
    {
        dbapi.insertVisit(visit);
    }
    private void insertPatient (Patients patient,Patientlogindata patientlogindata)
    {
        dbapi.insertPatient(patient,patientlogindata);
    }
    private void insertPrescription (Prescriptions prescript)
    {
        dbapi.insertPrescription(prescript);
    }
    private void deletePatient ( int id)
    {
        dbapi.deletePatient(id);
    }
    private void deleteVisit ( int id)
    {
        dbapi.deleteVisit(id);
    }
}
