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
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ian
 */
public class MainMenuController implements Initializable {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        conn = db.Db();
    }

    @FXML
    private void btnExploreCatalogue(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("Catalogue.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Catalogue");
        app.show();
    }

    @FXML
    private void btncustomerrecord(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("Patron.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Patron Record");
        app.show();
    }

    @FXML
    private void btntopup(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("Topup.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Account Top-up");
        app.show();
    }

    @FXML
    private void btnfavmovies(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("Favourite.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Favourites");
        app.show();
    }

    @FXML
    private void btnadmin(ActionEvent event) throws IOException, SQLException {

        Parent x = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Administration Menu");
        app.show();
    }

    @FXML
    private void btnquit(ActionEvent event) throws IOException {
        System.exit(0);
    }
}
