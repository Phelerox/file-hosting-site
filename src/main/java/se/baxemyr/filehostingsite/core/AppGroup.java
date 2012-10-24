package se.baxemyr.filehostingsite.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Marco Baxemyr
 */

@Entity
public class AppGroup implements Serializable {
    @Id
    @Column(nullable=false, name="ID")
    private String id;
    
    @OneToOne
    private AppUser creator;
   
    @ManyToMany
    private List<AppUser> members = new ArrayList();
    //private List<Comment> comments;
    
//    @OneToMany(mappedBy = "ggroup", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
//    private List<HostedFile> userHostedFiles;

    public AppGroup(){
        
    }
    
    public AppGroup(String id, AppUser creator){
        this.id = id;
        this.creator = creator;
    }
     
    public void setId(String id) {
        this.id = id;
    }
    
    public List<AppUser> getMembers() {
        return this.members;
    }
    
    public void addMember(AppUser user) {
        this.members.add(user);
    }
    
//    public void removeMember(AppUser user) {
//        this.members.remove(user);
//    }

//    public List<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<Comment> comments) {
//        this.comments = comments;
//    } 

    public String getId() {
        return id;
    }
}
