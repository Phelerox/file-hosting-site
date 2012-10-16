package se.baxemyr.filehostingsite.core;


/**
 * Singleton
 * @author Marco
 */
public enum DatabaseManager {

    INSTANCE;
    private UserHostedFileDatabase userHostedFileDatabase;
    private UserDatabase userDatabase;

    private DatabaseManager() {
        userHostedFileDatabase = UserHostedFileDatabase.newInstance("filehosting_pu");
        userDatabase = UserDatabase.newInstance("filehosting_pu");
    }
    
    private DatabaseManager(String pu) {
        userHostedFileDatabase = UserHostedFileDatabase.newInstance(pu);
    }  
    
    public UserHostedFileDatabase getUserHostedFileDatabase() {
        return userHostedFileDatabase;
    }
    
    public UserDatabase getUserDatabase() {
        return userDatabase;
    }
    
}
