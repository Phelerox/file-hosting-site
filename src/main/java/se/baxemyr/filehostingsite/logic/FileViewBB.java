/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.logic;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import se.baxemyr.filehostingsite.core.*;

/**
 *
 * @author Gustav & Anders
 */
@Named("fileviewBB")
@RequestScoped
public class FileViewBB implements Serializable {
    /*
    @Inject
    private Conversation conversation;
    */
    private HostedFileDatabase hostedFileDB = DatabaseManager.INSTANCE.getHostedFileDatabase();
    private UserDatabase userDB = DatabaseManager.INSTANCE.getUserDatabase();
    private CommentDatabase commentDB = DatabaseManager.INSTANCE.getCommentDatabase();
    private String comment;

    public void FileViewBB() {
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

    public void submit(String id) {
        HostedFile file = getFile(id);
        String content = this.comment;

        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String username = req.getRemoteUser();
        if (username != null) {
            AppUser author = userDB.find(username);
            Comment c = new Comment(content, author);
            file.addComment(c);
            this.hostedFileDB.update(file);   
        }
    }
    public void setComment(String text) {
        this.comment = text;
    }

    public String getComment() {
        return this.comment;
    }

    public String delete(String id) {
        hostedFileDB.remove(Long.valueOf(id));
        return "/users/userPage?faces-redirect=true";
    }
    
    public boolean access(String id) {
        HostedFile file = getFile(id);
        if (file.isPublic()) {
            return true;
        }
        return owner(id);
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