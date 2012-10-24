/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.grupp14.filehostingsite.backingbean;

import edu.chl.grupp14.filehostingsite.core.AppUser;
import edu.chl.grupp14.filehostingsite.core.DatabaseManager;
import edu.chl.grupp14.filehostingsite.core.CommentDatabase;
import edu.chl.grupp14.filehostingsite.core.Comment;
import edu.chl.grupp14.filehostingsite.core.HostedFile;
import edu.chl.grupp14.filehostingsite.core.HostedFileDatabase;
import edu.chl.grupp14.filehostingsite.core.UserDatabase;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

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
    private String newComment;

    public void FileViewBB() {
    }

    private HostedFile getFile(String idAsString) {
        Long id = Long.valueOf(idAsString);
        return hostedFileDB.find(id);
    }
    
    private Comment getComment(Long id) {
        return commentDB.find(id);
    }
    
    public void submitComment(String id) {
        if (!newComment.equals("")) {
            HostedFile file = getFile(id);
            String content = this.newComment;

            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String username = req.getRemoteUser();
            if (username != null) {
                AppUser author = userDB.find(username);
                Comment c = new Comment(content, author);
                file.addComment(c);
                this.hostedFileDB.update(file);   
            }
            newComment="";
        }
    }
    
    public void setNewComment(String text) {
        this.newComment = text;
    }

    public String getNewComment() {
        return this.newComment;
    }
    
    public String getCommentDate(Long id) {
        Date date = getComment(id).getDatePosted();
        return (date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate();
    }
}
