/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.logic;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import se.baxemyr.filehostingsite.core.DatabaseManager;
import se.baxemyr.filehostingsite.core.HostedFile;
import se.baxemyr.filehostingsite.core.HostedFileDatabase;

/**
 *
 * @author Sam
 */

@Named("administrate")
@RequestScoped
public class AdministrateBB {
    
    HostedFileDatabase hfd = DatabaseManager.INSTANCE.getHostedFileDatabase();
    
    public AdministrateBB(){   
    }
    
    public List<HostedFile> getAll(){
        return this.hfd.getAll();
    }
    public void remove(HostedFile file){
        this.hfd.remove(file.getId());
    }
}
