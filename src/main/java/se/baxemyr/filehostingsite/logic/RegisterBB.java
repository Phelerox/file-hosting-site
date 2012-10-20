/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.logic;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import se.baxemyr.filehostingsite.core.DatabaseManager;
import se.baxemyr.filehostingsite.core.AppUser;
import se.baxemyr.filehostingsite.core.UserDatabase;

/**
 *
 * @author anders
 */
@Named("registerBB")
@RequestScoped
public class RegisterBB implements Serializable {
   private UserDatabase userDB;
   
   private String username;
   private String name;
   private String email;
   private String password;
   private String repeatedPassword;
   
   public RegisterBB(){  
   }
   
   public String submit() throws IOException {
        //Save in DB.
        userDB = DatabaseManager.INSTANCE.getUserDatabase();
        System.out.println(username);
        AppUser user = new AppUser(username, name, email, password); //password won't be saved, User constructor makes sure a hash and salt is created
        userDB.add(user); //Still won't work, damn database
        //Skickar vidare till Userpage
        try{
            
            return "userPage?faces-redirect=true";
        }catch(Exception e){
            return null;
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
