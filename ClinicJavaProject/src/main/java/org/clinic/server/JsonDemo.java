package org.clinic.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.clinic.database.tables.Doctor;

public class JsonDemo {

    public static  void JsonDemoServer() {
        Doctor test = new Doctor(100,"japa","dupa", 100, "ginekolog", false);
        ObjectMapper objectMapper = new ObjectMapper();
    }

    public static void main(String[] args) {
        JsonDemoServer();
    }
}
