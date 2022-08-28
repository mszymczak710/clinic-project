package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
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
    void addVisitClick(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23);
        SpinnerValueFactory<Integer> valueFactoryMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59);
        valueFactory.setValue(0);
        valueFactoryMin.setValue(0);
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
