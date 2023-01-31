package gui;

import database.DBconfig.DBAPI;
import database.tables.Patients;
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

public class VerificationEditController {

    @FXML
    private Button confirm;

    @FXML
    private TextField pesel;

    @FXML
    void confirmClick(ActionEvent event) throws IOException {
        int id = dane.idPatientVisit;
        Patients patient;
        DBAPI dbapi = new DBAPI();
        patient = dbapi.getPatientsByID(id);


        if(pesel.getText().equals(patient.getPesel())){
            Parent loginFormParent = FXMLLoader.load(getClass().getResource("new_visits.fxml"));
            Scene loginFormScene = new Scene(loginFormParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(loginFormScene);
            window.setTitle("Nowa wizyta");
            System.out.println(window.getTitle());
            window.show();

        }

    }

}
