package edu.chl.grupp14.filehostingsite.backingbean;

import edu.chl.grupp14.filehostingsite.core.entities.AppGroup;
import edu.chl.grupp14.filehostingsite.core.db.AppGroupDatabase;
import edu.chl.grupp14.filehostingsite.core.db.DatabaseManager;
import edu.chl.grupp14.filehostingsite.core.entities.HostedFile;
import edu.chl.grupp14.filehostingsite.core.db.HostedFileDatabase;
import edu.chl.grupp14.filehostingsite.core.db.UserDatabase;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("displayGroup")
@RequestScoped
public class DisplayGroupBB {
    private AppGroup group;
    private AppGroupDatabase groupDB = DatabaseManager.INSTANCE.getAppGroupDatabase();
    private UserDatabase userDB = DatabaseManager.INSTANCE.getUserDatabase();
    private HostedFileDatabase fileDB = DatabaseManager.INSTANCE.getHostedFileDatabase();
    private String newMember;
    
    public DisplayGroupBB(){
        
    }
 
    public List<HostedFile> getAllFilesFromGroup(){
        return fileDB.getFilesFromGroup(group);
    }
    
    public String getNewMember() {
        return newMember;
    }

    public void setNewMember(String newMember) {
        this.newMember = newMember;
    }

    public void addNewMember(){
        Map<String,String> params;
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = params.get("id");
        AppGroup currentGroup = groupDB.find(id);
        currentGroup.addMember(userDB.find(newMember));
        groupDB.update(currentGroup); 
    }
    
    public AppGroup getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = groupDB.find(group);
    }
}
