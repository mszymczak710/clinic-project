package gui;

import database.tables.Visits;
import gui.ClientAPI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
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

    }

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
        ClientAPI clientAPI = new ClientAPI();
         List<Visits> list = clientAPI.getVisitsBYpatID(ClientAPI.IDuser);

        for(int i = 0; i < list.size(); i++){
              System.out.println(Arrays.toString(list.toArray()));
        }
    }

    @FXML
    private Button clearData;

    @FXML
    private Button exportPDF;

    @FXML
    private TableView<String> tableVisit;

    @FXML
    void clearDataClick(ActionEvent event) {

    }

    @FXML
    void exportPDFClick(ActionEvent event) {

    }


}