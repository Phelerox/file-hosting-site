package edu.chl.grupp14.filehostingsite.core;


public enum DatabaseManager {

    INSTANCE;
    private HostedFileDatabase hostedFileDatabase;
    private UserDatabase userDatabase;
    private CommentDatabase commentDatabase;
    private AppGroupDatabase appGroupDatabase;

    private DatabaseManager() {
        hostedFileDatabase = HostedFileDatabase.newInstance("filehosting_pu");
        userDatabase = UserDatabase.newInstance("filehosting_pu");
        commentDatabase = CommentDatabase.newInstance("filehosting_pu");
        appGroupDatabase = AppGroupDatabase.newInstance("filehosting_pu");
        
        addAdmin();
    }

    
    
    private void addAdmin(){
        AppUser admin = new AppUser("admin","admin", "admin@admin.com", "admin", SubjectGroup.ADMIN);
        try{
        if(userDatabase.find(admin.getUserName())==null ) {
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
    public AppGroupDatabase getAppGroupDatabase() {
        return appGroupDatabase;
    }
}
