package se.baxemyr.filehostingsite.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import se.baxemyr.filehostingsite.logic.UserAuthentication;
import se.baxemyr.filehostingsite.logic.login.SubjectGroup;

/**
 *
 * @author Marco Baxemyr
 */
@Entity
@Table(name = "APPUSER")
public class AppUser implements Serializable {


    @Id
    @Column(nullable=false, name="ID")
    private String id;
    private String fullName;
    private boolean isAdmin;
    private String email;
    @Column(nullable=false)
    private String password;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date regDate;
    private String hhash; //SHA512 hash of salt + password
    private byte[] salt; //Unique per-user per-password
    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<HostedFile> userHostedFiles;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "SUBJECT_GROUP")
    @Enumerated(EnumType.STRING)
    private final List<SubjectGroup> groups = new ArrayList<>();

    public AppUser() {
        this.regDate = new Date();
    }

    public AppUser(String userName, String fullName, String email, String password, SubjectGroup group) {
        this.groups.add(group);
        this.id = userName;
        this.fullName = fullName;
        this.email = email;
        this.regDate = new Date();
        this.password = password;
        UserAuthentication.changePassword(this, password);
    }

    public String getId() {
        return id;  //id
    }

    public String getUserName() {
        return id;
    }

    public void setId(String userName) {
        this.id = userName;
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
        return hhash;
    }

    public void setHash(String password_hash) {
        this.hhash = password_hash;
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String pw) {
        this.password = pw;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AppUser other = (AppUser) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int rhash = 3;
        rhash = 29 * rhash + (this.id != null ? this.id.hashCode() : 0);
        rhash = 29 * rhash + (this.password != null ? this.password.hashCode() : 0);
        return rhash;
    }
}
