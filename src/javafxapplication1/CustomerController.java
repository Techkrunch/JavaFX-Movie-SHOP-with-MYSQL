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
public class CustomerController implements Initializable {

    @FXML
    private TextField cusid;
    @FXML
    private TextField cusname;
    @FXML
    private TextField cusbal;
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
    private void addcus(ActionEvent event) {
        if (cusid.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Customer Id is Missing!");

            cusid.requestFocus();
        } else if (cusname.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Customer Names are Missing!");
            cusname.requestFocus();
        } else if (cusbal.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Customer Balance is Missing!");
            cusbal.requestFocus();
        } else {

            try {

                conn = (Connection) db.Db();

                String sql = "insert into customers (id,name,balance ) values (?,?,?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1, cusid.getText());
                pst.setString(2, cusname.getText());
                pst.setString(3, cusbal.getText());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Customer Details Saved!");
                pst.close();
                conn.close();
                refresh();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    @FXML
    private void exibtn(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Administration Menu");
        app.show();
    }

    private void refresh() {
        cusid.setText("");
        cusname.setText("");
        cusbal.setText("");
    }

      
}
