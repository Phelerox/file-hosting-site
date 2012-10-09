package se.baxemyr.filehostingsite.core;

import java.util.List;

/**
 *
 * @author Marco Baxemyr
 */
public class UserHostedFile extends AbstractHostedFile {

    private User owner;
    private List<User> usersWithAccess;
    
    public UserHostedFile() {
        super();
    }
    
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
    
    public List<User> getUsersWithAccess() {
        return usersWithAccess;
    }

    public void grantAccess(User user) {
        this.usersWithAccess.add(user);
    }
    
    public void revokeAccess(User user) {
        this.usersWithAccess.remove(user);
    }
    
}
