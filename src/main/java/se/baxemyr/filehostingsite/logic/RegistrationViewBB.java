/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.logic;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import se.baxemyr.filehostingsite.core.DatabaseManager;
import se.baxemyr.filehostingsite.core.HostedFileDatabase;
import se.baxemyr.filehostingsite.core.UserDatabase;

/**
 *
 * @author anders
 */
@Named("registrationviewBB")
@ConversationScoped
public class RegistrationViewBB implements Serializable{
   @Inject
   private Conversation conversation;
   private HostedFileDatabase userHostedFileDB;
   
   public RegistrationViewBB(){  
   }
   
   public void submit() throws IOException {
        //Save in DB.
        //UserDatabase userDB = DatabaseManager.INSTANCE.getUserDatabase();
        
        
        //Skickar vidare till Userpage
        //try{
        //    return "userPage?faces-redirect=true";
        //}catch(Exception e){
        //    return null;
        //}
    }
   
}
