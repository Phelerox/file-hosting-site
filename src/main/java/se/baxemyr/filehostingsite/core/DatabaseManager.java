package se.baxemyr.filehostingsite.core;


/**
 * Singleton
 * @author Marco
 */
public enum DatabaseManager {

    INSTANCE;
    private HostedFileDatabase hostedFileDatabase;
    private UserDatabase userDatabase;

    private DatabaseManager() {
        hostedFileDatabase = HostedFileDatabase.newInstance("filehosting_pu");
        userDatabase = UserDatabase.newInstance("filehosting_pu");
    }
    
    private DatabaseManager(String pu) {
        hostedFileDatabase = HostedFileDatabase.newInstance(pu);
    }  
    
    public HostedFileDatabase getHostedFileDatabase() {
        return hostedFileDatabase;
    }
    
    public UserDatabase getUserDatabase() {
        return userDatabase;
    }
    
}
