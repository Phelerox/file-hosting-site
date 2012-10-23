
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
    private HostedFileDatabase hostedFileDB = DatabaseManager.INSTANCE.getHostedFileDatabase();
    
    public Rest(){
        
    }
    
    //Har bara testat med en .txt fil
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFileById(@PathParam("id") Long id) {
        HostedFile hf = this.hostedFileDB.find(id);
        if (hf.isPublic()) {
            hf.download();
            hostedFileDB.update(hf);
            return Response.ok(hf.getBytes()).header("content-disposition","attachment; filename = "+hf.getFilename()).build();
        }
        return Response.noContent().build();
    }
}
