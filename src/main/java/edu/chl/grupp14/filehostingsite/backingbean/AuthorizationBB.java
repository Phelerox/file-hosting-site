/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.grupp14.filehostingsite.backingbean;

import edu.chl.grupp14.filehostingsite.core.entities.AppUser;
import edu.chl.grupp14.filehostingsite.core.db.DatabaseManager;
import edu.chl.grupp14.filehostingsite.core.SubjectGroup;
import edu.chl.grupp14.filehostingsite.core.UserAuthentication;
import edu.chl.grupp14.filehostingsite.core.db.UserDatabase;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@Named("authBB")
@RequestScoped
public class AuthorizationBB implements Serializable {
   
   private UserDatabase udb;
    
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
            udb = DatabaseManager.INSTANCE.getUserDatabase();
            AppUser user = udb.find(username);
            if (user != null) {
                request.login(username, UserAuthentication.hash(password, user.getSalt()));
                return "/users/userPage?faces-redirect=true";
            }
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unknown username", null));
            return null;
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unknown password", null));
            return null;
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
    
    public boolean isLoggedIn() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return (request.getRemoteUser() != null);
    }
   
    public boolean isAdminLoggedIn() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String remoteuser = request.getRemoteUser();
        if (remoteuser != null) {
            udb = DatabaseManager.INSTANCE.getUserDatabase();
            AppUser user = udb.find(remoteuser);
            if (user != null) {
                if (user.getSubjectGroups().contains(SubjectGroup.ADMIN)) {
                    return true;
                }
            }
        }
        return false;
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
