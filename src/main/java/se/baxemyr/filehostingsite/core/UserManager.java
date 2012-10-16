/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.core;

/**
 *
 * @author Gustav
 */
public class UserManager {
    private static UserManager instance = new UserManager();
    private static User currentUser;
    
    private UserManager() {
        //singleton
        
        currentUser = new User("default_user", "Default User", "default@user.com", "default");
    }
    
    public static UserManager getInstance() {
        return instance;
    }
    
    public void setCurrentUser(User user) {
        currentUser = user;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
}
