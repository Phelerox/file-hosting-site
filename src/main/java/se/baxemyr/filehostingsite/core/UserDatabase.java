package se.baxemyr.filehostingsite.core;

/**
 *
 * @author Marco
 */
public class UserDatabase extends AbstractDAO<AppUser, String> {

    private UserDatabase(String puName) {
        super(AppUser.class, puName);
    }
    
    public static UserDatabase newInstance(String puName){
        return new UserDatabase(puName);
    }
}
