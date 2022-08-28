package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception{
//        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("login_panel.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
//        stage.setTitle("Login");
//        stage.setScene(scene);
//        stage.show();


//        Parent root = FXMLLoader.load(getClass().getResource("login_panel.fxml"));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();

        try{
            Parent root = FXMLLoader.load(getClass().getResource("login_panel.fxml"));
            Scene login_scene = new Scene(root);
            stage.setScene(login_scene);
            stage.show();
        } catch (Exception e){ 
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        launch();
    }
}
