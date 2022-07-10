package org.przychodnia.serwer;

import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonDemo {

    public static  void JsonDemoSerwer()
    {

        Doctor test = new Doctor(100,"japa","dupa");
        ObjectMapper objectMapper = new ObjectMapper();


    }

    public static void main(String[] args) {
        JsonDemoSerwer();
    }
}
