/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ian
 */
public class PatronController implements Initializable {

    private ObservableList<ObservableList> data;

    @FXML
    private TextField search;
    @FXML
    private Label status;
    @FXML
    private TableView tablerentedmovies;
    @FXML
    private TableView tablerentedhistory;
    @FXML
    private Button btnselectcus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnselectcus.setDisable(true); // Initally text box was empty so button was disable
        search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (search.getLength() > 0) {

                    if (search.equals("")) {
                        btnselectcus.setDisable(true);
                    } else {
                        btnselectcus.setDisable(false);
                    }
                } else {
                btnselectcus.setDisable(true);
                }
            }
        });
    }

    @FXML
    private void btnselectcus(ActionEvent event) {
        if (search.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter a valid Customer Id!");
            search.requestFocus();
        } else {
            status.setText("");
            table1();
            table2();
            getdesc();
        }
    }

    private void getdesc() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = (Connection) db.Db();
            String sql = "select * from  customers where id = '" + search.getText() + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String add1 = rs.getString("id");
                String add2 = rs.getString("name");
                String add3 = rs.getString("balance");
                status.setText(add1 + "    " + add2 + "    $" + add3);
            } else {
                JOptionPane.showMessageDialog(null, "That customer ID doesn't exist");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
//functionfor table 1

    private void table1() {
        tablerentedmovies.getColumns().clear();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = (Connection) db.Db();
            //"SELECT movies.title,movies.year,movies.genre,movies.price FROM `movies`  JOIN borrowed ON borrowed.borrowed_movie = movies.id       WHERE    borrowed.cusid='101' order by  borrowed.id desc limit 1"
            String SQL = "SELECT movies.title,movies.year,movies.genre,movies.price FROM `movies`  JOIN borrowed ON borrowed.borrowed_movie = movies.id       WHERE    borrowed.cusid='" + search.getText() + "' order by  borrowed.id desc limit 1";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tablerentedmovies.getColumns().addAll(col);
            }

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            tablerentedmovies.setItems(data);
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error ->: " + e);
        }
    }

    private void table2() {
        tablerentedhistory.getColumns().clear();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = (Connection) db.Db();
            //"SELECT movies.title,movies.year,movies.genre,movies.price FROM `movies`  JOIN borrowed ON borrowed.borrowed_movie = movies.id       WHERE    borrowed.cusid='101' order by  borrowed.id desc limit 1"
            String SQL = "SELECT movies.title,movies.year,movies.genre,movies.price FROM `movies`  JOIN borrowed ON borrowed.borrowed_movie = movies.id       WHERE    borrowed.cusid='" + search.getText() + "'";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tablerentedhistory.getColumns().addAll(col);
            }

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            tablerentedhistory.setItems(data);
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error ->: " + e);
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
}
