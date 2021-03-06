
package edu.chl.grupp14.filehostingsite.backingbean;

import edu.chl.grupp14.filehostingsite.core.db.DatabaseManager;
import edu.chl.grupp14.filehostingsite.core.entities.HostedFile;
import edu.chl.grupp14.filehostingsite.core.db.HostedFileDatabase;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
 
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
