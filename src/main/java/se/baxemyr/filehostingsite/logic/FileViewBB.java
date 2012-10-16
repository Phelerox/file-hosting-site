/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.logic;

import java.io.Serializable;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import se.baxemyr.filehostingsite.core.AbstractHostedFile;

/**
 *
 * @author Gustav
 */
@Named("fileviewBB")
@ConversationScoped
public class FileViewBB implements Serializable {
    private AbstractHostedFile file;
    
    public void FileViewBB() {
        
    }
    
    public FileViewBB(Long id) {
        
    }
    
    public AbstractHostedFile getFile() {
        return file;
    }
    
    public void setFile(AbstractHostedFile file) {
        this.file = file;
    }
    
    public void download() {
        
    }
}
