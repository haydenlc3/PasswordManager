/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author HLC
 */
public class LoginFXMLController implements Initializable {
    
    @FXML
    private AnchorPane rootpane;

    @FXML
    private TextField username;
    
    @FXML
    private TextField password;
    
    @FXML
    private TextField confirmpass;
    
    @FXML
    private TextField email;
    
    @FXML
    private TextField question;
    
    @FXML
    private TextField answer;
    
    @FXML
    private Label error;
    
    @FXML
    public void login() {
        System.out.println("Hello");
    }
    
    @FXML
    public void signup() throws IOException {
        if (username.getLength() >= 6 && password.getLength() >= 8) {
            boolean hasNumber = false;
            for (int i = 0; i < username.getLength(); i++){
                if (Character.isDigit(username.getText().charAt(i))) {
                    hasNumber = true;
                    break;
                }
            }
            
            if (hasNumber) {
                Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(password.getText());
                
                if (m.find()) {
                    if (confirmpass.getText().equals(password.getText())) {
                        if (email.getText().contains("@")) {
                            if (question.getLength() > 0 && answer.getLength() > 0) {
                                // SUCCESS
                                // SAVE DATA TO FILE
                                AnchorPane pane = FXMLLoader.load(getClass().getResource("PasswordManagerFXML.fxml"));
                                rootpane.getChildren().setAll(pane);
                            } else {
                                error.setText("Security question and answer cannot be blank");
                            }
                        } else {
                            error.setText("Not a valid email address");
                        }
                    } else {
                        error.setText("Passwords do not match");
                    }
                } else {
                    error.setText("Password must contain at least one special character");
                }
            } else {
                error.setText("Password must contain at least one number");
            }
        } else {
            error.setText("Username must be at least 6 characters in length; password must be 8");
        }
    }
    
    @FXML
    public void goToSignup() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("SignupFXML.fxml"));
        rootpane.getChildren().setAll(pane);
    }
    
    @FXML
    public void returnLogin() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        rootpane.getChildren().setAll(pane);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
