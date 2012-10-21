package se.baxemyr.filehostingsite.logic.login;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named("auth")
@RequestScoped
public class AuthBB {

    private static final Logger log = Logger.getLogger(AuthBB.class.getName());

    public String logout() {
        String result = "/orderedLists?faces-redirect=true";

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.logout();
        } catch (ServletException e) {
            log.log(Level.SEVERE, "Failed to logout user!", e);
            result = "/login/loginError?faces-redirect=true";
        }

        return result;
    }
}
