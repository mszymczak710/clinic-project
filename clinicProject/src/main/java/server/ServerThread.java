package server;
// polaczenie serwer klient
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.DBconfig.DBAPI;
import database.tables.Doctors;

import java.io.*;
import java.net.Socket;
import com.fasterxml.jackson.*;
public class ServerThread implements Runnable{

     Socket socket;
    String clientMessage;
    ObjectNode jsonNode;
    BufferedReader inputStream ;
    PrintStream outputStream ;
    DBAPI dbapi;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    
    public void run () {
        System.out.println("* ------ *");
        System.out.println("[ Usluga ]: polaczenia z: " + socket.getInetAddress() + "/" + socket.getPort());

        try {
           inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputStream = new PrintStream(socket.getOutputStream());
            dbapi = new DBAPI();

            clientCommunication(); // Otwieramy mozliwosc wymieniania informacji z clientem

            outputStream.close();
            inputStream.close();
            socket.close();
        }
        catch (Exception e) {
            System.err.println("[ Usluga ]: Exception: " + e);
        }
        System.out.println("[ Usluga ]: rozlaczanie: " + socket.getInetAddress () + "/" + socket.getPort ());
        System.out.println("* ------ *");
    }

   private void clientCommunication() throws IOException;
   {
        /* czy sprawdzamy odrazu status accesu? */
       /*czy szukamy cos na pierwsze spojrzenie? */

       /*petla obslugujaca zapytania */
       while (true)
       {
           while(inputStream.n)
           clientMessage = inputStream.readLine()
       }

   }

}
