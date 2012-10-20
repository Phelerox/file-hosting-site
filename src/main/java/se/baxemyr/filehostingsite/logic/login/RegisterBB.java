package se.baxemyr.filehostingsite.logic.login;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import se.baxemyr.filehostingsite.core.AppUser;
import se.baxemyr.filehostingsite.core.DatabaseManager;
import se.baxemyr.filehostingsite.core.UserDatabase;

@Named("register")
@RequestScoped
public class RegisterBB {

    @NotNull
    private String login;
    @NotNull
    private String passwd;
   
    UserDatabase udb = DatabaseManager.INSTANCE.getUserDatabase();
    private static final Logger log = Logger.getLogger(RegisterBB.class.getName());

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String action() {
        log.log(Level.INFO, "New Customer Login {0} Passwd {1}", new Object[]{login, passwd});
        try {
            udb.add(new AppUser(login,"fullName","mail@mail.com", passwd, SubjectGroup.USER));
            return "login";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bad Login name"));
            return null; // Same page
        }
    }
}
