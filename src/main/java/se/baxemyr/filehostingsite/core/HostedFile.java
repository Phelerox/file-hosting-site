/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.core;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Marco Baxemyr
 */
public class HostedFile {
    
    private long id;
    private String path;
    private long owner;
    private List<Long> usersWithAccess;
    private boolean isPublic;
    
    private String fileName;
    private Date uploadDate;
    private long size;
    private long downloads;
    
    public HostedFile() {
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

    public long getOwner() {
        return owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }
    
    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public List<Long> getUsersWithAccess() {
        return usersWithAccess;
    }

    public void grantAccess(long userID) {
        this.usersWithAccess.add(userID);
    }
    
    public void revokeAccess(long userID) {
        this.usersWithAccess.remove(userID);
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
