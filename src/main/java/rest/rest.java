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
import se.baxemyr.filehostingsite.core.HostedFile;
import se.baxemyr.filehostingsite.core.HostedFileDatabase;

/**
 *
 * @author Sam
 */
@Path("file")
public class rest {
    private HostedFileDatabase userHostedFileDB = HostedFileDatabase.newInstance("filehosting_pu");
    
    public rest(){
        
    }
    
    //Har bara testat med en .txt fil
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFileById(@PathParam("id") Long id) {
        HostedFile uhf = this.userHostedFileDB.find(id);
        uhf.download();
        return Response.ok(uhf.getBytes()).header("content-disposition","attachment; filename = "+uhf.getFilename()).build();
    }
}
