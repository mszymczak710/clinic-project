package gui;

import database.DBconfig.DBAPI;
import database.tables.Prescriptions;
import database.tables.Visits;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PatientController {

    @FXML
    private Button exitApp;

    @FXML
    private Button myPrescription;

    @FXML
    private Button myVisits;

    @FXML
    void onExitAppClick(ActionEvent event) {
        Stage exit = (Stage) exitApp.getScene().getWindow();
        exit.close();

    }



    @FXML
    void onMyPrescriptionAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("my_prescription.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();

        stage.setTitle("Moje recepty");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(myPrescription.getScene().getWindow());
        stage.setScene(scene);
        stage.show();

//        try{
//            list2.clear();
//            DBAPI dbapi = new DBAPI();
//            List<Prescriptions> listPrescriptions;
//            listPrescriptions = dbapi.getPrescriptionsBYpatientID(1);
//            System.out.println(listPrescriptions.toString());
//            list3.addAll(listPrescriptions);
//            System.out.println("Hello");
//            codePrescriptionCol.setCellValueFactory(new PropertyValueFactory<Prescriptions, Integer>("codeOfPrescription"));
//            dataPrescriptionCol.setCellValueFactory(new PropertyValueFactory<Prescriptions, java.sql.Timestamp>("dateOfIssue"));
//
//            tablePrescription.setItems(list3);
//
//        }catch (Exception e) {
//
//        }

    }

    ObservableList<Prescriptions> list3 = FXCollections.observableArrayList();

    @FXML
    void onMyVisitsClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("my_visits.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();

        stage.setTitle("Moje wizyty");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(myVisits.getScene().getWindow());
        stage.setScene(scene);
        stage.show();

//        ClientAPI clientAPI = new ClientAPI();
//        List<Visits> list = clientAPI.getVisitsBYpatID(ClientAPI.IDuser);
//
//        for(int i = 0; i < list.size(); i++){
//            System.out.println(Arrays.toString(list.toArray()));
//
//        }
//        try{
//            list2.clear();
//            DBAPI dbapi = new DBAPI();
//            List<Visits> listVisits;
//            listVisits = dbapi.getVisitsBYpatID(4);
//            list2.addAll(listVisits);
//            System.out.println(listVisits.toString());
//            idVisitCol.setCellValueFactory(new PropertyValueFactory<Visits, Integer>("visitId"));
//            dataVisitCol.setCellValueFactory(new PropertyValueFactory<Visits, java.sql.Timestamp>("dateOfVisit"));
//            officeVisitCol.setCellValueFactory(new PropertyValueFactory<Visits, Integer>("officeNumber"));
//
//            tableVisit.setItems(list2);
//
//        }catch (Exception e) {
//
//        }



//        System.out.println(listVisits.size());
//        for(int i = 0; i < listVisits.size(); i++){
//            System.out.println(list2.get(0));
//        }


    }
}
