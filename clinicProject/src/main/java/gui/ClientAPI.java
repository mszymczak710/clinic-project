//package gui;
//
//import database.tables.*;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONValue;
//
//import java.io.*;
//import java.net.Socket;
//import java.sql.Date;
//import java.util.*;
//
//// client pobiera zapytania z bazy i wysyla komendy do watku serwera
//// przenosimy zapytania z api do bazy
//public class ClientAPI {
//
//    static Socket socket;
//    static String clientMessage;
//    static JSONObject jsonObject ;
//    static DataInputStream inputStream;
//    static DataOutputStream outputStream;
//    static  String userName="";
//    static int IDuser=-1;
//    static int levelAccess=0; // 0 nzal 1 pat 2 lekarz
//
//    public ClientAPI() {
//        try {
//            socket = new Socket("localhost",9999);
//            inputStream = new DataInputStream(socket.getInputStream());
//            outputStream = new DataOutputStream(socket.getOutputStream());
//            jsonObject = new JSONObject();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    public static void main(String[] args) {
//    }
//
//    void login_patient (int id, String password ) throws IOException {
//        jsonObject.clear();
//        jsonObject.put("id",id);
//        jsonObject.put("password",password); // niezaszyfrowane
//        jsonObject.put("usertype","pat");
//
//        outputStream.writeUTF(jsonObject.toString());
//        jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
//        if (jsonObject.get("status") == "TRUE") {
//            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
//            levelAccess = (int) jsonObject.get("level");
//            IDuser = (int) jsonObject.get("user");
//            userName = jsonObject.get("id").toString();
//        }
//        else
//        {
//            System.out.println("nie udalo sie zalogowac");
//        }
//
//    }
//    void login_doctor (int id, String password ) throws IOException {
//        jsonObject.clear();
//        jsonObject.put("id",id);
//        jsonObject.put("password",password); // niezaszyfrowane
//        jsonObject.put("usertype","doc");
//        outputStream.writeUTF(jsonObject.toString());
//        if (jsonObject.get("status") == "TRUE") {
//            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
//            levelAccess = (int) jsonObject.get("level");
//            IDuser = (int) jsonObject.get("user");
//            userName = jsonObject.get("id").toString();
//        }
//        else
//        {
//            System.out.println("nie udalo sie zalogowac");
//        }
//
//    }
//
//    private void exit ()
//    {
//        jsonObject.clear();
//        jsonObject.put("command","break");
//        try {
//            outputStream.writeUTF(jsonObject.toString());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
//    private static List<Patients> getPatients()
//    {
//        jsonObject.clear();
//        jsonObject.put("command", "getPatients");
//        try {
//            outputStream.writeUTF(jsonObject.toString());
//            jsonObject.clear();
//            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
//            if (jsonObject.isEmpty())
//            {
//                return new ArrayList<Patients>();
//            }
//            else
//            {
//                List<Patients> list = new ArrayList<>();
//                for (int i =0; i<jsonObject.size();i++)
//                {
//                    list.add(new Patients( (JSONObject)jsonObject.get(Integer.toString(i)) )   );
//                }
//                return list;
//            }
//
//
//        } catch (IOException e) {
//            return new ArrayList<Patients>();
//        }
//
//    }
//    private static List<Patients> getPatientsByID(int id)
//    {
//        jsonObject.clear();
//        jsonObject.put("command", "getPatientsByID");
//        jsonObject.put("args",Integer.toString(id));
//        try {
//            outputStream.writeUTF(jsonObject.toString());
//            jsonObject.clear();
//            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
//            if (jsonObject.isEmpty())
//            {
//                return new ArrayList<Patients>();
//            }
//            else
//            {
//                List<Patients> list = new ArrayList<>();
//                for (int i =0; i<jsonObject.size();i++)
//                {
//                    list.add(new Patients( (JSONObject)jsonObject.get(Integer.toString(i)) )   );
//                }
//                return list;
//            }
//
//
//        } catch (IOException e) {
//            return new ArrayList<Patients>();
//        }
//    }
//    private static List<Doctors> getDoctors()
//    {
//        jsonObject.clear();
//        jsonObject.put("command", "getDoctors");
//        try {
//            outputStream.writeUTF(jsonObject.toString());
//            jsonObject.clear();
//            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
//            if (jsonObject.isEmpty())
//            {
//                return new ArrayList<Doctors>();
//            }
//            else
//            {
//                List<Doctors> list = new ArrayList<>();
//                for (int i =0; i<jsonObject.size();i++)
//                {
//                    list.add(new Doctors( (JSONObject)jsonObject.get(Integer.toString(i)) )   );
//                }
//                return list;
//            }
//
//
//        } catch (IOException e) {
//            return new ArrayList<Doctors>();
//        }
//    }
//    private static List<Doctors> getDoctorsByID(int id)
//    {
//        jsonObject.clear();
//        jsonObject.put("command", "getDoctorsByID");
//        jsonObject.put("args",Integer.toString(id));
//        try {
//            outputStream.writeUTF(jsonObject.toString());
//            jsonObject.clear();
//            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
//            if (jsonObject.isEmpty())
//            {
//                return new ArrayList<Doctors>();
//            }
//            else
//            {
//                List<Doctors> list = new ArrayList<>();
//                for (int i =0; i<jsonObject.size();i++)
//                {
//                    list.add(new Doctors( (JSONObject)jsonObject.get(Integer.toString(i)) )   );
//                }
//                return list;
//            }
//
//
//        } catch (IOException e) {
//            return new ArrayList<Doctors>();
//        }
//    }
//    private static List<Doctors> getDoctorsByJobExcutionnumb(int id)
//    {
//        jsonObject.clear();
//        jsonObject.put("command", "getDoctorsByJobExcutionnumb");
//        jsonObject.put("args",Integer.toString(id));
//        try {
//            outputStream.writeUTF(jsonObject.toString());
//            jsonObject.clear();
//            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
//            if (jsonObject.isEmpty())
//            {
//                return new ArrayList<Doctors>();
//            }
//            else
//            {
//                List<Doctors> list = new ArrayList<>();
//                for (int i =0; i<jsonObject.size();i++)
//                {
//                    list.add(new Doctors( (JSONObject)jsonObject.get(Integer.toString(i)) )   );
//                }
//                return list;
//            }
//
//
//        } catch (IOException e) {
//            return new ArrayList<Doctors>();
//        }
//    }
//    private static List<Prescriptions> getPrescriptions()
//    {
//        jsonObject.clear();
//        jsonObject.put("command", "getPrescriptions");
//        try {
//            outputStream.writeUTF(jsonObject.toString());
//            jsonObject.clear();
//            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
//            if (jsonObject.isEmpty())
//            {
//                return new ArrayList<Prescriptions>();
//            }
//            else
//            {
//                List<Prescriptions> list = new ArrayList<>();
//                for (int i =0; i<jsonObject.size();i++)
//                {
//                    list.add(new Prescriptions( (JSONObject)jsonObject.get(Integer.toString(i)) )   );
//                }
//                return list;
//            }
//
//
//        } catch (IOException e) {
//            return new ArrayList<Prescriptions>();
//        }
//    }
//    private static List<Prescriptions>  getPrescriptionsBYprescID(int id)
//    {
//        jsonObject.clear();
//        jsonObject.put("command", "getPrescriptionsBYprescID");
//        jsonObject.put("args",Integer.toString(id));
//        try {
//            outputStream.writeUTF(jsonObject.toString());
//            jsonObject.clear();
//            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
//            if (jsonObject.isEmpty())
//            {
//                return new ArrayList<Prescriptions>();
//            }
//            else
//            {
//                List<Prescriptions> list = new ArrayList<>();
//                for (int i =0; i<jsonObject.size();i++)
//                {
//                    list.add(new Prescriptions( (JSONObject)jsonObject.get(Integer.toString(i)) )   );
//                }
//                return list;
//            }
//
//
//        } catch (IOException e) {
//            return new ArrayList<Prescriptions>();
//        }
//    }
//    private static List<Prescriptions>  getPrescriptionsBYpatientID(int id)
//    {
//        jsonObject.clear();
//        jsonObject.put("command", "getPrescriptionsBYpatientID");
//        jsonObject.put("args",Integer.toString(id));
//        try {
//            outputStream.writeUTF(jsonObject.toString());
//            jsonObject.clear();
//            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
//            if (jsonObject.isEmpty())
//            {
//                return new ArrayList<Prescriptions>();
//            }
//            else
//            {
//                List<Prescriptions> list = new ArrayList<>();
//                for (int i =0; i<jsonObject.size();i++)
//                {
//                    list.add(new Prescriptions( (JSONObject)jsonObject.get(Integer.toString(i)) )   );
//                }
//                return list;
//            }
//
//
//        } catch (IOException e) {
//            return new ArrayList<Prescriptions>();
//        }
//    }
//    private static List<Prescriptions>   getPrescriptionsBYvisitID(int id)
//    {
//        jsonObject.clear();
//        jsonObject.put("command", "getPrescriptionsBYvisitID");
//        jsonObject.put("args",Integer.toString(id));
//        try{
//            outputStream.writeUTF(jsonObject.toString());
//            jsonObject.clear();
//            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
//            if (jsonObject.isEmpty())
//            {
//                return new ArrayList<Prescriptions>();
//            }
//            else
//            {
//                List<Prescriptions> list = new ArrayList<>();
//                for (int i =0; i<jsonObject.size();i++)
//                {
//                    list.add(new Prescriptions( (JSONObject)jsonObject.get(Integer.toString(i)) )   );
//                }
//                return list;
//            }
//
//
//        } catch (IOException e) {
//            return new ArrayList<Prescriptions>();
//        }
//    }
//    private static List<Prescriptions>   getPrescriptionsBYDate(Date date)
//    {
//        jsonObject.clear();
//        jsonObject.put("command", "getPrescriptionsBYprescID");
//        jsonObject.put("args",date.toString());
//        try {
//            outputStream.writeUTF(jsonObject.toString());
//            jsonObject.clear();
//            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
//            if (jsonObject.isEmpty())
//            {
//                return new ArrayList<Prescriptions>();
//            }
//            else
//            {
//                List<Prescriptions> list = new ArrayList<>();
//                for (int i =0; i<jsonObject.size();i++)
//                {
//                    list.add(new Prescriptions( (JSONObject)jsonObject.get(Integer.toString(i)) )   );
//                }
//                return list;
//            }
//
//
//        } catch (IOException e) {
//            return new ArrayList<Prescriptions>();
//        }
//    }
//    private static List<Offices> getOffices()
//    {
//        jsonObject.clear();
//        jsonObject.put("command", "getOffices");
//        try {
//            outputStream.writeUTF(jsonObject.toString());
//            jsonObject.clear();
//            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
//            if (jsonObject.isEmpty())
//            {
//                return new ArrayList<Offices>();
//            }
//            else
//            {
//                List<Offices> list = new ArrayList<>();
//                for (int i =0; i<jsonObject.size();i++)
//                {
//                    list.add(new Offices( (JSONObject)jsonObject.get(Integer.toString(i)) )   );
//                }
//                return list;
//            }
//
//
//        } catch (IOException e) {
//            return new ArrayList<Offices>();
//        }
//    }
//    private static List<Offices> getOfficesBYid(int id)
//    {
//        jsonObject.clear();
//        jsonObject.put("command", "getOffices");
//        jsonObject.put("args",Integer.toString(id));
//        try {
//            outputStream.writeUTF(jsonObject.toString());
//            jsonObject.clear();
//            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
//            if (jsonObject.isEmpty())
//            {
//                return new ArrayList<Offices>();
//            }
//            else
//            {
//                List<Offices> list = new ArrayList<>();
//                for (int i =0; i<jsonObject.size();i++)
//                {
//                    list.add(new Offices( (JSONObject)jsonObject.get(Integer.toString(i)) )   );
//                }
//                return list;
//            }
//
//
//        } catch (IOException e) {
//            return new ArrayList<Offices>();
//        }
//    }
//    private static List<Visits> getVisits()
//    {
//        jsonObject.clear();
//        jsonObject.put("command", "getVisits");
//        try {
//            outputStream.writeUTF(jsonObject.toString());
//            jsonObject.clear();
//            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
//            if (jsonObject.isEmpty())
//            {
//                return new ArrayList<Visits>();
//            }
//            else
//            {
//                List<Visits> list = new ArrayList<>();
//                for (int i =0; i<jsonObject.size();i++)
//                {
//                    list.add(new Visits( (JSONObject)jsonObject.get(Integer.toString(i)) )   );
//                }
//                return list;
//            }
//
//
//        } catch (IOException e) {
//            return new ArrayList<Visits>();
//        }
//    }
//    private static List<Visits> getVisitsBYvisID(int id)
//    {
//        jsonObject.clear();
//        jsonObject.put("command", "getVisitsBYvisID");
//        jsonObject.put("args",Integer.toString(id));
//        try {
//            outputStream.writeUTF(jsonObject.toString());
//            jsonObject.clear();
//            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
//            if (jsonObject.isEmpty())
//            {
//                return new ArrayList<Visits>();
//            }
//            else
//            {
//                List<Visits> list = new ArrayList<>();
//                for (int i =0; i<jsonObject.size();i++)
//                {
//                    list.add(new Visits( (JSONObject)jsonObject.get(Integer.toString(i)) )   );
//                }
//                return list;
//            }
//
//
//        } catch (IOException e) {
//            return new ArrayList<Visits>();
//        }
//    }
//    private static List<Visits> getVisitsBYdocID(int id)
//    {
//        jsonObject.clear();
//        jsonObject.put("command", "getVisitsBydocID");
//        jsonObject.put("args",Integer.toString(id));
//
//        try {
//            outputStream.writeUTF(jsonObject.toString());
//            jsonObject.clear();
//            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
//            if (jsonObject.isEmpty())
//            {
//                return new ArrayList<Visits>();
//            }
//            else
//            {
//                List<Visits> list = new ArrayList<>();
//                for (int i =0; i<jsonObject.size();i++)
//                {
//                    list.add(new Visits( (JSONObject)jsonObject.get(Integer.toString(i)) )   );
//                }
//                return list;
//            }
//
//
//        } catch (IOException e) {
//            return new ArrayList<Visits>();
//        }
//    }
//    public static List<Visits> getVisitsBYpatID(int id)
//    {
//        jsonObject.clear();
//        jsonObject.put("command", "getVisitsBYpatID");
//        jsonObject.put("args",Integer.toString(id));
//
//        try {
//            outputStream.writeUTF(jsonObject.toString());
//            jsonObject.clear();
//            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
//            if (jsonObject.isEmpty())
//            {
//                return new ArrayList<Visits>();
//            }
//            else
//            {
//                List<Visits> list = new ArrayList<>();
//                for (int i =0; i<jsonObject.size();i++)
//                {
//                    list.add(new Visits( (JSONObject)jsonObject.get(Integer.toString(i)) )   );
//                }
//                return list;
//            }
//
//
//        } catch (IOException e) {
//            return new ArrayList<Visits>();
//        }
//    }
//    private static void insertPatient (String pesel, String firstName, String lastName, java.sql.Date dateOfBirth, String address, String city, String zipCode, String phoneNumber, String emailAddress, String Password )
//    {
//        jsonObject.clear();
//
//        jsonObject.put("command","insertPatient");
//        if (!pesel.isEmpty())jsonObject.put("pesel",pesel);
//        jsonObject.put("firstName",firstName);
//        jsonObject.put("lastName",lastName);
//        jsonObject.put("dateOfBirth",dateOfBirth);
//        jsonObject.put("address",address);
//        jsonObject.put("city",city);
//        jsonObject.put("zipCode",zipCode);
//        jsonObject.put("phoneNumber",phoneNumber);
//        jsonObject.put("emailAddress",emailAddress);
//        jsonObject.put("password",Password);
//
//        try {
//            outputStream.writeUTF(jsonObject.toString());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    private static void insertPrescription (String description,java.sql.Date dateOfIssue,int visitId)
//    {
//        jsonObject.clear();
//        jsonObject.put("command","insertPrescription");
//
//        jsonObject.put("visitId",visitId);
//        jsonObject.put("description",description);
//        jsonObject.put("dateOfIssue",dateOfIssue);
//        Random r = new Random();
//        int codeofpresc = r.nextInt(8999)+1000;
//        jsonObject.put("codeOfPrescription",codeofpresc);
//        try {
//            outputStream.writeUTF(jsonObject.toString());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    private static void insertVisit(java.sql.Date dateOfVisit,int durInmin,int patientId, int officeNumber) {
//        if (levelAccess == 2 && IDuser!=-1) {
//            jsonObject.clear();
//            jsonObject.put("command","insertVisit");
//            jsonObject.put("dateOfVisit", dateOfVisit);
//            jsonObject.put("durationInMinutes", durInmin);
//            jsonObject.put("patientId", patientId);
//            jsonObject.put("doctorId",String.valueOf(IDuser) );
//            jsonObject.put("officeNumber", officeNumber);
//        }
//    }
//    private static void deletePatient(int id) throws IOException {
//        jsonObject.clear();
//        jsonObject.put("command","deletePatient");
//        jsonObject.put("id",id);
//        outputStream.writeUTF(jsonObject.toString());
//    }
//    private static void deleteVisit(int id) throws IOException {
//        jsonObject.clear();
//        jsonObject.put("command","deleteVisit");
//        jsonObject.put("id",id);
//        outputStream.writeUTF(jsonObject.toString());
//    }
//
//
//}
