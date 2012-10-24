/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chs.edu.grupp14.filehostingsite.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;
import chs.edu.grupp14.filehostingsite.core.AppUser;
import chs.edu.grupp14.filehostingsite.core.DatabaseManager;
import chs.edu.grupp14.filehostingsite.core.UserDatabase;
import chs.edu.grupp14.filehostingsite.core.SubjectGroup;

/**
 *
 * @author anders
 */
@Named("settingsBB")
@RequestScoped
public class SettingsBB implements Serializable {
   
   @NotNull(message = "Can't be null")
   @Pattern(regexp="[\\p{Alpha}\\p{Blank}]+",message="Only Alphabetic and Blank chars allowed")
   private String name;
   @NotNull(message = "Can't be null")
   private String email;
   @NotNull(message = "Can't be null")
   private String password;
   @NotNull(message = "Can't be null")
   private String repeatedPassword;
   
   private UserDatabase udb;
   private static final Logger log = Logger.getLogger(SettingsBB.class.getName());
   
   public SettingsBB(){  
   }
     
   public String submit() throws IOException {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            String username = request.getRemoteUser();
            udb = DatabaseManager.INSTANCE.getUserDatabase();
            if (username != null) {
                udb.update(new AppUser(username, name, email, password, SubjectGroup.USER));
                return "/orderedLists?faces-redirect=true";
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Are you logged in?", null));
            return null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error"));
            e.printStackTrace();
            return null; // Same page
        }
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
