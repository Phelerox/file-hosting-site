/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.logic;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import se.baxemyr.filehostingsite.core.AppUser;
import se.baxemyr.filehostingsite.core.Comment;
import se.baxemyr.filehostingsite.core.CommentDatabase;
import se.baxemyr.filehostingsite.core.DatabaseManager;
import se.baxemyr.filehostingsite.core.HostedFile;
import se.baxemyr.filehostingsite.core.HostedFileDatabase;
import se.baxemyr.filehostingsite.core.UserDatabase;

/**
 *
 * @author Gustav
 */
@Named("fileBB")
@RequestScoped
public class FileBB implements Serializable {
    private HostedFileDatabase hostedFileDB = DatabaseManager.INSTANCE.getHostedFileDatabase();
    private UserDatabase userDB = DatabaseManager.INSTANCE.getUserDatabase();
    private CommentDatabase commentDB = DatabaseManager.INSTANCE.getCommentDatabase();
    
    public void FileBB() {
        
    }
    
    private HostedFile getFile(String idAsString) {
        Long id = Long.valueOf(idAsString);
        return hostedFileDB.find(id);
    }
    
    public long downloads(String id) {
        return getFile(id).getDownloads();
    }
    
    public String filename(String id) {
        return getFile(id).getFilename();
    }
    
    public String date(String id) {
        Date date = getFile(id).getUploadDate();
        return (date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate();
    }
    
    public void download(String id) throws IOException {
        HostedFile file = getFile(id);
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        ec.responseReset(); 
        ec.setResponseContentType(file.getContentType()); 
        ec.setResponseContentLength(file.getBytes().length); 
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + file.getFilename() + "\""); 

        OutputStream output = ec.getResponseOutputStream();
        output.write(file.getBytes());
        output.close();

        fc.responseComplete();
        file.download();
        hostedFileDB.update(file);
    }

  
    public List<Comment> getAllComments(String id) {
        List<Comment> allComments = getFile(id).getComments();
        this.hostedFileDB.update(getFile(id));
        return allComments;   
    }

    public String delete(String id) {
        hostedFileDB.remove(Long.valueOf(id));
        return "/users/userPage?faces-redirect=true";
    }
    
    public boolean access(String id) {
        if (isPublic(id)) {
            return true;
        }
        return owner(id);
    }
    
    public boolean isPublic(String id) {
        return getFile(id).isPublic();
    }
    
    public boolean owner(String id) {
        HostedFile file = getFile(id);
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String username = req.getRemoteUser();
        if (username != null) {
            AppUser user = file.getOwner();
            if (user != null) {
                return user.getUserName().equals(username);
            }
        }
        return false;
    }
}