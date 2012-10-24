package edu.chl.grupp14.filehostingsite.backingbean;

import edu.chl.grupp14.filehostingsite.core.*;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@RequestScoped //?
@Named("userpageBB")
public class UserPageBB {

    private HostedFileDatabase hostedFileDB = DatabaseManager.INSTANCE.getHostedFileDatabase();
    private UserDatabase userDB = DatabaseManager.INSTANCE.getUserDatabase();
    private String username;

    public UserPageBB() {
        username = getRemoteUser();
    }

    private String getRemoteUser() {
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
        return userDB.find(username).getGroupList();
    }
}
