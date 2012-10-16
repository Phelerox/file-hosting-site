package se.baxemyr.filehostingsite.core;


/**
 * Singleton
 * @author Marco
 */
public enum DatabaseManager {

    INSTANCE;
    private HostedFileDatabase userHostedFileDatabase;
    private UserDatabase userDatabase;

    private DatabaseManager() {
        userHostedFileDatabase = HostedFileDatabase.newInstance("filehosting_pu");
        userDatabase = UserDatabase.newInstance("filehosting_pu");
    }
    
    private DatabaseManager(String pu) {
        userHostedFileDatabase = HostedFileDatabase.newInstance(pu);
    }  
    
    public HostedFileDatabase getUserHostedFileDatabase() {
        return userHostedFileDatabase;
    }
    
    public UserDatabase getUserDatabase() {
        return userDatabase;
    }
    
}
