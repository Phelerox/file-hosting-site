package se.baxemyr.filehostingsite.core;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author Marco Baxemyr
 */
public class Group {
    private List<User> members;
    private List<Comment> comments;
    
    @OneToMany(mappedBy = "ggroup", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<HostedFile> userHostedFiles;

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
