package se.baxemyr.filehostingsite.core;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Marco Baxemyr
 */
@Entity
public abstract class AbstractHostedFile implements Serializable {
   
    //This should be generated, and accessible?!
    private static long idshouldbegenereated = 0;
    
    @Id
    private long id;
    private byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
    private boolean isPublic;
    
    private List<Comment> comments;
    private String fileName;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date uploadDate;
    //size is reserved
    private long ssize;
    private long downloads;

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
    
    public void removeComment(Comment comment) {
        this.comments.remove(comment);
    }
    
    public AbstractHostedFile() {
        this.id = AbstractHostedFile.idshouldbegenereated;
        AbstractHostedFile.idshouldbegenereated += 1;
        
        this.downloads = 0;
        this.uploadDate = new Date();
    }
    
    public long getId() {
        return id;
    }

    public String getName() {
        return fileName;
    }

    public void setName(String fileName) {
        this.fileName = fileName;
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

}
