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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import se.baxemyr.filehostingsite.core.DatabaseManager;
import se.baxemyr.filehostingsite.core.AppUser;
import se.baxemyr.filehostingsite.core.UserDatabase;
import se.baxemyr.filehostingsite.core.SubjectGroup;

/**
 *
 * @author anders
 */
@Named("authBB")
@RequestScoped
public class AuthorizationBB implements Serializable {
   
   @NotNull
   private String username;
   @NotNull
   private String password;
   
   public AuthorizationBB(){  
   }
   
   public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            AppUser user = new AppUser();
            request.login(username, UserAuthentication.hash(password, user.getSalt()));
            return "/orderedLists?faces-redirect=true";
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unknown login", null));
            return "/login/loginError?faces-redirect=true";
        }
    }
   
    public String logout() {
        String result = "/orderedLists?faces-redirect=true";

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.logout();
        } catch (ServletException e) {
            result = "/login/loginError?faces-redirect=true";
        }

        return result;
    }
   

   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
    
    
}
