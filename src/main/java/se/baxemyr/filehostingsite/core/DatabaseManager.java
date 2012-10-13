package se.baxemyr.filehostingsite.core;


/**
 * Singleton
 * @author Marco
 */
public enum DatabaseManager {

    INSTANCE;
    private UserHostedFileDatabase userHostedFileDatabase;

    private DatabaseManager() {
        userHostedFileDatabase = UserHostedFileDatabase.newInstance("filehosting_pu");
    }
    
    private DatabaseManager(String pu) {
        userHostedFileDatabase = UserHostedFileDatabase.newInstance(pu);
    }  
    
    public UserHostedFileDatabase getUserHostedFileDatabase() {
        return userHostedFileDatabase;
    }
}
