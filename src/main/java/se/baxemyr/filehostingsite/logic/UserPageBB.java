/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.logic;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import se.baxemyr.filehostingsite.core.HostedFile;
import se.baxemyr.filehostingsite.core.HostedFile;
import se.baxemyr.filehostingsite.core.HostedFileDatabase;
import se.baxemyr.filehostingsite.core.UserManager;

/**
 *
 * @author Gustav
 */
@RequestScoped //?
@Named("userpageBB")
public class UserPageBB {
    private HostedFileDatabase userHostedFileDB = HostedFileDatabase.newInstance("filehosting_pu");
    
    public UserPageBB() {
        
    }
    
    public List<HostedFile> getAll() {
        List<HostedFile> filelist = new ArrayList<>();
        filelist.addAll(userHostedFileDB.getFilesFromOwner(UserManager.getInstance().getCurrentUser())); 
        filelist.add(userHostedFileDB.find(1L)); //endast tills vidare
        return filelist;
    }
    
    public List<HostedFile> getLatestFiles(){
        List<HostedFile> filelist = new ArrayList<HostedFile>();
        filelist.addAll(userHostedFileDB.getLatestFiles());
        return filelist;
    }
    public List<HostedFile> getMostDownloaded(){
        List<HostedFile> filelist = new ArrayList<HostedFile>();
        filelist.addAll(userHostedFileDB.getMostDownloaded());
        return filelist;
    }
}
