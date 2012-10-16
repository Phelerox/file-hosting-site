package se.baxemyr.filehostingsite.core;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 *
 * @author Marco Baxemyr
 */
@Entity
public class UserHostedFile extends AbstractHostedFile {

    @ManyToOne (cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
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
