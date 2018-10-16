/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordmanager;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author HLC
 */

public class Password {
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;
    private final SimpleStringProperty site;
    private final SimpleStringProperty email;
    private final SimpleStringProperty date;
    
    public Password(String User, String Pass, String Site, String Email, String cDate) {
        this.username = new SimpleStringProperty(User);
        this.password = new SimpleStringProperty(Pass);
        this.site = new SimpleStringProperty(Site);
        this.email = new SimpleStringProperty(Email);
        this.date = new SimpleStringProperty(cDate);
    }
    
    public void setUsername(String User) {
        username.set(User);
    }
    
    public void setPassword(String Pass) {
        password.set(Pass);
    }
    
    public void setSite(String Site) {
        site.set(Site);
    }
    
    public void setEmail(String Email) {
        email.set(Email);
    }
    
    public void setDate(String Date) {
        date.set(Date);
    }
}