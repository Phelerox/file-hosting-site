package se.baxemyr.filehostingsite.core;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Marco Baxemyr
 */
public abstract class AbstractHostedFile {
    
    private long id;
    private String path;
    private boolean isPublic;
    
    private List<Comment> comments;
    private String fileName;
    private Date uploadDate;
    private long size;
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
        this.downloads = 0;
        this.uploadDate = new Date();
    }
    
    public long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getDownloads() {
        return downloads;
    }

    public void download() {
        this.downloads++;
    }

}
