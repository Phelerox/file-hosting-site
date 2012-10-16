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
import org.apache.commons.io.FilenameUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import se.baxemyr.filehostingsite.core.DatabaseManager;
import se.baxemyr.filehostingsite.core.UserHostedFile;
import se.baxemyr.filehostingsite.core.AbstractHostedFileDatabase;
import se.baxemyr.filehostingsite.core.UserManager;

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
    
   private AbstractHostedFileDatabase userHostedFileDB;
    
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

        //TODO: bör vara AbstractHostedFile hostedFile = new UserHostedFile();
        UserHostedFile hostedFile = new UserHostedFile(); //TODO: dynamically determine if it should be User or Group hosted, and which user/group
        hostedFile.setFilename(fileName);
        hostedFile.setBytes(bytes);
        
        //Filen sparas inte i databasen om den har en owner??
        //hostedFile.setOwner(UserManager.getInstance().getCurrentUser());
        
        //Save in DB.
        userHostedFileDB = DatabaseManager.INSTANCE.getUserHostedFileDatabase();
        userHostedFileDB.add(hostedFile);

        //Detta blir onödigt när vi skcikar vidare direkt
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(String.format("File '%s' of type '%s' successfully uploaded!", fileName, contentType)));
        
        //Skickar vidare till fileview
        try{
            return "userPage?faces-redirect=true";
        }catch(Exception e){
            return null;
        }
    }
}
