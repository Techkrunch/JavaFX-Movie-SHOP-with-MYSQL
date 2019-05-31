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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ian
 */
public class MovieRemoveController implements Initializable {

    String Finalvaluetablerow;
    private ObservableList<ObservableList> data;
    @FXML
    private TableView tableview;
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table();
        tableview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (tableview.getSelectionModel().getSelectedItem() != null) {
                    TableView.TableViewSelectionModel selectionModel = tableview.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();

                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);

                    tablePosition.getTableView().getSelectionModel().getTableView().getId();
                    //gives you selected cell value..
                    Object GetSinglevalue = tablePosition.getTableColumn().getCellData(newValue);

                    String getbothvalue = tableview.getSelectionModel().getSelectedItem().toString();
                    //gives you first column value..
                    Finalvaluetablerow = getbothvalue.toString().split(",")[0].substring(1);
                    System.out.println("The First column value of row.." + Finalvaluetablerow);
                }
            }
        });
    }

    @FXML
    private void removemovies(ActionEvent event) {

//      tableview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
//    @Override
//    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
//        //Check whether item is selected and set value of selected item to Label
//        if(tableview.getSelectionModel().getSelectedItem() != null) 
//        {    
//           TableViewSelectionModel selectionModel = tableview.getSelectionModel();
//           ObservableList selectedCells = selectionModel.getSelectedCells();
//           TablePosition tablePosition = (TablePosition) selectedCells.get(0);
//           Object val = tablePosition.getTableColumn().getCellData(newValue);
//           System.out.println("Selected Value" + val);
//         }else{}
//         }
//     }); 
        if (Finalvaluetablerow == null) {
            JOptionPane.showMessageDialog(null, "Select a Movie to Delete!");
        } else {

            try {

                conn = (Connection) db.Db();
                String sql = "DELETE FROM movies WHERE id = '" + Finalvaluetablerow + "'";
                pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Movie Deleted!");
                pst.close();
                conn.close();
                table();
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

    private void table() {
        tableview.getColumns().clear();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = (Connection) db.Db();
            String SQL = "SELECT * from movies order by id asc ";
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
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error->: " + e);
        }
    }
}
