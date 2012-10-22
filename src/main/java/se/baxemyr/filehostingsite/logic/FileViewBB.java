/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import se.baxemyr.filehostingsite.core.*;

/**
 *
 * @author Gustav & Anders
 */
@Named("fileviewBB")
@ConversationScoped
public class FileViewBB implements Serializable {

    @Inject
    private Conversation conversation;
    private HostedFileDatabase hostedFileDB = DatabaseManager.INSTANCE.getHostedFileDatabase();
    private UserDatabase userDB = DatabaseManager.INSTANCE.getUserDatabase();
    private CommentDatabase commentDB = DatabaseManager.INSTANCE.getCommentDatabase();
    private HostedFile file;
    private String comment;

    public void FileViewBB() {
    }

    public HostedFile getFile() {
        return file;
    }

    public void setFile(HostedFile file) {
        this.file = file;
    }

    public void download() {
    }

    public String submit() {
        String content = this.comment;

        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String username = req.getRemoteUser();
        AppUser author = userDB.find(username);

        file.setComment(new Comment(content, author));

        this.hostedFileDB.update(file);

        return null; //"/fileView?faces-redirect=true";
    }

    public List<Comment> getAllComments() {
        List<Long> ids = new ArrayList();
        List<Comment> comments = new ArrayList();
        comments = commentDB.getCommentsIdbyFileId(file.getId());
        
        return comments;     
    }

    public void setComment(String text) {
        this.comment = text;
    }

    public String getComment() {
        return this.comment;
    }

    public void delete() {
        hostedFileDB.remove(this.file.getId());
    }

    public void init(ActionEvent e) {
        if (conversation.isTransient()) {
            conversation.begin();
        } else {
        }
        this.file = (HostedFile) e.getComponent().getAttributes().get("file");
    }
}