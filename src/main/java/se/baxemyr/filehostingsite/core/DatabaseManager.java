package se.baxemyr.filehostingsite.core;



/**
 * Singleton
 * @author Marco
 */
public enum DatabaseManager {

    INSTANCE;
    private HostedFileDatabase hostedFileDatabase;
    private UserDatabase userDatabase;
    private CommentDatabase commentDatabase;

    private DatabaseManager() {
        hostedFileDatabase = HostedFileDatabase.newInstance("filehosting_pu");
        userDatabase = UserDatabase.newInstance("filehosting_pu");
        commentDatabase = CommentDatabase.newInstance("filehosting_pu");
        
        addAdmin();
    }
    
    private void addAdmin(){
        try{
        AppUser admin = new AppUser("admin","admin", "admin@admin.com", "admin", SubjectGroup.ADMIN);
        if(!(userDatabase.find("admin").getUserName().equals(admin.getUserName()))){
           userDatabase.add(admin); 
        }
        }catch(Exception e){
        }
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
    
    public CommentDatabase getCommentDatabase(){
        return commentDatabase;
    }
}
