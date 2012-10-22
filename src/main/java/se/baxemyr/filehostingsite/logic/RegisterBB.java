/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.logic;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.*;
import se.baxemyr.filehostingsite.core.AppUser;
import se.baxemyr.filehostingsite.core.DatabaseManager;
import se.baxemyr.filehostingsite.core.UserDatabase;
import se.baxemyr.filehostingsite.core.SubjectGroup;

/**
 *
 * @author anders
 */
@Named("registerBB")
@RequestScoped
public class RegisterBB implements Serializable {
   
   @NotNull(message = "Can't be null")
   @Pattern(regexp="\\p{Alpha}+", message="Only Alphabetic chars allowed")
   private String username;
   @NotNull(message = "Can't be null")
   @Pattern(regexp="\\p{Alpha}+", message="Only Alphabetic chars allowed")
   private String name;
   @NotNull(message = "Can't be null")
   private String email;
   @NotNull(message = "Can't be null")
   private String password;
   @NotNull(message = "Can't be null")
   private String repeatedPassword;
   
   private UserDatabase udb;
   private static final Logger log = Logger.getLogger(RegisterBB.class.getName());
   
   public RegisterBB(){  
   }
     
   public String submit() throws IOException {
        
   log.log(Level.INFO, "New Customer Login: {0} Passwd: {1}", new Object[]{username, password});
        try {
            udb = DatabaseManager.INSTANCE.getUserDatabase();
            udb.add(new AppUser(username,name,email,password, SubjectGroup.USER));
            return "/login/login";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bad Login name"));
            return null; // Same page
        }
}
   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }
   
}
