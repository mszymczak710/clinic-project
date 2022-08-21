package gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.net.Socket;

// client pobiera zapytania z bazy i wysyla komendy do watku serwera
// przenosimy zapytania z api do bazy
public class ClientAPI {
    Socket socket;
    String clientMessage;
    JSONObject jsonObject;
    DataInputStream inputStream;
    DataOutputStream outputStream;
    String userName="";
    int levelAccess=0; // 0 nzal 1 pat 2 lekarz

    void login_patient (int id, String password ) throws IOException {
        jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("password",password); // niezaszyfrowane

        outputStream.writeUTF(jsonObject.toString());
        if (jsonObject.get("status") == "TRUE") {
            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
            levelAccess = (int) jsonObject.get("level");
            userName = jsonObject.get("id").toString();
        }
        else
        {
            System.out.println("nie udalo sie zalogowac");
        }

    }
    void login_doctor (int id, String password ) throws IOException {
        jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("password",password); // niezaszyfrowane

        outputStream.writeUTF(jsonObject.toString());
        if (jsonObject.get("status") == "TRUE") {
            jsonObject = (JSONObject) JSONValue.parse(inputStream.readUTF());
            levelAccess = (int) jsonObject.get("level");
            userName = jsonObject.get("id").toString();
        }
        else
        {
            System.out.println("nie udalo sie zalogowac");
        }

    }



}
