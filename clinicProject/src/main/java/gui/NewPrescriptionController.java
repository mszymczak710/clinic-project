package gui;

import database.DBconfig.DBAPI;
import database.tables.Prescriptions;
import database.tables.Visits;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.util.Random;

public class NewPrescriptionController {

    @FXML
    private TextField codePrescription;

    @FXML
    private Button createPrescription;

    @FXML
    private TextField description;

    @FXML
    private DatePicker expirrationDate;

    @FXML
    private TextField patientID;

    @FXML
    private TextField visitID;

    @FXML
    void createPrescriptionClick(ActionEvent event) {
        if(!(patientID.getText().trim().isEmpty()) && !(description.getText().trim().isEmpty()) && !(visitID.getText().trim().isEmpty()) && !(Date.valueOf(expirrationDate.getValue()).toString().trim().isEmpty())){
            Prescriptions prescriptions = new Prescriptions();
            prescriptions.setVisitId(Integer.parseInt(visitID.getText()));
            prescriptions.setDescription(description.getText());
            prescriptions.setDateOfIssue(Date.valueOf(java.time.LocalDate.now()));
            prescriptions.setExpirationDate(Date.valueOf(expirrationDate.getValue()));
            prescriptions.setPatientId(Integer.parseInt(patientID.getText()));
            prescriptions.setCodeOfPrescription(new Random().nextInt(9000) + 1000);
            DBAPI dbapi = new DBAPI();
            dbapi.insertPrescription(prescriptions);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Dodano recepte");
            alert.show();
        }
    }
}
