/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.logic;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import se.baxemyr.filehostingsite.core.AppUser;
import se.baxemyr.filehostingsite.core.DatabaseManager;
import se.baxemyr.filehostingsite.core.HostedFile;
import se.baxemyr.filehostingsite.core.HostedFileDatabase;
import se.baxemyr.filehostingsite.core.UserDatabase;

/**
 *
 * @author Gustav
 */
@RequestScoped //?
@Named("userpageBB")
public class UserPageBB {
    private HostedFileDatabase hostedFileDB = DatabaseManager.INSTANCE.getHostedFileDatabase();
    private UserDatabase userDB = DatabaseManager.INSTANCE.getUserDatabase();
    
    public UserPageBB() {
        
    }
    
    public List<HostedFile> getAll() {
        List<HostedFile> filelist = new ArrayList<>();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String username = request.getRemoteUser();
        if (username != null) {    
            UserDatabase userDB = DatabaseManager.INSTANCE.getUserDatabase();
            AppUser user = userDB.find(username);
            if (user != null) {
                filelist.addAll(hostedFileDB.getFilesFromOwner(user));
            }
        }
        return filelist;
    }
    
}
