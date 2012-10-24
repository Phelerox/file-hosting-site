/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.grupp14.filehostingsite.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import edu.chl.grupp14.filehostingsite.core.AppGroup;
import edu.chl.grupp14.filehostingsite.core.AppGroupDatabase;
import edu.chl.grupp14.filehostingsite.core.AppUser;
import edu.chl.grupp14.filehostingsite.core.DatabaseManager;
import edu.chl.grupp14.filehostingsite.core.HostedFile;
import edu.chl.grupp14.filehostingsite.core.HostedFileDatabase;
import edu.chl.grupp14.filehostingsite.core.UserDatabase;

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
    private boolean isPublic;
    
    private HostedFileDatabase userHostedFileDB;
    private AppGroupDatabase groupDB = DatabaseManager.INSTANCE.getAppGroupDatabase();
    
    public UploadBB() {
  
    }
    
    public UploadedFile getFile() {
        return file;
    }
    
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public boolean getIsPublic() {
        return isPublic;
    }
    
    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
    
    public void actionListener(ActionEvent e) {
    }
    
    public String groupSubmit() throws IOException{
        Map<String,String> params;
        FacesContext context = FacesContext.getCurrentInstance();
        params = context.getExternalContext().getRequestParameterMap();
        String id = params.get("id");
        AppGroup currentGroup = groupDB.find(id);
        
        if(file!=null){
            String fileName = FilenameUtils.getName(file.getName());
            String contentType = file.getContentType();
            byte[] bytes = file.getBytes();
            
            HostedFile hostedFile = new HostedFile();
            hostedFile.setFilename(fileName);
            hostedFile.setBytes(bytes);
            hostedFile.setPublic(true); //Should actually be false, but to get fileView working we set it to true
            hostedFile.setContentType(contentType);
            
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            String username = request.getRemoteUser();
            if (username != null) {    
                UserDatabase userDB = DatabaseManager.INSTANCE.getUserDatabase();
                AppUser user = userDB.find(username);
                if (user != null) {
                    hostedFile.setOwner(user);
                }
            }
            
            if(currentGroup!=null){
                hostedFile.setGroup(currentGroup);
            }
            
             //Save in DB.
            userHostedFileDB = DatabaseManager.INSTANCE.getHostedFileDatabase();
            userHostedFileDB.add(hostedFile);

            context.addMessage(null, 
                new FacesMessage(String.format("File successfully uploaded!")));
            return null;
        }
        context.addMessage(null, new FacesMessage(String.format("Select a file!")));
            return null;
        
    }
    
    public String submit() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (file != null) {
            String fileName = FilenameUtils.getName(file.getName());
            String contentType = file.getContentType();
            byte[] bytes = file.getBytes();
            
            HostedFile hostedFile = new HostedFile();
            hostedFile.setFilename(fileName);
            hostedFile.setBytes(bytes);
            hostedFile.setPublic(isPublic);
            hostedFile.setContentType(contentType);

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

            context.addMessage(null, 
                new FacesMessage(String.format("File successfully uploaded!")));
            return null;
        }
        context.addMessage(null, 
                new FacesMessage(String.format("Select a file!")));
            return null;
        
        
    }
}
