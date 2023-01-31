package gui;

import database.DBconfig.DBAPI;
import database.tables.Patients;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
        dbapi.deletePatient(dane.idPatientVisit);
    }

    @FXML
    void editPatientClick(ActionEvent event) {
        DBAPI dbapi = new DBAPI();
        Patients patient;
        int id = dane.idPatientVisit;
        patient = dbapi.getPatientsByID(id);
        int check = 0;
        patient.setPatientId(id);
        patient.setFirstName(namePatient.getText());
        patient.setLastName(surnamePatient.getText());
        patient.setPesel(pesel.getText());
        patient.setPhoneNumber(phoneNumber.getText());
        patient.setAddress(address.getText());
        patient.setCity(city.getText());
        patient.setZipCode(zipcode.getText());
        patient.setEmailAddress(email.getText());
        patient.setEmailAddress(email.getText());
        patient.setPassword(password.getText());
        patient.setDateOfBirth(Date.valueOf(bday.getValue()));

//        if(!(namePatient.getText().trim().isEmpty())){
//            patient.setFirstName(namePatient.getText());
//            check = 5;
//        }
//        if(!(surnamePatient.getText().trim().isEmpty())){
//            patient.setLastName(surnamePatient.getText());
//            check = 5;
//        }
//        if(!(pesel.getText().trim().isEmpty())){
//            patient.setPesel(pesel.getText());
//            check = 5;
//        }
//        if(!(phoneNumber.getText().trim().isEmpty())){
//            patient.setPhoneNumber(phoneNumber.getText());
//            check = 5;
//        }
//        if(!(address.getText().trim().isEmpty())){
//            patient.setAddress(address.getText());
//            check = 5;
//        }
//        if(!(city.getText().trim().isEmpty())){
//            patient.setCity(city.getText());
//            check = 5;
//        }
//        if(!(zipcode.getText().trim().isEmpty())){
//            patient.setZipCode(zipcode.getText());
//            check = 5;
//        }
//        if(!(email.getText().trim().isEmpty())){
//            patient.setEmailAddress(email.getText());
//            check = 5;
//        }
//        if(!(password.getText().trim().isEmpty())){
//            patient.setPassword(password.getText());
//            check = 5;
//        }
//        if(!(bday.getValue().toString().isEmpty())){
//            patient.setDateOfBirth(Date.valueOf(bday.getValue()));
//        }
//        if (check == 5) {
//            DBAPI dbapi1 = new DBAPI();
//            dbapi1.updatePatient(id, patient);
//        }
        DBAPI dbapi1 = new DBAPI();
        dbapi1.updatePatient(id, patient);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Zaaktualizowano dane pacjenta");
        alert.show();

    }

    @FXML
    void onExitAppClick(ActionEvent event) {
        Stage exit = (Stage) exitApp.getScene().getWindow();
        exit.close();

    }

    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    @FXML
    public void initialize() {
        DBAPI dbapi = new DBAPI();
        Patients patient;
        int id = dane.idPatientVisit;
        patient = dbapi.getPatientsByID(id);

        namePatient.setText(patient.getFirstName());
        surnamePatient.setText(patient.getLastName());
        pesel.setText(patient.getPesel());
        phoneNumber.setText(patient.getPhoneNumber());
        address.setText(patient.getAddress());
        city.setText(patient.getCity());
        zipcode.setText(patient.getZipCode());
        email.setText(patient.getEmailAddress());
        password.setText(patient.getPassword());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(patient.getDateOfBirth());
        System.out.println(strDate);
        bday.setValue(LOCAL_DATE(strDate));
    }

}
