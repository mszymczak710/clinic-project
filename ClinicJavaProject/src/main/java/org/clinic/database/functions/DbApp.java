package org.clinic.database.functions;


public class DbApp {
    public static void main(String[] args) {


        DatabaseOperations dbOperations = new DatabaseOperations();
        System.out.println("1");
        dbOperations.insertOperation();
        System.out.println("999");

        dbOperations.selectOperation();
        dbOperations.updateOperation();
      //  dbOperations.deleteOperation();


    }
}
