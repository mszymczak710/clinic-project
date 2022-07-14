package org.clinic.server;
// polaczenie serwer klient
import java.io.*;
import java.net.Socket;

public class ServiceHTTP implements Runnable {

    Socket socket;
    public ServiceHTTP (Socket socket) {
        this.socket = socket;
    }

    public void run () {
        System.out.println("* ------ *");
        System.out.println("[ Usluga ]: polaczenia z: " + socket.getInetAddress() + "/" + socket.getPort());

        try {
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream outputStream = new PrintStream(socket.getOutputStream());
            String JsonToClient = "";

                // wstepnie tylko wyslanie przykladowego obiektu jako json i odczyt jego w kliencie
            JsonDemo.JsonDemoServer();


            outputStream.println("HTTP/1.0 200 OK\r\n");
            outputStream.println( JsonToClient );

            socket.close();
        }
        catch (Exception e) {
            System.err.println("[ Usluga ]: Exception: " + e);
        }

        System.out.println("[ Usluga ]: rozlaczanie: " + socket.getInetAddress () + "/" + socket.getPort ());
        System.out.println("* ------ *");
    }

    private String getServerResult(String userCommand, String userParams){
        return "dupa";
    }

}
