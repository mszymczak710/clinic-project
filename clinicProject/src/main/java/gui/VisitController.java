package gui;

import database.DBconfig.DBAPI;
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

import java.sql.Timestamp;
import java.util.List;

public class VisitController {
    ObservableList<Visits> list2 = FXCollections.observableArrayList();
    @FXML
    protected TableView<Visits> tableVisit;
    @FXML
    private TableColumn<Visits, Timestamp> dataVisitCol;

    @FXML
    private TableColumn<Visits, Integer> idVisitCol;


    @FXML
    private TableColumn<Visits, Integer> officeVisitCol;
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
    private Button searchVisit;
    @FXML
    void searchVisitClick(ActionEvent event) {

    }

    @FXML
    public void initialize() {
        list2.clear();
        DBAPI dbapi = new DBAPI();
        List<Visits> listVisits;
        System.out.println("ID:");
        System.out.println(dane.idUser);
        if(dane.idUser == 1) {
            listVisits = dbapi.getVisitsBYpatID(dane.idUser);
        }
        else{
            listVisits = dbapi.getVisitsBYdocID(dane.idUser);
        }
        list2.addAll(listVisits);
        System.out.println(listVisits.toString());
        idVisitCol.setCellValueFactory(new PropertyValueFactory<Visits, Integer>("visitId"));
        dataVisitCol.setCellValueFactory(new PropertyValueFactory<Visits, java.sql.Timestamp>("dateOfVisit"));
        officeVisitCol.setCellValueFactory(new PropertyValueFactory<Visits, Integer>("officeNumber"));

        tableVisit.setItems(list2);

    }
}
