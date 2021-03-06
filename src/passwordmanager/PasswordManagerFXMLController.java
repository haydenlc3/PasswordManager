/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author HLC
 */
public class PasswordManagerFXMLController implements Initializable {
    
    @FXML
    private AnchorPane rootpane;
    
    @FXML
    private TextField username;
    
    @FXML
    private TextField password;
    
    @FXML
    private TextField site;
    
    @FXML
    private TextField email;
    
    @FXML
    private Label error;
    
    @FXML
    private TableView<Password> table;
    private TableColumn<Password, SimpleStringProperty> usernameColumn;
    private TableColumn<Password, SimpleStringProperty> passwordColumn;
    private TableColumn<Password, SimpleStringProperty> siteColumn;
    private TableColumn<Password, SimpleStringProperty> emailColumn;
    private TableColumn<Password, LocalDate> dateColumn;
    
    public void clearFields() {
        username.clear();
        password.clear();
        site.clear();
        email.clear();
    }
    
    public void loadTable() {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        siteColumn.setCellValueFactory(new PropertyValueFactory<>("site"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
    
    public boolean isValidData() {
        if (username.getLength() > 0) {
            if (password.getLength() > 0) {
                if (site.getLength() > 0) {
                    return true;
                } else {
                    error.setText("Must enter name of program/site");
                    return false;
                }
            } else {
                error.setText("Must have password");
                return false;
            }
        } else {
            error.setText("Must have username");
            return false;
        }
    }
    
    @FXML
    public void addData() {
        if (isValidData()) {
            table.getItems().add(new Password(username.getText(), password.getText(), site.getText(), email.getText(), LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MMMM yyyy"))));
            //table.setItems(data);
        }
        clearFields();
    }
    
    @FXML
    public void replaceSelectedData() {
        if (isValidData()) {
            TablePosition pos = (TablePosition) table.getSelectionModel().getSelectedCells().get(0);
            //table.getItems().set(pos.getRow(), new Object[]{username.getText(), password.getText(), site.getText(), email.getText(), java.time.LocalDate.now()});
            clearFields();
        }
    }
    
    @FXML
    public void removeSelectedData() {
        //ObservableList<Object> selectedRows = table.getSelectionModel().getSelectedItems();
        //ArrayList<Object> rows = new ArrayList<>(selectedRows);
        //rows.forEach(row -> table.getItems().remove(row));
    }
    
    @FXML
    public void clearAll() {
        table.getItems().clear();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                error.setText("");
            }
        }, 0, 1, TimeUnit.SECONDS);
    }    
}
