package org.clinic.database.functions;

import org.clinic.database.functions.DatabaseOperations;

public class DbApp {
    public static void main(String[] args) {
        DatabaseOperations dbOperations = new DatabaseOperations();
        dbOperations.insertOperation();
       // dbOperations.selectOperation();
       // dbOperations.updateOperation();
       // dbOperations.deleteOperation();
    }
}
