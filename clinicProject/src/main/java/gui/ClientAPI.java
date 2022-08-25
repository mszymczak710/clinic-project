package gui;

import database.tables.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.net.Socket;
import java.util.*;

// client pobiera zapytania z bazy i wysyla komendy do watku serwera
// przenosimy zapytania z api do bazy
public class ClientAPI {

    static Socket socket;
    static String clientMessage;
    static JSONObject jsonObject ;
    static DataInputStream inputStream;
    static DataOutputStream outputStream;
    static  String userName="";
    static int IDuser=-1;
     static int levelAccess=0; // 0 nzal 1 pat 2 lekarz

    public ClientAPI() {
        try {
            socket = new Socket("localhost",9999);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream();
            jsonObject = new JSONObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    void login_patient (int id, String password ) throws IOException {
        jsonObject.clear();
        jsonObject.put("id",id);
        jsonObject.put("password",password); // niezaszyfrowane

        outputStream.writeUTF(jsonObject.toString());
        if (jsonObject.get("status") == "TRUE") {
            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
            levelAccess = (int) jsonObject.get("level");
            IDuser = (int) jsonObject.get("user");
            userName = jsonObject.get("id").toString();
        }
        else
        {
            System.out.println("nie udalo sie zalogowac");
        }

    }
    void login_doctor (int id, String password ) throws IOException {
        jsonObject.clear();
        jsonObject.put("id",id);
        jsonObject.put("password",password); // niezaszyfrowane

        outputStream.writeUTF(jsonObject.toString());
        if (jsonObject.get("status") == "TRUE") {
            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
            levelAccess = (int) jsonObject.get("level");
            IDuser = (int) jsonObject.get("user");
            userName = jsonObject.get("id").toString();
        }
        else
        {
            System.out.println("nie udalo sie zalogowac");
        }

    }

    private void exit ()
    {
        jsonObject.clear();
        jsonObject.put("command","break");
        outputStream.writeUTF(jsonObject.toString());


    }
    private static List<Patients> getPatients(JSONObject json_fromDB)
    {
        List <Patients> list = new ArrayList<>();
        for (int i=0; i<json_fromDB.size();i++)
        {
            list.add ()
        }


    }
    private static List<Patients> getPatientsByID()
    {

    }
    private static List<Doctors> getDoctors()
    {

    }
    private static List<Doctors> getDoctorsByID()
    {

    }
    private static List<Doctors> getDoctorsByJobExcutionnumb()
    {

    }
    private static List<Prescriptions> getPrescriptions()
    {

    }
    private static List<Prescriptions>  getPrescriptionsBYprescID()
    {

    }
    private static List<Prescriptions>  getPrescriptionsBYpatientID()
    {

    }
    private static List<Prescriptions>   getPrescriptionsBYvisitID()
    {

    }
    private static List<Prescriptions>   getPrescriptionsBYDate()
    {

    }
    private static List<Offices> getOffices()
    {

    }
    private static List<Offices> getOfficesBYid()
    {

    }
    private static List<Visits> getVisits()
    {

    }
    private static List<Visits> getVisitsBYvisID()
    {

    }
    private static List<Visits> getVisitsBYdocID()
    {

    }
    private static List<Visits> getVisitsBYpatID()
    {

    }
    private static void insertPatient (String pesel, String firstName, String lastName, java.sql.Date dateOfBirth, String address, String city, String zipCode, String phoneNumber, String emailAddress, String Password )
    {
        jsonObject.clear();

        jsonObject.put("command","insertPatient");
        if (!pesel.isEmpty())jsonObject.put("pesel",pesel);
        jsonObject.put("firstName",firstName);
        jsonObject.put("lastName",lastName);
        jsonObject.put("dateOfBirth",dateOfBirth);
        jsonObject.put("address",address);
        jsonObject.put("city",city);
        jsonObject.put("zipCode",zipCode);
        jsonObject.put("phoneNumber",phoneNumber);
        jsonObject.put("emailAddress",emailAddress);
        jsonObject.put("password",Password);

        outputStream.writeUTF(jsonObject.toString());
    }
    private static void insertPrescription (String description,java.sql.Date dateOfIssue,int visitId)
    {
        jsonObject.clear();
        jsonObject.put("command","insertPrescription");

        jsonObject.put("visitId",visitId);
        jsonObject.put("description",description);
        jsonObject.put("dateOfIssue",dateOfIssue);
        Random r = new Random();
        int codeofpresc = r.nextInt(8999)+1000;
        jsonObject.put("codeOfPrescription",codeofpresc);
        outputStream.writeUTF(jsonObject.toString());
    }
    private static void insertVisit(java.sql.Date dateOfVisit,int durInmin,int patientId, int officeNumber) {
        if (levelAccess == 2 && IDuser!=-1) {
            jsonObject.clear();
            jsonObject.put("command","insertVisit");
            jsonObject.put("dateOfVisit", dateOfVisit);
            jsonObject.put("durationInMinutes", durInmin);
            jsonObject.put("patientId", patientId);
            jsonObject.put("doctorId",String.valueOf(IDuser) );
            jsonObject.put("officeNumber", officeNumber);
        }
    }
    private static void deletePatient(int id) throws IOException {
        jsonObject.clear();
        jsonObject.put("command","deletePatient");
        jsonObject.put("id",id);
        outputStream.writeUTF(jsonObject.toString());
    }
    private static void deleteVisit(int id) throws IOException {
        jsonObject.clear();
        jsonObject.put("command","deleteVisit");
        jsonObject.put("id",id);
        outputStream.writeUTF(jsonObject.toString());
    }
*/

}
