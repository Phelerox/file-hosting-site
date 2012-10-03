package se.baxemyr.filehostingsite.core;

import java.util.Date;

/**
 *
 * @author Marco Baxemyr
 */
public class User {

    private long id;
    private String userName;
    private String fullName;
    private String pwd_hash;
    private Date regDate;
    
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

    public String getPwdHash() {
        return pwd_hash;
    }

    public void setPwdHash(String password_hash) {
        this.pwd_hash = password_hash;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    
    
}
