package se.baxemyr.filehostingsite.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Marco Baxemyr
 */
@Entity
public class HostedFile implements IEntity<Long> {
    @Id
    @GeneratedValue
    private Long id;
    private byte[] bytes;

    private boolean isPublic;
    
    @OneToMany
    private List<Comment> comments = new ArrayList();
    
    private String filename;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date uploadDate;
    //size is reserved
    private long ssize;
    private long downloads;
    
    @ManyToOne (cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private AppGroup ggroup;
    
    @ManyToOne (cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private AppUser owner;
    //private List<AppUser> usersWithAccess;
    
    private String contentType;
    
    
    public HostedFile() {
     this.uploadDate = new Date();
    }
    
    public void addComment(Comment c){
        this.comments.add(c);
    }
    public List<Comment> getComments(){
        return this.comments;
    }
    
    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }
    
//    public List<AppUser> getUsersWithAccess() {
//        return usersWithAccess;
//    }
//
//    public void grantAccess(AppUser user) {
//        this.usersWithAccess.add(user);
//    }
//    
//    public void revokeAccess(AppUser user) {
//        this.usersWithAccess.remove(user);
//    }  
    
    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
    
    public Long getId() {
        return id;
    }

    public AppGroup getGroup() {
        return this.ggroup;
    }

    public void setGroup(AppGroup group) {
        this.ggroup = group;
    }
    
    public String getFilename() {
        return filename;
    }

    public void setFilename(String fileName) {
        this.filename = fileName;
    }
    
    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public long getSize() {
        return ssize;
    }

    public void setSize(long size) {
        this.ssize = size;
    }

    public long getDownloads() {
        return downloads;
    }

    public void download() {
        this.downloads++;
    }

    public String getContentType() {
        return this.contentType;
    }
    
    public void setContentType(String contentType){
        this.contentType = contentType;
    }
}
