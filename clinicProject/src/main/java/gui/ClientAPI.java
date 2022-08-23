package gui;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.tables.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.print.Doc;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// client pobiera zapytania z bazy i wysyla komendy do watku serwera
// przenosimy zapytania z api do bazy
public class ClientAPI {
    Socket socket;
    String clientMessage;
    static JSONObject jsonObject ;
    DataInputStream inputStream;
    DataOutputStream outputStream;
    String userName="";
    int IDuser=-1;
    int levelAccess=0; // 0 nzal 1 pat 2 lekarz

    public ClientAPI() {
        inputStream = new DataInputStream();
        outputStream = new DataOutputStream();
        jsonObject = new JSONObject();
    }
/*
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
*/


}
