package se.baxemyr.filehostingsite.core;


/**
 * Singleton
 * @author Marco
 */
public enum DatabaseManager {

    INSTANCE;
    private AbstractHostedFileDatabase userHostedFileDatabase;
    private UserDatabase userDatabase;

    private DatabaseManager() {
        userHostedFileDatabase = AbstractHostedFileDatabase.newInstance("filehosting_pu");
        userDatabase = UserDatabase.newInstance("filehosting_pu");
    }
    
    private DatabaseManager(String pu) {
        userHostedFileDatabase = AbstractHostedFileDatabase.newInstance(pu);
    }  
    
    public AbstractHostedFileDatabase getUserHostedFileDatabase() {
        return userHostedFileDatabase;
    }
    
    public UserDatabase getUserDatabase() {
        return userDatabase;
    }
    
}
