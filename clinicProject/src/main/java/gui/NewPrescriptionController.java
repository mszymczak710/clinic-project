package gui;

import database.DBconfig.DBAPI;
import database.tables.Patients;
import database.tables.Prescriptions;
import database.tables.Visits;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.util.List;
import java.util.Random;

public class NewPrescriptionController {
    @FXML
    private TableColumn<Visits, java.sql.Timestamp> dataVisitCol;
    @FXML
    private TableView<Visits> tableVisit;
    @FXML
    private TableColumn<Visits, Integer> idVisitCol;

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
        if (tableVisit.getSelectionModel().getSelectedItem()!=null) {
            Visits tmp = tableVisit.getSelectionModel().getSelectedItem();
            if(!(description.getText().trim().isEmpty()) && !(Date.valueOf(expirrationDate.getValue()).toString().trim().isEmpty())){
                Prescriptions prescriptions = new Prescriptions();

                prescriptions.setVisitId(tmp.getVisitId());
                prescriptions.setDescription(description.getText());
                prescriptions.setDateOfIssue(Date.valueOf(java.time.LocalDate.now()));
                prescriptions.setExpirationDate(Date.valueOf(expirrationDate.getValue()));
                prescriptions.setPatientId(dane.idPatientVisit);
                prescriptions.setCodeOfPrescription(new Random().nextInt(9000) + 1000);

                DBAPI dbapi = new DBAPI();
                dbapi.insertPrescription(prescriptions);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Dodano recepte");
                alert.show();
            }

        }


    }
    ObservableList<Visits> list3 = FXCollections.observableArrayList();
    @FXML
    public void initialize() {
        list3.clear();
        DBAPI dbapi = new DBAPI();
        List<Visits> listVisits;

        listVisits = dbapi.getVisitsComing(dane.idPatientVisit);
        list3.addAll(listVisits);
        System.out.println(listVisits.toString());
        idVisitCol.setCellValueFactory(new PropertyValueFactory<Visits, Integer>("visitId"));
        dataVisitCol.setCellValueFactory(new PropertyValueFactory<Visits, java.sql.Timestamp>("dateOfVisit"));

        tableVisit.setItems(list3);


    }
}
