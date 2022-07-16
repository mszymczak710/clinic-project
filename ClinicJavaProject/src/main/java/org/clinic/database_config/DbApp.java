package org.clinic.database_config;

public class DbApp {
    public static void main(String[] args) {
        DatabaseOperations dbOperations = new DatabaseOperations();
        dbOperations.insertOperation();
        dbOperations.selectOperation();
        dbOperations.updateOperation();
        dbOperations.deleteOperation();
    }
}
