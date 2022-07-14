package org.clinic.gui;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class JsonDemo // odbior / demo klienta
{
    final static private int PORT = 9999;
    private static Socket clientSocket;
    private static PrintWriter out;
    private static BufferedReader in;

    public static void main(String[] args) {
        try {
            clientSocket = new Socket("127.0.0.1",PORT);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println(in.readLine());
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}