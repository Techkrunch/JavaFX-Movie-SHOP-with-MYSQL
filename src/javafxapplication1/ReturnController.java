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
import javafx.scene.control.TablePosition;
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
public class ReturnController implements Initializable {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs;
    private ObservableList<ObservableList> data;
    String Finalvaluetablerow;
    int movieid;

    @FXML
    private TextField cusid;
    @FXML
    private Button btnselectcus;
    @FXML
    private TableView tableview;

    @FXML
    private Button btnreturn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnselectcus.setDisable(true); // Initally text box was empty so button was disable
        btnreturn.setDisable(true);
        cusid.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (cusid.getLength() > 0) {
                    if (cusid.equals("")) {
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
    private void btnSelect(ActionEvent event) {
        String sql = "SELECT * from customers where id=?";
        try {
            conn = db.Db();
            pst = conn.prepareStatement(sql);
            pst.setString(1, cusid.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                //  JOptionPane.showMessageDialog(null, "found");
                loadata();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Customer ID");
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
    private void btnexit(ActionEvent event) throws IOException {
        Parent x = FXMLLoader.load(getClass().getResource("Catalogue.fxml"));
        Scene M = new Scene(x);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(M);
        app.setTitle("Movies by Year");
        app.show();
    }

    private void loadata() {
        tableview.getColumns().clear();
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = (Connection) db.Db();
//            SELECT movies.title,movies.year,movies.genre,movies.price FROM `movies` JOIN borrowed ON borrowed.borrowed_movie = movies.id WHERE    borrowed.cusid='101'  and status ='borrowed'
            String SQL = " SELECT movies.title,movies.year,movies.genre,movies.price FROM `movies` JOIN borrowed ON borrowed.borrowed_movie = movies.id WHERE    borrowed.cusid = '" + cusid.getText() + "' and movies.status = 'borrowed' ";
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
                    btnreturn.setDisable(false);
                    getid();

                }
            }
        });
    }

    public void getid() {

        String sql = "SELECT * from movies where title='" + Finalvaluetablerow + "'";
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                movieid = Integer.parseInt(rs.getString("id"));

                conn.close();
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
    private void returnselectedmovie(ActionEvent event) throws SQLException {
        conn = db.Db();
        String sql = "update movies set status = 'available' where id = '" + movieid + "'  ";
        pst = conn.prepareStatement(sql);
        try {
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Movie Returned &\n is Available!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                pst.close();
            } catch (Exception e) {
            }
        }
    }
}
