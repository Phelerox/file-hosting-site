package se.baxemyr.filehostingsite.core;

import java.util.List;

/**
 *
 * @author Marco Baxemyr
 */
public class Group {
    private List<User> members;
    private List<Comment> comments;

    public List<User> getMembers() {
        return this.members;
    }
    
    public void addMember(User user) {
        this.members.add(user);
    }
    
    public void removeMember(User user) {
        this.members.remove(user);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    
    
}
