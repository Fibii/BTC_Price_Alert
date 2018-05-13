package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.net.URI;

public class MainGui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override public void start(Stage stage){

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/main.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Btc price alert");
            stage.getIcons().add(new Image("/icon.png"));
            stage.setResizable(false);
            stage.show();


        } catch (Exception e){
            e.printStackTrace();
        }
        }
}
