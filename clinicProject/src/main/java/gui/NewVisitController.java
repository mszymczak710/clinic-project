package gui;

import database.DBconfig.DBAPI;
import database.tables.Offices;
import database.tables.Patients;
import database.tables.Visits;
import jakarta.persistence.criteria.CriteriaBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class NewVisitController implements Initializable {

    @FXML
    private Button addVisit;

    @FXML
    private DatePicker dataVisit;

    @FXML
    private Button exitApp;

    @FXML
    private Spinner<Integer> hourInput;

    @FXML
    private Spinner<Integer> minInput;
    @FXML
    private Spinner<Integer> durationTime;

    @FXML
    private ComboBox<Integer> office;

    @FXML
    private Label name_surname_patient;

    @FXML
    void addVisitClick(ActionEvent event) {

        if(!(Date.valueOf(dataVisit.getValue()).toString().isEmpty()) && (minInput.getValue() > 0 || hourInput.getValue() > 0) && (durationTime.getValue() > 0)){
            Visits visit = new Visits();
            String output = new String();
            output += dataVisit.getValue().toString() + " ";
            output += ((hourInput.getValue() < 10) ? "0" + hourInput.getValue() : hourInput.getValue()) + ":" + ((minInput.getValue() < 10) ? "0" + minInput.getValue() : minInput.getValue());
            output += ":00";
            visit.setDateOfVisit(Timestamp.valueOf(output));
            visit.setPatientId(dane.idPatientVisit);
            visit.setDurationInMinutes(durationTime.getValue());
            visit.setOfficeNumber(office.getValue());
            visit.setDoctorId(dane.idUser);

            DBAPI dbapi = new DBAPI();
            dbapi.insertVisit(visit);
            System.out.println(visit);


        }
        else{
            System.out.println("Wypelnij wszystkie pola");
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int id = dane.idPatientVisit;
        Patients patient;
        DBAPI dbapi = new DBAPI();
        patient = dbapi.getPatientsByID(id);


        name_surname_patient.setText(patient.getFirstName() + ' ' + patient.getLastName());

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23);
        SpinnerValueFactory<Integer> valueFactoryMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59);
        valueFactory.setValue(0);
        valueFactoryMin.setValue(0);
        SpinnerValueFactory<Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,120);
        valueFactory1.setValue(0);
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("\\d*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter1 = new TextFormatter<>(filter);
        TextFormatter<String> textFormatter2 = new TextFormatter<>(filter);
        hourInput.getEditor().setTextFormatter(textFormatter1);
        minInput.getEditor().setTextFormatter(textFormatter2);
        hourInput.setValueFactory(valueFactory);
        minInput.setValueFactory(valueFactoryMin);
        durationTime.setValueFactory(valueFactory1);

        List<Offices> offices;
        offices = dbapi.getOffices();
        ObservableList<Offices> list = FXCollections.observableArrayList(offices);

        for(int i = 0; i < list.size(); i++){
            office.getItems().add(list.get(i).getOfficeNumber());
        }


    }
    @FXML
    void onExitAppClick(ActionEvent event) {
        Stage exit = (Stage) exitApp.getScene().getWindow();
        exit.close();

    }



    @FXML
    void setDataVisit(ActionEvent event) {

    }

}
