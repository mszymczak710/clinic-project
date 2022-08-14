package database.DBconfig;

import database.DBconfig.DBOperations;

public class DBAPP {

    public static void main(String[] args) {
        DBOperations dbOperations= new DBOperations();
       dbOperations.PatientInsertOperation();
        //dbOperations.PatientupdateOperation(1);
        System.out.println("select----------------------------");
        //dbOperations.PatientSelectOperation(1);
        //dbOperations.PatientdeleteOperation(5);

    }

}
