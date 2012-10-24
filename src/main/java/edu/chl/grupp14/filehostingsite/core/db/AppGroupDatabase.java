
package edu.chl.grupp14.filehostingsite.core.db;

import edu.chl.grupp14.filehostingsite.core.entities.AppGroup;

public class AppGroupDatabase extends AbstractDAO<AppGroup, String> {
    public AppGroupDatabase(String puName){
        super(AppGroup.class, puName);
    }
    
    public static AppGroupDatabase newInstance(String puName){
        return new AppGroupDatabase(puName);
    }
    
}
