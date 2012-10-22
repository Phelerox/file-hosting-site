package se.baxemyr.filehostingsite.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import se.baxemyr.filehostingsite.logic.UserAuthentication;

/**
 *
 * @author Marco Baxemyr
 */
@Entity
@Table(name = "APPUSER")
public class AppUser implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    @Id
    @Column(nullable=false, name="ID")
    private String id;
    private String fullName;
    private boolean isAdmin;
    private String email;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date regDate;
    @Column(nullable=false, name = "PASSWORD")
    private String passwordHash; //SHA512 hash of salt + password
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password_hash) {
        this.passwordHash = password_hash;
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
        if ((this.passwordHash == null) ? (other.passwordHash != null) : !this.passwordHash.equals(other.passwordHash)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int rhash = 3;
        rhash = 29 * rhash + (this.id != null ? this.id.hashCode() : 0);
        rhash = 29 * rhash + (this.passwordHash != null ? this.passwordHash.hashCode() : 0);
        return rhash;
    }
}
