package gui;

import database.DBconfig.DBAPI;
import database.tables.Patients;
import database.tables.Visits;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ListPatientsController {

    @FXML
    private TableColumn<Patients, Date> bdayCol;

    @FXML
    private TextField id;

    @FXML
    private TableColumn<Patients, Integer> idCol;

    @FXML
    private TextField name;

    @FXML
    private TableColumn<Patients, String> nameCol;

    @FXML
    private Button newVisit;

    @FXML
    private TableView<Patients> patientsTable;

    @FXML
    private Button search;

    @FXML
    private Button edit;

    @FXML
    void editClick(ActionEvent event) throws IOException {
        if (patientsTable.getSelectionModel().getSelectedItem()!=null) {
            Patients tmp = patientsTable.getSelectionModel().getSelectedItem();
            dane.idPatientVisit = tmp.getPatientId();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("verification_edit.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();

            stage.setTitle("Edycja pacjenta");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(newVisit.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private TextField surname;

    @FXML
    private TableColumn<Patients, String> surnameCol;
    @FXML
    private Button newPrescription;
    @FXML
    void newPrescriptionClick(ActionEvent event) throws IOException {
        if (patientsTable.getSelectionModel().getSelectedItem()!=null) {
            Patients tmp = patientsTable.getSelectionModel().getSelectedItem();
            dane.idPatientVisit = tmp.getPatientId();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("verification_prescription.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();

            stage.setTitle("Edycja wizyty");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(newPrescription.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    void newVisitClick(ActionEvent event) throws IOException {
        if (patientsTable.getSelectionModel().getSelectedItem()!=null) {
            Patients tmp = patientsTable.getSelectionModel().getSelectedItem();
            dane.idPatientVisit = tmp.getPatientId();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("new_visits.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();

            stage.setTitle("Lista pacjentow");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(newVisit.getScene().getWindow());
            stage.setScene(scene);
            stage.show();


        }


    }
    @FXML
    private Button deleteVisit;

    @FXML
    void deleteVisitClick(ActionEvent event) throws IOException {
        if (patientsTable.getSelectionModel().getSelectedItem()!=null) {
            Patients tmp = patientsTable.getSelectionModel().getSelectedItem();
            dane.idPatientVisit = tmp.getPatientId();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("verification_delete.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();

            stage.setTitle("Lista pacjentow");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(deleteVisit.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    void searchClick(ActionEvent event) {
        if(!(id.getText().trim().isEmpty())){
            DBAPI dbapi = new DBAPI();
            Patients patient;
            try{
                patient = dbapi.getPatientsByID(Integer.parseInt(id.getText()));
                System.out.println(patient);
                list3.clear();
                list3.addAll(patient);
                patientsTable.setItems(list3);
            }
            catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Pacjent o podanym ID nie istnieje");
                alert.show();
                System.out.println("Something went wrong");
            }

        }
        else if(!(surname.getText().trim().isEmpty())){
            DBAPI dbapi = new DBAPI();
            List<Patients> patient;
            if(!(name.getText().trim().isEmpty())){
                patient = dbapi.getPatientsByNameSurname(name.getText(), surname.getText());
                if(patient.size() == 0){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Nie ma takiego pacjenta");
                    alert.show();
                }
            }
            else{
                patient = dbapi.getPatientsBySurname(surname.getText());
                if(patient.size() == 0){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Nie ma takiego pacjenta");
                    alert.show();
                }
            }
            list3.clear();
            list3.addAll(patient);
            patientsTable.setItems(list3);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Nie ma takiego pacjenta");
            alert.show();
            System.out.println("Something went wrong");

        }

    }
    ObservableList<Patients> list3 = FXCollections.observableArrayList();
    @FXML
    public void initialize() {
        list3.clear();
        DBAPI dbapi = new DBAPI();
        List<Patients> listPatients;

        listPatients = dbapi.getPatients();
        list3.addAll(listPatients);
        System.out.println(listPatients.toString());
        idCol.setCellValueFactory(new PropertyValueFactory<Patients, Integer>("patientId"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("lastName"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Patients, String>("firstName"));
        bdayCol.setCellValueFactory(new PropertyValueFactory<Patients, Date>("dateOfBirth"));

        patientsTable.setItems(list3);


    }

}
