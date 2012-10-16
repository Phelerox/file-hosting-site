/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.logic;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import se.baxemyr.filehostingsite.core.AbstractHostedFile;
import se.baxemyr.filehostingsite.core.UserHostedFile;
import se.baxemyr.filehostingsite.core.UserHostedFileDatabase;
import se.baxemyr.filehostingsite.core.UserManager;

/**
 *
 * @author Gustav
 */
@RequestScoped //?
@Named("userpageBB")
public class UserPageBB {
    private UserHostedFileDatabase userHostedFileDB = UserHostedFileDatabase.newInstance("filehosting_pu");
    
    public UserPageBB() {
        
    }
    
    public List<UserHostedFile> getAll() {
        List<UserHostedFile> filelist = new ArrayList<>();
        filelist.addAll(userHostedFileDB.getFilesFromOwner(UserManager.getInstance().getCurrentUser())); 
        filelist.add(userHostedFileDB.getFile(1L)); //endast tills vidare
        return filelist;
    }
}
