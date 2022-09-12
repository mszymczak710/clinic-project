package gui;

import database.DBconfig.DBAPI;
import database.tables.Prescriptions;
import database.tables.Visits;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

public class PrescriptionController {

    @FXML
    private Button exportPDF;

    @FXML
    protected TableView<Prescriptions> tablePrescription;
    @FXML
    private TableColumn<Prescriptions, Integer> codePrescriptionCol;
    @FXML
    private TableColumn<Prescriptions, java.sql.Timestamp>dataPrescriptionCol;
    @FXML
    private Button clearData;
    @FXML
    private Button exitApp;
    @FXML
    void onExitAppClick(ActionEvent event) {
        Stage exit = (Stage) exitApp.getScene().getWindow();
        exit.close();
    }
    @FXML
    void clearDataClick(ActionEvent event) {

    }
    @FXML
    void exportPDFClick(ActionEvent event) {

    }

    ObservableList<Prescriptions> list3 = FXCollections.observableArrayList();


    @FXML
    public void initialize(){
        list3.clear();
        DBAPI dbapi = new DBAPI();
        List<Prescriptions> listPrescriptions;
        listPrescriptions = dbapi.getPrescriptionsBYpatientID(dane.idUser);
        System.out.println(listPrescriptions.toString());
        list3.addAll(listPrescriptions);
        System.out.println("Hello");
        codePrescriptionCol.setCellValueFactory(new PropertyValueFactory<Prescriptions, Integer>("codeOfPrescription"));
        dataPrescriptionCol.setCellValueFactory(new PropertyValueFactory<Prescriptions, java.sql.Timestamp>("expirationDate"));

        tablePrescription.setItems(list3);
    }
}
