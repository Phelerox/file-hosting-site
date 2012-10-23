
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import se.baxemyr.filehostingsite.core.DatabaseManager;
import se.baxemyr.filehostingsite.core.DatabaseManager;
import se.baxemyr.filehostingsite.core.HostedFile;
import se.baxemyr.filehostingsite.core.HostedFile;
import se.baxemyr.filehostingsite.core.HostedFileDatabase;
import se.baxemyr.filehostingsite.core.HostedFileDatabase;

/**
*
* @author Sam
*/
@Path("file")
public class Rest {
    private HostedFileDatabase userHostedFileDB = DatabaseManager.INSTANCE.getHostedFileDatabase();
    
    public Rest(){
        
    }
    
    //Har bara testat med en .txt fil
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFileById(@PathParam("id") Long id) {
        HostedFile uhf = this.userHostedFileDB.find(id);
        uhf.download();
        userHostedFileDB.update(uhf);
        return Response.ok(uhf.getBytes()).header("content-disposition","attachment; filename = "+uhf.getFilename()).build();
    }
}