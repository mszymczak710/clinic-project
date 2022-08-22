package server;
// polaczenie serwer klient
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.DBconfig.DBAPI;
import database.tables.Doctors;

import java.io.*;
import java.net.Socket;
import java.sql.Date;

import com.fasterxml.jackson.*;
import database.tables.Patients;
import database.tables.Prescriptions;
import database.tables.Visits;
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
            if (accesslevel == 0) // logowanie
            {
                clientMessage = inputStream.readUTF();
                jsonObject = (JSONObject) JSONValue.parse(clientMessage);

                if (jsonObject.get("command").equals("break")) break;

                if (jsonObject.get("usertype").equals("doc")) {
                    JSONObject jsonObject2 = new JSONObject();
                    jsonObject2 = dbapi.loginAsDoctor((Integer) jsonObject.get("login"), (String) jsonObject.get("password"));
                    docid = (int) jsonObject2.get("doctor_id");
                    accesslevel = 2;
                }
                if (jsonObject.get("usertype").equals("pat")) {
                    JSONObject jsonObject2 = new JSONObject();
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
                                    updateVisit(jsonObject);
                                    break;
                                case "insertVisit":
                                    Visits visitTOadd = new Visits();
                                    visitTOadd.setDateOfVisit();
                                    // trzeba dodac duzo
                                    updateVisit(visitTOadd);
                                    break;
                                case "insertPatient":
                                    Patients patientstoadd = new Patients();
                                    //sety
                                    insertPatient(patientstoadd);
                                    break;
                                case "insertPrescription":
                                    Prescriptions prToadd = new Prescriptions();
                                    //sety
                                    insertPrescription(prToadd);
                                    break;
                                case "deletePatient":
                                    deletePatient(  (int)jsonObject.get("id"););
                                    break;
                                case "deleteVisit":
                                    deleteVisit(  (int)jsonObject.get("id"););
                                    break;

                            }
                    }  else {
                        jsonObject.clear();
                        jsonObject.put("error", "1");
                        outputStream.writeUTF(jsonObject.toString());
                    }

                }
            }

        }

    }
    private void getPatients ()
    {

    }
    private void getPatientsByID ( int id)
    {

    }
    private void getDoctors ()
    {

    }
    private void getDoctorsByID ( int id)
    {

    }
    private void getDoctorsByJobExecutionnumb (int id)
    {

    }
    private void getPrescriptions ()
    {

    }
    private void getPrescriptionsBYprescID ( int id)
    {

    }
    private void getPrescriptionsBYpatientID ( int id)
    {

    }
    private void getPrescriptionsBYvisitID ( int id)
    {

    }
    private void getPrescriptionsBYDate (Date date)
    {

    }
    private void getOffices ()
    {

    }
    private void getOfficesBYid ( int id)
    {

    }
    private void getOfficesBydate_freeoffices ( int id)
    {

    }
    private void getOfficesBydate_takenoffices ( int id)
    {

    }
    private void getVisits ()
    {

    }
    private void getVisitsBYvisID ( int id)
    {

    }
    private void getVisitsBYdocID ( int id)
    {

    }
    private void getVisitsBYpatID ( int id)
    {

    }
    private void updateVisit (JSONObject jsonObject)
    {
        dbapi.updateVisit(jsonObject);
    }
    private void insertVisit (Visits visit)
    {

    }
    private void insertPatient (Patients patient)
    {

    }
    private void insertPrescription (Prescriptions prescript)
    {

    }
    private void deletePatient ( int id)
    {

    }
    private void deleteVisit ( int id)
    {

    }
}
