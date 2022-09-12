package gui;

import database.DBconfig.DBAPI;
import database.tables.Visits;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.util.List;

public class DeleteVisitController {
    ObservableList<Visits> list3 = FXCollections.observableArrayList();

    @FXML
    private Button deleteVisit;

    @FXML
    private Button exitApp;

    @FXML
    protected TableView<Visits> tableVisit;
    @FXML
    private TableColumn<Visits, Timestamp> dataVisitCol;

    @FXML
    private TableColumn<Visits, Integer> idVisitCol;
    @FXML
    private TableColumn<Visits, Integer> officeVisitCol;

    @FXML
    void deleteVisitClick(ActionEvent event) {
        if (tableVisit.getSelectionModel().getSelectedItem()!=null) {
            Visits tmp = tableVisit.getSelectionModel().getSelectedItem();
            DBAPI dbapi = new DBAPI();
            dbapi.deleteVisit(tmp.getVisitId());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Usunieto wskazana wizyte");
            alert.show();
            initialize();
        }

    }

    @FXML
    void onExitAppClick(ActionEvent event) {
        Stage exit = (Stage) exitApp.getScene().getWindow();
        exit.close();
    }

    @FXML
    public void initialize() {
        list3.clear();
        DBAPI dbapi = new DBAPI();
        List<Visits> listVisits;
        System.out.println("Typ:");
        System.out.println(dane.typ);

        listVisits = dbapi.getVisits();
        list3.addAll(listVisits);
        System.out.println(listVisits.toString());
        idVisitCol.setCellValueFactory(new PropertyValueFactory<Visits, Integer>("visitId"));
        dataVisitCol.setCellValueFactory(new PropertyValueFactory<Visits, java.sql.Timestamp>("dateOfVisit"));
        officeVisitCol.setCellValueFactory(new PropertyValueFactory<Visits, Integer>("officeNumber"));



        tableVisit.setItems(list3);


    }

}
