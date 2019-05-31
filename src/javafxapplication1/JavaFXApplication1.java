/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.awt.Image;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author ian
 */
public class JavaFXApplication1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Main Menu.fxml"));
//            Parent root = FXMLLoader.load(getClass().getResource("MovieRemove.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setTitle("Movie Kiosk - Main Menu");
//primaryStage.titleProperty(frame);
            primaryStage.setScene(scene);
//            ((Stage) scene.getWindow()).getIcons().add(new Image(JavaFXApplication1.class.getResourceAsStream("../images/icon.png")) {});
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
