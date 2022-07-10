package org.przychodnia.serwer;
// polaczenie serwer klient
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.*;

public class UslugaHTTP implements Runnable{

    Socket gniazdo;
    JSONParser parser = new JSONParser();
    public UslugaHTTP (Socket gniazdo) {
        this.gniazdo = gniazdo;
    }
    public void run () {
        System.out.println("* ------ *");
        System.out.println("[ USLUGA ]: polaczenia z: " + gniazdo.getInetAddress() + "/" + gniazdo.getPort());
        try {
            String uC="", uP="";
            String PSresult;
            BufferedReader strumienWe = new BufferedReader(new InputStreamReader(gniazdo.getInputStream()));
            PrintStream strumienWy = new PrintStream(gniazdo.getOutputStream());

            String jsonFromClient="";
            jsonFromClient =strumienWe.readLine();
            jsonFromClient =strumienWe.readLine();


            jsonFromClient = jsonFromClient.substring(16);

            System.out.println(jsonFromClient);

            ContainerFactory containerFactory = new ContainerFactory(){ public List creatArrayContainer() { return new LinkedList(); }
                public Map createObjectContainer() {
                    return new LinkedHashMap();
                }
            };
            Map jsonMap = (Map)parser.parse(jsonFromClient, containerFactory);
            Iterator iter = jsonMap.entrySet().iterator();

            while(iter.hasNext())
            { Map.Entry entry = (Map.Entry)iter.next();
                if (iter.hasNext())  uP = (String)entry.getValue();
                else  uC = (String)entry.getValue();

            }


            PSresult=ProstyShell(uC,uP);
            JSONObject obj2 = new JSONObject();
            obj2.put("result",PSresult);
            String jsonInputString = obj2.toString();

            strumienWy.println("HTTP/1.0 200 OK\r\n");
            strumienWy.println(jsonInputString);

            gniazdo.close();
        }
        catch (Exception e) {
            System.err.println("[ USLUGA ]: Exception: " + e);
        }
        System.out.println("[ USLUGA ]: rozlaczanie: " + gniazdo.getInetAddress () + "/" + gniazdo.getPort ());
        System.out.println("* ------ *");
    }

    private String getServerResult(String userCommand, String userParams)
    {
        Stringt commandReszzzzzul="";



    }

}
