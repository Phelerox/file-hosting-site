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
import se.baxemyr.filehostingsite.core.HostedFile;

/**
 *
 * @author Gustav
 */
@Named("fileviewBB")
@ConversationScoped
public class FileViewBB implements Serializable {
    @Inject
    private Conversation conversation;
    
    private HostedFile file;
    
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
    public void delete(){
        
    }
    
    public void init(ActionEvent e) {
        if (conversation.isTransient()) {
            conversation.begin();
        } else {
        }
        
        this.file = (HostedFile) e.getComponent().getAttributes().get("file");
        
    }
}
