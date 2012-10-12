package se.baxemyr.filehostingsite.core;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import se.baxemyr.filehostingsite.logic.UserAuthentication;

/**
 *
 * @author Marco Baxemyr
 */
@Entity
public class User implements IDataObject{

    @Id
    @GeneratedValue
    private Long id;
    
    private String userName;
    private String fullName;
    private boolean isAdmin;
    private String email;
  
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date regDate;
    private String hash; //SHA512 hash of salt + password
    private byte[] salt; //Unique per-user per-password
    
    @OneToMany(mappedBy = "owner")
    private List<UserHostedFile> userHostedFiles;
        
    public User() {
        this.regDate = new Date();
    }
    
    public User(Long id, String userName, String fullName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.regDate = new Date();
        UserAuthentication.changePassword(this, password);
    }
    
    public Long getId() {
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
