package gui;

import database.DBconfig.DBAPI;
import database.tables.Patients;
import database.tables.Visits;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class VerificationPrescriptionController {

    @FXML
    private Button confirm;

    @FXML
    private TextField pesel;

    @FXML
    void confirmClick(ActionEvent event) throws IOException {
        int id = dane.idPatientVisit;
        List<Visits> visits;
        Patients patient;
        DBAPI dbapi = new DBAPI();
        patient = dbapi.getPatientsByID(id);
        visits = dbapi.getVisitsComing(id);


        if(pesel.getText().equals(patient.getPesel())){
            Parent loginFormParent = FXMLLoader.load(getClass().getResource("new_prescription.fxml"));
            Scene loginFormScene = new Scene(loginFormParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(loginFormScene);
            window.setTitle("Nowa recepta");
            System.out.println(window.getTitle());
            window.show();

        }

    }

}
