/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chs.edu.grupp14.filehostingsite.backingbean;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import chs.edu.grupp14.filehostingsite.core.AppGroup;
import chs.edu.grupp14.filehostingsite.core.AppGroupDatabase;
import chs.edu.grupp14.filehostingsite.core.AppUser;
import chs.edu.grupp14.filehostingsite.core.DatabaseManager;
import chs.edu.grupp14.filehostingsite.core.HostedFile;
import chs.edu.grupp14.filehostingsite.core.HostedFileDatabase;
import chs.edu.grupp14.filehostingsite.core.UserDatabase;

/**
 *
 * @author Gustav
 */
@RequestScoped //?
@Named("userpageBB")
public class UserPageBB {

    private HostedFileDatabase hostedFileDB = DatabaseManager.INSTANCE.getHostedFileDatabase();
    private UserDatabase userDB = DatabaseManager.INSTANCE.getUserDatabase();
    private AppGroupDatabase appGroupDB = DatabaseManager.INSTANCE.getAppGroupDatabase();
    private String username;

    public UserPageBB() {
        username = getRemoteUser();
    }

    public String getRemoteUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.getRemoteUser();
    }

    public List<HostedFile> getAll() {
        List<HostedFile> filelist = new ArrayList<>();
        if(username != null){
            AppUser user = userDB.find(username);
            if (user != null) {
                filelist.addAll(hostedFileDB.getFilesFromOwner(user));
            }
        }
        return filelist;
    }

    public List<AppGroup> getAllGroups() {
        /*List<AppGroup> grouplist = new ArrayList<>();
        if(username != null){
            AppUser user = userDB.find(username);
            if (user != null) {
                grouplist.addAll(user.getGroupList());
            }
        }*/
        
        return userDB.find(username).getGroupList();
    }
}
