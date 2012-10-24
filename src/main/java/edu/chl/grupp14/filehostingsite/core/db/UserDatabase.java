package edu.chl.grupp14.filehostingsite.core.db;

import edu.chl.grupp14.filehostingsite.core.entities.AppUser;

public class UserDatabase extends AbstractDAO<AppUser, String> {

    private UserDatabase(String puName) {
        super(AppUser.class, puName);
    }
    
    public static UserDatabase newInstance(String puName){
        return new UserDatabase(puName);
    }
}
