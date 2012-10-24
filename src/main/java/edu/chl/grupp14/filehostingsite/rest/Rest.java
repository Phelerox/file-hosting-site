
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package edu.chl.grupp14.filehostingsite.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import edu.chl.grupp14.filehostingsite.core.db.DatabaseManager;
import edu.chl.grupp14.filehostingsite.core.db.DatabaseManager;
import edu.chl.grupp14.filehostingsite.core.entities.HostedFile;
import edu.chl.grupp14.filehostingsite.core.entities.HostedFile;
import edu.chl.grupp14.filehostingsite.core.db.HostedFileDatabase;
import edu.chl.grupp14.filehostingsite.core.db.HostedFileDatabase;

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
