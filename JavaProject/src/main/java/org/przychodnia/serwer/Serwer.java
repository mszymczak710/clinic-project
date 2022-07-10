package org.przychodnia.serwer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


public class Serwer {
    final static private int PORT = 9999;

    public static void main(String[] args) {
        ServerSocket serwer;
        try {
            System.out.println("[ SERWER ]: nasluchuje na porcie " + PORT);
            serwer = new ServerSocket(PORT);

            while (true) {
                System.out.println("[ SERWER ]: oczekiwanie na polaczenie z klientem");
                Socket gniazdo = serwer.accept();
                System.out.println("[ SERWER ]: nawiazano polaczenie z klientem");

                UslugaHTTP usluga = new UslugaHTTP(gniazdo);
                Thread watek = new Thread(usluga);
                watek.start();

            }
        } catch (SocketException e) {
            System.err.println("[ SERWER ]: ZERWANO POLACZENIE ");
            System.err.println("           SocketException: " + e);
        }

        catch (IOException e) {
            System.err.println("[ SERWER ]: IOException: " + e);
        }
    }


}