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
import se.baxemyr.filehostingsite.core.*;

/**
 *
 * @author Gustav & Anders
 */
@Named("fileviewBB")
@RequestScoped
public class FileViewBB implements Serializable {
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
    
    public void submit(String id) {
        if (!comment.equals("")) {
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
            comment="";
        }
    }
    
    public void setComment(String text) {
        this.comment = text;
    }

    public String getComment() {
        return this.comment;
    }
}
