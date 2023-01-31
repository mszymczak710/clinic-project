package gui;
import database.DBconfig.DBAPI;
import database.tables.Doctors;
import database.tables.Visits;
import gui.dane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.print.Doc;
import java.sql.Timestamp;
import java.util.List;

public class RaportsController {

    @FXML
    private TableColumn<Visits, java.sql.Timestamp> dateCol;

    @FXML
    private DatePicker dateVisit;

    @FXML
    private ComboBox<String> doctors;

    @FXML
    private TableColumn<Visits, Integer> durationCol;

    @FXML
    private TableColumn<Visits, Integer> officeCol;

    @FXML
    private Button search;

    @FXML
    void onSearchClick(ActionEvent event) {
        list2.clear();
        DBAPI dbapi = new DBAPI();


        List<Visits> listVisits;
        System.out.println("Typ:");
        System.out.println(dane.typ);

        java.sql.Date sqlDate =java.sql.Date.valueOf(dateVisit.getValue());
        System.out.println(doctors.getValue().toString());

        int id = Character.getNumericValue(doctors.getValue().toString().charAt(0));
        System.out.println("ID");

        System.out.println(id);

        String modyfikacja = String.valueOf(sqlDate);
        modyfikacja += " 00:00:00";
        System.out.println(modyfikacja);

        listVisits = dbapi.getVisitsBYdocIDDay(id, Timestamp.valueOf(modyfikacja));


        list2.addAll(listVisits);
        System.out.println(listVisits.toString());
        durationCol.setCellValueFactory(new PropertyValueFactory<Visits, Integer>("durationInMinutes"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Visits, java.sql.Timestamp>("dateOfVisit"));
        officeCol.setCellValueFactory(new PropertyValueFactory<Visits, Integer>("officeNumber"));

        tableVisit.setItems(list2);


    }

    @FXML
    private TableView<Visits> tableVisit;

    ObservableList<Visits> list2 = FXCollections.observableArrayList();
    @FXML
    public void initialize() {
        DBAPI dbapi = new DBAPI();
        List<Doctors> doctorsList = dbapi.getDoctors();

        for(int i = 0; i < doctorsList.size(); i++){
            String tmp = new String();
            tmp = String.valueOf(doctorsList.get(i).getDoctorId());
            tmp += ' ';
            tmp += doctorsList.get(i).getFirstName() + ' ' + doctorsList.get(i).getLastName();
            doctors.getItems().addAll(tmp);
        }
    }

}
