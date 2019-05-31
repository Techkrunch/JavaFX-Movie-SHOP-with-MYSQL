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
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ian
 */
public class GenresController implements Initializable {

    private ObservableList<ObservableList> data;

    String firstFourChars, current;
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    @FXML
    private TableView genretable;
    @FXML
    private ListView genrelist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void listvclicked(MouseEvent event) {
        String abc = (String) genrelist.getSelectionModel().getSelectedItem();
//    System.out.println("clicked on " + yearlist.getSelectionModel().getSelectedItem());
        genretable.getColumns().clear();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = (Connection) db.Db();
            String SQL = "SELECT movies.title,movies.year,movies.genre,movies.price from movies where genre= '" + abc + "' ";
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                genretable.getColumns().addAll(col);
            }

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            genretable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error->: " + e);
        }
    }

    @FXML
    private void exit(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("Catalogue.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Catalogue");
        app.show();
    }

    private void list() {

        conn = db.Db();
        try {

            String query = "select * from movies";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                current = rs.getString("genre");
                ObservableList<String> list = FXCollections.observableArrayList(current);

                genrelist.getItems().addAll(list);
            }
            pst.close();
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void displaylist(ActionEvent event) {
        list();

    }

}
