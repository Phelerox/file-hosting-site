/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.logic;

import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import se.baxemyr.filehostingsite.core.AbstractHostedFile;
import se.baxemyr.filehostingsite.core.UserHostedFile;

/**
 *
 * @author Gustav
 */
@Named("fileviewBB")
@ConversationScoped
public class FileViewBB implements Serializable {
    @Inject
    private Conversation conversation;
    
    private AbstractHostedFile file;
    
    public void FileViewBB() {
        
    }
    
    public AbstractHostedFile getFile() {
        return file;
    }
    
    public void setFile(AbstractHostedFile file) {
        this.file = file;
    }
    
    public void download() {
        
    }
    
    public void init(ActionEvent e) {
        if (conversation.isTransient()) {
            conversation.begin();
        } else {
        }
        
        this.file = (AbstractHostedFile) e.getComponent().getAttributes().get("file");
        
    }
}
