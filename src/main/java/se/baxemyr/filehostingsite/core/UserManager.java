/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.core;

import se.baxemyr.filehostingsite.logic.login.SubjectGroup;

/**
 *
 * @author Gustav
 * 
 * This class should probably be deleted until we figure out how to deal with logged in users, 
 * and find out how much the container does for us
 */
public class UserManager {
    private static UserManager instance = new UserManager();
    private static AppUser currentUser;
    
    private UserManager() {
        //singleton
        
        currentUser = new AppUser("default_user", "Default User", "default@user.com", "default",SubjectGroup.USER);
        DatabaseManager.INSTANCE.getUserDatabase().add(currentUser);
    }
    
    public static UserManager getInstance() {
        return instance;
    }
    
    public void setCurrentUser(AppUser user) {
        currentUser = user;
    }
    
    public AppUser getCurrentUser() {
        return currentUser;
    }
    
    public AppUser getUser(String username){
        return null;
        //Should fetch user from database
    }
}
