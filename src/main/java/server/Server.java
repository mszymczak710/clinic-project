package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    /*ZAMYSŁ
       DO SERWERA TRAFIA JSON Z KOMENDĄ
       SERWER ODSYLA DO KLIENTA JSONA Z LISTĄ (OBIEKTOW Z BAZY)**/
    final static private int PORT = 9999;

    public static void main(String[] args) {
        ServerSocket server;

        try {
            System.out.println("[ Serwer ]: nasluchuje na porcie " + PORT);
            server = new ServerSocket(PORT);

            while (true) {
                System.out.println("[ Serwer ]: oczekiwanie na polaczenie z klientem");
                Socket socket = server.accept();
                System.out.println("[ Serwer ]: nawiazano polaczenie z klientem");

                ServerThread service = new ServerThread(socket);
                Thread thread = new Thread(service);
                thread.start();

            }
        } catch (SocketException e) {
            System.err.println("[ Serwer ]: ZERWANO POLACZENIE ");
            System.err.println("           SocketException: " + e);
        }

        catch (IOException e) {
            System.err.println("[ Serwer ]: IOException: " + e);
        }
    }


}