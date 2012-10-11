package se.baxemyr.filehostingsite.core;

import java.util.Date;
import se.baxemyr.filehostingsite.logic.UserAuthentication;

/**
 *
 * @author Marco Baxemyr
 */
public class User {

    private long id;
    private String userName;
    private String fullName;
    private boolean isAdmin;
    private String email;
    private Date regDate;
    private String hash; //SHA512 hash of salt + password
    private byte[] salt; //Unique per-user per-password
        
    public User() {
        this.regDate = new Date();
    }
    
    public User(long id, String userName, String fullName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.regDate = new Date();
        UserAuthentication.changePassword(this, password);
    }
    
    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public boolean isAdmin() {
        return isAdmin;
    }
    
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String password_hash) {
        this.hash = password_hash;
    }
    
    public byte[] getSalt() {
            return salt;
    }
    
    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    
        public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
