package gui;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;

// client pobiera zapytania z bazy i wysyla komendy do watku serwera
public class ClientAPI {
    Socket socket;
    String clientMessage;
    ObjectNode jsonNode;
    BufferedReader inputStream ;
    PrintStream outputStream ;
}
