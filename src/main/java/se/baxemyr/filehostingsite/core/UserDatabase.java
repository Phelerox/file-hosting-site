package se.baxemyr.filehostingsite.core;

/**
 *
 * @author Marco
 */
public class UserDatabase extends AbstractDAO<User, Long> {

    private UserDatabase(String puName) {
        super(User.class, puName);
    }
    
    public static UserDatabase newInstance(String puName){
        return new UserDatabase(puName);
    }
}
