package io.github.subuiyer.crazycards;


import io.github.subuiyer.crazycards.util.DefaultCardDeckStore;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("dealer")
public class DealerEndpoint 
{
    
    private Dealer dealer = null;
   
    
    public DealerEndpoint() throws InstantiationException
    {
        dealer = Dealer.getInstance(new DefaultCardDeckStore());
    }
    
    
    @GET
    @Path("/info")
    @Produces(MediaType.APPLICATION_JSON)
    public Response info() 
    {
        String info = "{ \"name\": \"crazy-cards\" }";
        return Response.ok(info, MediaType.APPLICATION_JSON).build();
    }
    
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response listDecks()
    {
        List<String> listNames = dealer.getDeckNames();
        return Response.status(Response.Status.OK).entity(listNames).build();
    }
    
    
    @PUT
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDeck(@PathParam("name") String name)
    {
        Response response = null;
        
        if(dealer.deckExists(name) == false)
        {
            boolean result = dealer.createDeck(name);
            if(result == true)
            {
                response = Response.status(Response.Status.CREATED).entity("Deck created").build();
            }
            else
            {
                response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Deck not created").build();
            }
        }
        else
        {
            response = Response.status(Response.Status.CREATED).entity("Deck not created, deck exists already").build();
        }
        
        return response;
    }
    
    
    
    
}
