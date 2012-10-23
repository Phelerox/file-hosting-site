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
import se.baxemyr.filehostingsite.core.AppGroup;
import se.baxemyr.filehostingsite.core.AppGroupDatabase;
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
    private AppGroupDatabase appGroupDB = DatabaseManager.INSTANCE.getAppGroupDatabase();
    private String groupname;
    private String username;

    public UserPageBB() {
        getRemoteUser();
    }

    public void getRemoteUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        username = request.getRemoteUser();
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public void createGroup() { 
        //if (username != null) {
           AppUser user = userDB.find(username);
       
           AppGroup group = new AppGroup(this.groupname, user);
           group.addMember(user);
           appGroupDB.add(group);
        //}
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
