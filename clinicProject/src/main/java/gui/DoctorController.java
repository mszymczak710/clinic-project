package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

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

    @FXML
    void createVisitClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("new_visits.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Nowa wizyta");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(createVisit.getScene().getWindow());
        stage.setScene(scene);
        stage.show();

    }



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
    void onCreatePrescriptionClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("new_prescription.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();

    }



    @FXML
    void onPatientRegistrationClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("add_patient.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();

        stage.setTitle("Nowy pacjent");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(createVisit.getScene().getWindow());
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
    private TextField namePatinet;

    @FXML
    private PasswordField passwordPatient;

    @FXML
    private TextField pesel;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField surnamePatient;

    @FXML
    void addPatientClick(ActionEvent event) {
        //add patient to DB

    }

    @FXML
    void clearDataClick(ActionEvent event) {

    }

    @FXML
    private Button clearData;

    @FXML
    private Button searchVisit;

    @FXML
    private ComboBox<?> statusVisit;

    @FXML
    private TableView<?> tableVisit;


    @FXML
    void searchVisitClick(ActionEvent event) {

    }

    @FXML
    private TextField comment;

    @FXML
    private TextField medicine;

    @FXML
    private TextField patientID;

    @FXML
    private TextField pieces;

    @FXML
    void createPrescriptionClick(ActionEvent event) {

    }

    @FXML
    private ComboBox<?> listOfPatient;

    @FXML
    private Spinner<?> minInput;

    @FXML
    private ComboBox<?> room;



}
