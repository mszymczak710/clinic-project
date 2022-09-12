package gui;

import database.DBconfig.DBAPI;
import database.tables.Patients;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Date;

public class ModifyPatientController {

    @FXML
    private TextField address;

    @FXML
    private DatePicker bday;

    @FXML
    private TextField city;

    @FXML
    private Button deletePatient;

    @FXML
    private Button editPatient;

    @FXML
    private TextField email;

    @FXML
    private Button exitApp;

    @FXML
    private TextField namePatient;

    @FXML
    private PasswordField password;

    @FXML
    private TextField patientID;

    @FXML
    private TextField pesel;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField surnamePatient;

    @FXML
    private TextField zipcode;

    @FXML
    void deletePatientClick(ActionEvent event) {
        DBAPI dbapi = new DBAPI();
        System.out.println(Integer.parseInt(patientID.getText()));
        dbapi.deletePatient(Integer.parseInt(patientID.getText()));
    }

    @FXML
    void editPatientClick(ActionEvent event) {
        DBAPI dbapi = new DBAPI();
        Patients patient;
        int id = Integer.parseInt(patientID.getText());
        patient = dbapi.getPatientsByID(id);
        int check = 0;
        if(!(namePatient.getText().trim().isEmpty())){
            patient.setFirstName(namePatient.getText());
            check = 5;
        }
        if(!(surnamePatient.getText().trim().isEmpty())){
            patient.setLastName(surnamePatient.getText());
            check = 5;
        }
        if(!(pesel.getText().trim().isEmpty())){
            patient.setPesel(pesel.getText());
            check = 5;
        }
        if(!(phoneNumber.getText().trim().isEmpty())){
            patient.setPhoneNumber(phoneNumber.getText());
            check = 5;
        }
        if(!(address.getText().trim().isEmpty())){
            patient.setAddress(address.getText());
            check = 5;
        }
        if(!(city.getText().trim().isEmpty())){
            patient.setCity(city.getText());
            check = 5;
        }
        if(!(zipcode.getText().trim().isEmpty())){
            patient.setZipCode(zipcode.getText());
            check = 5;
        }
        if(!(email.getText().trim().isEmpty())){
            patient.setEmailAddress(email.getText());
            check = 5;
        }
        if(!(password.getText().trim().isEmpty())){
            patient.setPassword(password.getText());
            check = 5;
        }
        if (check == 5) {
            DBAPI dbapi1 = new DBAPI();
            dbapi1.updatePatient(id, patient);
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Zaaktualizowano dane pacjenta");
        alert.show();

    }

    @FXML
    void onExitAppClick(ActionEvent event) {
        Stage exit = (Stage) exitApp.getScene().getWindow();
        exit.close();

    }

}
