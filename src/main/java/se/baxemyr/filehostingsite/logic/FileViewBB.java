/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.logic;

import java.io.Serializable;
import javax.enterprise.context.*;
import javax.inject.*;
import se.baxemyr.filehostingsite.core.AbstractHostedFile;

/**
 *
 * @author Gustav
 */
@Named("upload")
@ConversationScoped
public class FileViewBB implements Serializable {
    @Inject
    private Conversation conversation;
    
    private AbstractHostedFile file;
    
    public FileViewBB() {
        
    }
    
    public AbstractHostedFile getFile() {
        return file;
    }
    
    public void setFile(AbstractHostedFile file) {
        this.file = file;
    }
    
    public String actionListener() {
         if (!conversation.isTransient()) {
             //do stuff
            conversation.end();
        }
        try {
            return "index?faces-redirect=true"; // Go back
        } catch (Exception e) {
            return null;
        }
    }
}
