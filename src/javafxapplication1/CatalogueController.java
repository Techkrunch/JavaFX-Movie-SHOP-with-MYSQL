/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ian
 */
public class CatalogueController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void allmovies(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("All Movies.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("All Movies");
        app.show();
    }

    @FXML
    private void availablemovies(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("Available Movies.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Available Movies");
        app.show();
    }

    @FXML
    private void moviebygenres(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("Genres.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Movies by Genre");
        app.show();
    }

    @FXML
    private void moviesbyyear(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("Year.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Movies by Year");
        app.show();
    }

    @FXML
    private void rentamovie(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("Rent.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Rent a Movie");
        app.show();
    }

    @FXML
    private void returnamovie(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("Return.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Return a Movie");
        app.show();
    }

    @FXML
    private void exit(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("Main Menu.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Movie Kiosk - Main Menu");

        app.show();
    }

}
