/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.logic;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import se.baxemyr.filehostingsite.core.AppGroup;
import se.baxemyr.filehostingsite.core.AppGroupDatabase;
import se.baxemyr.filehostingsite.core.DatabaseManager;

/**
 *
 * @author Sam
 */



@Named("displayGroup")
@RequestScoped
public class DisplayGroupBB {
    private AppGroup group;
    private AppGroupDatabase groupDB = DatabaseManager.INSTANCE.getAppGroupDatabase();
    
    public DisplayGroupBB(){
        
    }

    public AppGroup getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = groupDB.find(group);
    }
}
