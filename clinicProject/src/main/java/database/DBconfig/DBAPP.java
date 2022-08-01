package database.DBconfig;

import database.DBconfig.DBOperations;

public class DBAPP {

    public static void main(String[] args) {
        DBOperations dbOperations= new DBOperations();
        dbOperations.patientInsertOperation();
      //  dbOperations.patientUpdateOperation(7);
        System.out.println("select----------------------------");
       // dbOperations.patientSelectOperation(7);
        //dbOperations.patientDeleteOperation(5);

    }

}
