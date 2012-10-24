/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chs.edu.grupp14.filehostingsite.backingbean;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import chs.edu.grupp14.filehostingsite.core.DatabaseManager;
import chs.edu.grupp14.filehostingsite.core.HostedFile;
import chs.edu.grupp14.filehostingsite.core.HostedFileDatabase;

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
