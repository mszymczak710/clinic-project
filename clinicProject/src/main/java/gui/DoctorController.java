package gui;

import database.DBconfig.DBAPI;
import database.tables.Patients;
import database.tables.Visits;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

public class DoctorController {

    @FXML
    private Button createPrescription;

    @FXML
    private Button createVisit;

    @FXML
    private Button exitApp;

    @FXML
    private Button myVisits;

    @FXML
    private Button patientRegistration;

    @FXML
    private Button addVisit;

    @FXML
    private DatePicker dataVisit;


    @FXML
    void addVisitClick(ActionEvent event) {
    //add visit to DB
    }

    @FXML
    void onExitAppClick(ActionEvent event) {
        Stage exit = (Stage) exitApp.getScene().getWindow();
        exit.close();

    }

    @FXML
    void setDataVisit(ActionEvent event) {}

//    @FXML
//    void createVisitClick(ActionEvent event) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("new_visits.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        Stage stage = new Stage();
//        stage.setTitle("Nowa wizyta");
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.initOwner(createVisit.getScene().getWindow());
//        stage.setScene(scene);
//        stage.show();
//
//    }



    @FXML
    void myVisitsClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("my_visits.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();

        stage.setTitle("Moje wizyty");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(myVisits.getScene().getWindow());
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    void onPatientRegistrationClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("add_patient.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();

        stage.setTitle("Nowy pacjent");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(patientRegistration.getScene().getWindow());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private Button addPatient;

    @FXML
    private TextField address;

    @FXML
    private DatePicker bday;

    @FXML
    private Button clear;


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
    private Button editPatient;

    @FXML
    private Button deleteVisit;


//    @FXML
//    void deleteVisitClick(ActionEvent event) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("delete_visits.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        Stage stage = new Stage();
//
//        stage.setTitle("Edycja wizyty");
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.initOwner(myVisits.getScene().getWindow());
//        stage.setScene(scene);
//        stage.show();
//
//    }

    @FXML
    void addPatientClick(ActionEvent event) {
        if(!(namePatient.getText().trim().isEmpty()) && !(surnamePatient.getText().trim().isEmpty()) && !(pesel.getText().trim().isEmpty()) && !(phoneNumber.getText().trim().isEmpty()) && !(Date.valueOf(bday.getValue()).toString().trim().isEmpty()) && !(address.getText().trim().isEmpty()) && !(city.getText().trim().isEmpty()) && !(zipcode.getText().trim().isEmpty()) && !(email.getText().trim().isEmpty()) && !(password.getText().trim().isEmpty())){
            Patients patient = new Patients();
            patient.setPatientId(30);
            patient.setFirstName(namePatient.getText());
            patient.setLastName(surnamePatient.getText());
            patient.setPesel(pesel.getText());
            patient.setDateOfBirth(Date.valueOf(bday.getValue()));
            patient.setPhoneNumber(phoneNumber.getText());
            patient.setAddress(address.getText());
            patient.setCity(city.getText());
            patient.setZipCode(zipcode.getText());
            patient.setEmailAddress(email.getText());


            patient.setPassword(password.getText());

            DBAPI dbapi = new DBAPI();
            dbapi.insertPatient(patient);
            clear_values();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Dodano nowego pacjenta");
            alert.show();
        }
        else{
            System.out.println("Wypelnij wszystkie pola");
        }

    }
    void clear_values(){
        namePatient.clear();
        surnamePatient.clear();
        pesel.clear();
        bday.setValue(null);
        phoneNumber.clear();
        address.clear();
        city.clear();
        zipcode.clear();
        email.clear();
        password.clear();

    }
    @FXML
    void clearDataClick(ActionEvent event) {
        clear_values();

    }

    @FXML
    private Button raports;

    @FXML
    private Button listOfPatient;

    @FXML
    void raportsClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("raports.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();

        stage.setTitle("Lista pacjentow");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(raports.getScene().getWindow());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void listOfPatientClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("listPatients.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();

        stage.setTitle("Lista pacjentow");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(myVisits.getScene().getWindow());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private Button clearData;

    @FXML
    private Button searchVisit;

    @FXML
    private ComboBox<?> statusVisit;

    @FXML
    private TableView<Visits> tableVisit;
    @FXML
    private TableColumn<Visits, String> dataVisitCol;

    @FXML
    private TableColumn<Visits, Integer> idVisitCol;


    @FXML
    private TableColumn<Visits, Integer> officeVisitCol;


//    @FXML
//    void searchVisitClick(ActionEvent event) {
//
//    }

    @FXML
    private TextField comment;

    @FXML
    private TextField medicine;

    @FXML
    private TextField patientID;

    @FXML
    private TextField pieces;

//    @FXML
//    void onCreatePrescriptionClick(ActionEvent event) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("new_prescription.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        Stage stage = new Stage();
//
//        stage.setTitle("Edycja wizyty");
//        stage.initModality(Modality.WINDOW_MODAL);
//        stage.initOwner(myVisits.getScene().getWindow());
//        stage.setScene(scene);
//        stage.show();
//
//    }

    @FXML
    private TextField zipcode;
    @FXML
    private TextField city;
    @FXML
    private TextField email;




}
