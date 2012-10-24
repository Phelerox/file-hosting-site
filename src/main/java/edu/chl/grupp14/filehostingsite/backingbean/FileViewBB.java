package edu.chl.grupp14.filehostingsite.backingbean;

import edu.chl.grupp14.filehostingsite.core.db.CommentDatabase;
import edu.chl.grupp14.filehostingsite.core.db.DatabaseManager;
import edu.chl.grupp14.filehostingsite.core.db.HostedFileDatabase;
import edu.chl.grupp14.filehostingsite.core.db.UserDatabase;
import edu.chl.grupp14.filehostingsite.core.entities.HostedFile;
import edu.chl.grupp14.filehostingsite.core.entities.AppUser;
import edu.chl.grupp14.filehostingsite.core.entities.Comment;
import edu.chl.grupp14.filehostingsite.core.*;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named("fileviewBB")
@RequestScoped
public class FileViewBB implements Serializable {
    private HostedFileDatabase hostedFileDB = DatabaseManager.INSTANCE.getHostedFileDatabase();
    private UserDatabase userDB = DatabaseManager.INSTANCE.getUserDatabase();
    private CommentDatabase commentDB = DatabaseManager.INSTANCE.getCommentDatabase();
    private String newComment;

    public FileViewBB() {
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
