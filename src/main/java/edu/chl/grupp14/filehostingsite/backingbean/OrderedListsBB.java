
package edu.chl.grupp14.filehostingsite.backingbean;

import edu.chl.grupp14.filehostingsite.core.entities.HostedFile;
import edu.chl.grupp14.filehostingsite.core.db.HostedFileDatabase;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped //?
@Named("orderedlistsBB")
public class OrderedListsBB {
    
    private HostedFileDatabase hostedFileDB = HostedFileDatabase.newInstance("filehosting_pu");
    
    public OrderedListsBB(){
        
    }
    
    public List<HostedFile> getLatestFiles(){
        List<HostedFile> filelist = new ArrayList<>();
        filelist.addAll(hostedFileDB.getLatestPublicFiles());
        return filelist;
    }
    
    public List<HostedFile> getMostDownloaded(){
        List<HostedFile> filelist = new ArrayList<>();
        filelist.addAll(hostedFileDB.getMostPublicDownloaded());
        return filelist;
    }
}
