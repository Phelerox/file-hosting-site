/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.grupp14.filehostingsite.backingbean;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import edu.chl.grupp14.filehostingsite.core.AppUser;
import edu.chl.grupp14.filehostingsite.core.DatabaseManager;
import edu.chl.grupp14.filehostingsite.core.AppGroup;
import edu.chl.grupp14.filehostingsite.core.AppGroupDatabase;
import edu.chl.grupp14.filehostingsite.core.UserDatabase;

/**
 *
 * @author Sam
 */
@Named("groups")
@RequestScoped
public class GroupsBB {

    private UserDatabase userDB = DatabaseManager.INSTANCE.getUserDatabase();
    private AppGroupDatabase groupDB = DatabaseManager.INSTANCE.getAppGroupDatabase();
    private AppUser user;
    private String name;
    private String newUser;
    private String lookupName;

    public GroupsBB() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String username = req.getRemoteUser();
        user = userDB.find(username);

    }
        
    public String create() { 
        user = userDB.find(user.getUserName());
        AppGroup group = new AppGroup(this.name, user);
        group.addMember(user);
        groupDB.add(group);
        user.addGroup(group);
        userDB.update(user);
        return "/users/displayGroup?id="+this.name+"&faces-redirect=true";
    }
    
    public void addNewMember() {
        AppGroup group = groupDB.find(this.lookupName);
        AppUser newUserr = userDB.find(newUser);
        group.addMember(newUserr);
        groupDB.update(group);
        user.addGroup(group);
        userDB.update(newUserr);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<AppGroup> getAll(){
        return this.groupDB.getAll();
    }

    public String getNewUser() {
        return newUser;
    }

    public void setNewUser(String newUser) {
        this.newUser = newUser;
    }

    public String getLookupName() {
        return lookupName;
    }

    public void setLookupName(String lookupName) {
        this.lookupName = lookupName;
    }
    
    
}
