package org.przychodnia.serwer;
// polaczenie serwer klient
import java.io.*;
import java.net.Socket;

public class UslugaHTTP implements Runnable{

    Socket gniazdo;
    public UslugaHTTP (Socket gniazdo) {
        this.gniazdo = gniazdo;
    }
    public void run () {
        System.out.println("* ------ *");
        System.out.println("[ USLUGA ]: polaczenia z: " + gniazdo.getInetAddress() + "/" + gniazdo.getPort());
        try {
            BufferedReader strumienWe = new BufferedReader(new InputStreamReader(gniazdo.getInputStream()));
            PrintStream strumienWy = new PrintStream(gniazdo.getOutputStream());
            String JsontoClient="";

                // wstepnie tylko wyslanie przykladowego obiektu jako json i odczyt jego w kliencie
            JsonDemo.JsonDemoSerwer();



            strumienWy.println("HTTP/1.0 200 OK\r\n");
            strumienWy.println( JsontoClient);

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
        return "dupa";


    }

}
