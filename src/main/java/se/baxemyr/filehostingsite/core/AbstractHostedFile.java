package se.baxemyr.filehostingsite.core;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Marco Baxemyr
 */
@Entity
public abstract class AbstractHostedFile implements IEntity<Long> {
    @Id
    @GeneratedValue
    private Long id;
    private byte[] bytes;

    private boolean isPublic;
    
    private List<Comment> comments;
    private String filename;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date uploadDate;
    //size is reserved
    private long ssize;
    private long downloads;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
    
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
     this.downloads = 0;
        this.uploadDate = new Date();
    }
    
    public Long getId() {
        return id;
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
}
