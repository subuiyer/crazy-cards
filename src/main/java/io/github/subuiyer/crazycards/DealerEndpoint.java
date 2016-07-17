package io.github.subuiyer.crazycards;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("dealer")
public class DealerEndpoint 
{
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response info() 
    {
        String info = "{ \"name\": \"crazy-cards\" }";
        return Response.ok(info, MediaType.APPLICATION_JSON).build();
    }
    
    
}
