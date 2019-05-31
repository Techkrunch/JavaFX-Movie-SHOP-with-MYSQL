/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ian
 */
public class FavouriteController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private Button btnselectcus;
    @FXML
    private Button btnexit;
    @FXML
    private TableView tableview;
    private ObservableList<ObservableList> data;

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
        tablevalues();
    }

    @FXML
    private void btnexit(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("Main Menu.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Movie Kiosk - Main Menu");
        app.show();
    }

    private void tablevalues() {
        tableview.getColumns().clear();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = (Connection) db.Db();
            String SQL = "SELECT movies.title,movies.year,movies.genre,movies.price FROM `movies`  JOIN borrowed ON borrowed.borrowed_movie = movies.id       WHERE    borrowed.cusid='" + search.getText() + "' ";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
            }

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {

                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            tableview.setItems(data);
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error ->: " + e);
        }
    }
}
