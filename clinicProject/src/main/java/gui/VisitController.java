package gui;

import database.DBconfig.DBAPI;
import database.tables.Visits;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
        for(int i = 0; i < tableVisit.getItems().size(); i++){
            tableVisit.getItems().clear();
        }
        statusVisit.getSelectionModel().clearSelection();
    }
    @FXML
    private Button searchVisit;
    @FXML
    void searchVisitClick(ActionEvent event) {
        if(statusVisit.getValue().equals("coming")){
            System.out.println("comming");
            DBAPI dbapi = new DBAPI();
            List<Visits> list;
            list = dbapi.getVisitsComing(dane.idUser);
            System.out.println(list.toString());

        } else if (statusVisit.getValue().equals("ended")) {
            System.out.println("ended");
            DBAPI dbapi = new DBAPI();
            List<Visits> list;
            list = dbapi.getVisitsEnded(dane.idUser);
            System.out.println(list.toString());

        }
        else {
            System.out.println("Set value of combobox");
        }


    }

    @FXML
    private ComboBox<String> statusVisit;

    @FXML
    public void initialize() {
        list2.clear();
        DBAPI dbapi = new DBAPI();
        List<Visits> listVisits;
        System.out.println("Typ:");
        System.out.println(dane.typ);
        if(dane.typ == 1) {
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
        statusVisit.getItems().addAll("coming", "ended");


        tableVisit.setItems(list2);


    }
}
