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
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ian
 */
public class TopupController implements Initializable {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    private TextField cusid;
    @FXML
    private TextField topup;
    @FXML
    private Label status;
    @FXML
    private Button btntopup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        TextField cusid, topup = new TextField();

        ChangeListener<String> forceNumberListener = (observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                ((StringProperty) observable).set(oldValue);
            }
        };

        cusid.textProperty().addListener(forceNumberListener);
        topup.textProperty().addListener(forceNumberListener);

        btntopup.setDisable(true); // Initally text box was empty so button was disable
        cusid.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (cusid.getLength() > 0) {
                    if (cusid.equals("")) {
                        btntopup.setDisable(true);
                    } else {
                        btntopup.setDisable(false);
                    }
                } else {
                    btntopup.setDisable(true);

                }
            }
        });

    }

    @FXML
    private void btntopup(ActionEvent event) {
        String sql = "SELECT * from customers where id=?";
        try {
            conn = db.Db();
            pst = conn.prepareStatement(sql);
            pst.setString(1, cusid.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                //  JOptionPane.showMessageDialog(null, "found");
                int cus_bal = Integer.parseInt(rs.getString("balance"));
                int newbal = Integer.parseInt(topup.getText());
                int updatable_bal = newbal + cus_bal;

                String sqlaa = "update customers set balance = '" + updatable_bal + "' where id = '" + cusid.getText() + "'";
                pst = conn.prepareStatement(sqlaa);
                pst.execute();
                status.setText("Transaction Complete.");
                pst.close();

            } else {
                status.setText("Invalid Customer ID");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }

    }

    @FXML
    private void btnclose(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("Main Menu.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Movie Kiosk - Main Menu");

        app.show();
    }

    @FXML
    private void typesdaaa(KeyEvent event) {
    }

}
