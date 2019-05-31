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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ian
 */
public class MovieController implements Initializable {

    @FXML
    private TextField moviegenre;
    @FXML
    private TextField movieyear;
    @FXML
    private TextField movietitle;
    @FXML
    private TextField movieprice;

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void addmovie(ActionEvent event) {

        if (movietitle.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Movie Title is Missing!");
//            status1.setText("FirstName is missing");
            movietitle.requestFocus();
        } else if (movieyear.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Movie Year is Missing!");
            movieyear.requestFocus();
        } else if (moviegenre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Movie Genre is Missing!");
            moviegenre.requestFocus();
        } else if (movieprice.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Movie Price is Missing!");
            movieprice.requestFocus();
        } else {

            try {

                conn = (Connection) db.Db();

                String sql = "insert into movies (title,year,genre,price) values (?,?,?,?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1, movietitle.getText());
                pst.setString(2, movieyear.getText());
                pst.setString(3, moviegenre.getText());
                pst.setString(4, movieprice.getText());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Movie Details Saved!");
                pst.close();
                conn.close();
                refresh();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @FXML
    private void closemovies(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Administration Menu");
        app.show();
    }

    private void refresh() {
        movietitle.setText("");
        movieyear.setText("");
        moviegenre.setText("");
        movieprice.setText("");
    }
}
