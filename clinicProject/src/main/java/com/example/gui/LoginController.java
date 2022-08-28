package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

        if (!password.getText().equals("bazyl")) {
            infoLogin.setText("Your password is incorrect!");
            password.clear();
        } else {
            infoLogin.setText("Your password has been confirmed");

            Parent loginFormParent = FXMLLoader.load(getClass().getResource("doctor_panel.fxml"));
            Scene loginFormScene = new Scene(loginFormParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(loginFormScene);
            window.setTitle("Doktor");
            System.out.println(window.getTitle());
            window.show();

        }
        System.out.println(password.getText());


    }
    @FXML
    void onSignInPatientClick(ActionEvent event) throws IOException {

        if (!password.getText().equals("bazyl")) {
            infoLogin.setText("Your password is incorrect!");
            password.clear();
        } else {
            infoLogin.setText("Your password has been confirmed");

            Parent loginFormParent = FXMLLoader.load(getClass().getResource("patient_panel.fxml"));
            Scene loginFormScene = new Scene(loginFormParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(loginFormScene);
            window.setTitle("Pacjent");
            System.out.println(window.getTitle());
            window.show();

        }
        System.out.println(password.getText());


    }


    public void onLogDoctorAction(ActionEvent event) throws IOException {
        Parent loginFormParent = FXMLLoader.load(getClass().getResource("doctor_login.fxml"));
        Scene loginFormScene = new Scene(loginFormParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginFormScene);
        window.show();


//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("doctor_login.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        Stage stage = new Stage();
//        LoginController c = (LoginController) fxmlLoader.getController();
//
//        stage.setTitle("Log as doctor");
//        stage.setScene(scene);
//        stage.show();
    }


    public void onLogPatientAction(ActionEvent event) throws IOException {
        Parent loginFormParent = FXMLLoader.load(getClass().getResource("patient_login.fxml"));
        Scene loginFormScene = new Scene(loginFormParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginFormScene);
        window.show();





//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("patient_login.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        Stage stage = new Stage();
//        LoginController c = (LoginController) fxmlLoader.getController();
//
//        stage.setTitle("Log as doctor");
//        stage.setScene(scene);
//        stage.show();

    }

    @FXML
    private void initialize(URL url, ResourceBundle rb) {

    }

}
