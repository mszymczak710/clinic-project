package org.przychodnia.serwer;
// polaczenie serwer klient
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.przychodnia.DBconfig.Doctor;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable{

    Socket gniazdo;
    public ServerThread(Socket gniazdo) {
        this.gniazdo = gniazdo;
    }
    public void run () {
        System.out.println("* ------ *");
        System.out.println("[ USLUGA ]: polaczenia z: " + gniazdo.getInetAddress() + "/" + gniazdo.getPort());

        try {
            BufferedReader strumienWe = new BufferedReader(new InputStreamReader(gniazdo.getInputStream()));
            PrintStream strumienWy = new PrintStream(gniazdo.getOutputStream());
            String JsontoClient="";

            // W TY MIEJSCU SERWER ZCZYTUJE ZAPYTANIE TYPU            jsonFromClient =strumienWe.readLine();


                // wstepnie tylko wyslanie przykladowego obiektu jako json i odczyt jego w kliencie
            System.out.println("JESTEM PRZED WYSLANIEM DO KLIENTA");
            Doctor test = new Doctor(100,"111111","2222222222");
            Doctor test2 = new Doctor(100,"3333","44444444");

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String outToClient = objectMapper.writeValueAsString(test);
                System.out.println(outToClient);
                strumienWy.println(outToClient);

                String outToClient2 = objectMapper.writeValueAsString(test2);
                System.out.println(outToClient2);
                strumienWy.println(outToClient2);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

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
