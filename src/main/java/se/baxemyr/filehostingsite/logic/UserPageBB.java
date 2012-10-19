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
import se.baxemyr.filehostingsite.core.HostedFileDatabase;
import se.baxemyr.filehostingsite.core.UserManager;

/**
 *
 * @author Gustav
 */
@RequestScoped //?
@Named("userpageBB")
public class UserPageBB {
    private HostedFileDatabase hostedFileDB = HostedFileDatabase.newInstance("filehosting_pu");
    
    public UserPageBB() {
        
    }
    
    public List<HostedFile> getAll() {
        List<HostedFile> filelist = new ArrayList<>();
//        filelist.addAll(hostedFileDB.getFilesFromOwner(UserManager.getInstance().getCurrentUser()));  //Orsakar exception
        filelist.add(hostedFileDB.find(1L)); //endast tills vidare
        return filelist;
    }
    
}
