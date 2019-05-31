/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class AdminController implements Initializable {

//    Connection conn = null;
//    PreparedStatement pst = null;
//    ResultSet rs = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        conn = db.Db();
    }

    @FXML
    private void exibtn(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("Main Menu.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Movie Kiosk - Main Menu");
        app.show();
    }

    @FXML
    private void removemovie(ActionEvent event) throws IOException {
         Parent x = FXMLLoader.load(getClass().getResource("MovieRemove.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Remove Customer");
        app.show();
    }

    @FXML
    private void Addmovies(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("Movie.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Add Movie");
        app.show();
    }

    @FXML
    private void Viewallmovies(ActionEvent event) throws IOException {
    }

    @FXML
    private void removecustomer(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("CustomerRemove.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Remove Customer");
        app.show();
    }

    @FXML
    private void Addcustomer(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("Customer.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Add Customer");
        app.show();
    }

    @FXML
    private void ViewAll(ActionEvent event) throws IOException {
    }

}
