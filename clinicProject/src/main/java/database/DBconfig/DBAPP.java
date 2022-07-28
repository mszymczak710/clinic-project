package database.DBconfig;

public class DBAPP {

    public static void main(String[] args) {
        DBOperations dbOperations = new DBOperations();
        dbOperations.patientInsertOperation();
        //dbOperations.patientSelectOperation(1);
        dbOperations.patientUpdateOperation(4);
    }

}
