package database.DBconfig;

import database.DBconfig.DBOperations;

public class DBAPP {

    public static void main(String[] args) {
        DBOperations dbOperations= new DBOperations();
        //dbOperations.PatientInsertOperation();
        //dbOperations.PatientSelectOperation(1);
        dbOperations.PatientupdateOperation(7);
    }

}
