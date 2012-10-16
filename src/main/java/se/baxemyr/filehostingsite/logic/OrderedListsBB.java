/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.baxemyr.filehostingsite.logic;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import se.baxemyr.filehostingsite.core.HostedFile;
import se.baxemyr.filehostingsite.core.HostedFileDatabase;

/**
 *
 * @author anders
 */

@RequestScoped //?
@Named("orderedlistsBB")
public class OrderedListsBB {
    
    private HostedFileDatabase hostedFileDB = HostedFileDatabase.newInstance("filehosting_pu");
    
    public OrderedListsBB(){
        
    }
    
    public List<HostedFile> getLatestFiles(){
        List<HostedFile> filelist = new ArrayList<>();
        filelist.addAll(hostedFileDB.getLatestFiles());
        return filelist;
    }
    
    public List<HostedFile> getMostDownloaded(){
        List<HostedFile> filelist = new ArrayList<>();
        filelist.addAll(hostedFileDB.getMostDownloaded());
        return filelist;
    }
}
