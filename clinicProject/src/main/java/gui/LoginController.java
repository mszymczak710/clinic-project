package gui;

import database.DBconfig.DBAPI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private Label infoLogin;

    @FXML
    private Button exitApp;

    @FXML
    private Button logDoctor;

    @FXML
    private Button logPatient;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Button signIn;

    @FXML
    void getLogin(ActionEvent event) {

    }

    @FXML
    void getPassword(ActionEvent event) {

    }

    @FXML
    void onExitAppClick(ActionEvent event) {
        Stage exit = (Stage) exitApp.getScene().getWindow();
        exit.close();
    }

    @FXML
    void onSignInDoctorClick(ActionEvent event) throws IOException {
        DBAPI dbapi = new DBAPI();

        if(login.getText().trim().isEmpty() && password.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Prosze wypelnic wszystkie pola");
            alert.show();
        } else if(login.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Prosze podac login");
            alert.show();
        } else if (password.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Prosze podac haslo");
            alert.show();
        } else {
            if(dbapi.loginAsDoctor(Integer.parseInt(login.getText()), password.getText()) == true){
                Parent loginFormParent = FXMLLoader.load(getClass().getResource("doctor_panel.fxml"));
                Scene loginFormScene = new Scene(loginFormParent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(loginFormScene);
                window.setTitle("Doktor");
                System.out.println(window.getTitle());
                window.show();
            }
            else{
                login.clear();
                password.clear();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Nieprawidlowe dane");
                alert.show();
            }

        }

//        Parent loginFormParent = FXMLLoader.load(getClass().getResource("doctor_panel.fxml"));
//        Scene loginFormScene = new Scene(loginFormParent);
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        window.setScene(loginFormScene);
//        window.setTitle("Doktor");
//        System.out.println(window.getTitle());
//        window.show();
//
//        System.out.println(password.getText());
    }
    @FXML
    void onSignInPatientClick(ActionEvent event) throws IOException {
        DBAPI dbapi = new DBAPI();

        if(login.getText().trim().isEmpty() && password.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Prosze wypelnic wszystkie pola");
            alert.show();
        } else if(login.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Prosze podac login");
            alert.show();
        } else if (password.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Prosze podac haslo");
            alert.show();
        } else {
            if(dbapi.loginAsPatient(Integer.parseInt(login.getText()), password.getText()) == true){
                Parent loginFormParent = FXMLLoader.load(getClass().getResource("patient_panel.fxml"));
                Scene loginFormScene = new Scene(loginFormParent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(loginFormScene);
                window.setTitle("Pacjent");
                System.out.println(window.getTitle());
                window.show();
            }
            else{
                login.clear();
                password.clear();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Nieprawidlowe dane");
                alert.show();
            }

        }
        System.out.println(password.getText());
    }


    public void onLogDoctorAction(ActionEvent event) throws IOException {
        Parent loginFormParent = FXMLLoader.load(getClass().getResource("doctor_login.fxml"));
        Scene loginFormScene = new Scene(loginFormParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginFormScene);
        window.show();

    }


    public void onLogPatientAction(ActionEvent event) throws IOException {
        Parent loginFormParent = FXMLLoader.load(getClass().getResource("patient_login.fxml"));
        Scene loginFormScene = new Scene(loginFormParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginFormScene);
        window.show();

    }

    @FXML
    private void initialize(URL url, ResourceBundle rb) {

    }

}
