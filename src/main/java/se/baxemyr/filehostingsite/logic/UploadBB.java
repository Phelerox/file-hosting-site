/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.logic;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import se.baxemyr.filehostingsite.core.AppUser;
import se.baxemyr.filehostingsite.core.DatabaseManager;
import se.baxemyr.filehostingsite.core.HostedFile;
import se.baxemyr.filehostingsite.core.HostedFileDatabase;
import se.baxemyr.filehostingsite.core.UserDatabase;

/**
 * This backing bean is used to upload new files to the hosting site.
 * @author Gustav, Marco
 */
@Named("upload")
@ConversationScoped
public class UploadBB implements Serializable {
    @Inject
    private Conversation conversation; 
    private UploadedFile file;
    
    private HostedFileDatabase userHostedFileDB;
    
    public UploadBB() {
  
    }
    
    public UploadedFile getFile() {
        return file;
    }
    
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public void actionListener(ActionEvent e) {
        
    }
    
    public String submit() throws IOException {
        String fileName = FilenameUtils.getName(file.getName());
        String contentType = file.getContentType();
        byte[] bytes = file.getBytes();

        HostedFile hostedFile = new HostedFile();
        hostedFile.setFilename(fileName);
        hostedFile.setBytes(bytes);
        
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String username = request.getRemoteUser();
        if (username != null) {    
            UserDatabase userDB = DatabaseManager.INSTANCE.getUserDatabase();
            AppUser user = userDB.find(username);
            if (user != null) {
                hostedFile.setOwner(user);
            }
        }
        
        
        //Save in DB.
        userHostedFileDB = DatabaseManager.INSTANCE.getHostedFileDatabase();
        userHostedFileDB.add(hostedFile);

        //Detta blir onödigt när vi skcikar vidare direkt
        context.addMessage(null, 
            new FacesMessage(String.format("File '%s' of type '%s' successfully uploaded!", fileName, contentType)));
        
        //Skickar vidare till fileview
        try{
            return "userPage?faces-redirect=true";
        }catch(Exception e){
            return null;
        }
    }
}
