package io.github.subuiyer.crazycards;


import io.github.subuiyer.crazycards.util.DefaultCardDeckStore;
import io.github.subuiyer.crazycards.util.ResponseMessage;
import java.util.List;
import javax.ws.rs.DELETE;
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
    
    
    public void setDealer(Dealer dealer)
    {
        this.dealer = dealer;
    }
    
    
    @GET
    @Path("/info")
    @Produces(MediaType.APPLICATION_JSON)
    public Response info() 
    {
        ResponseMessage message = new ResponseMessage(
                ResponseMessage.STATUS_SUCCESS, null, "name:crazy-cards");
        return Response.status(Response.Status.OK).entity(message).build();
    }
    
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response listDecks()
    {
        List<String> listNames = dealer.getDeckNames();
        ResponseMessage message = new ResponseMessage(
                ResponseMessage.STATUS_SUCCESS, listNames, null);
        
        return Response.status(Response.Status.OK).entity(message).build();
    }
    
    
    @PUT
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDeck(@PathParam("name") String name)
    {
        Response response = null;
        ResponseMessage message = null;
        
        if(dealer.deckExists(name) == false)
        {
            boolean result = dealer.createDeck(name);
            if(result == true)
            {
                message = new ResponseMessage(ResponseMessage.STATUS_SUCCESS, null, "New deck created");
                response = Response.status(Response.Status.CREATED).entity(message).build();
            }
            else
            {
                message = new ResponseMessage(ResponseMessage.STATUS_FAIL, null, "New deck not created");
                response = Response.status(Response.Status.CONFLICT).entity(message).build();
            }
        }
        else
        {
            message = new ResponseMessage(ResponseMessage.STATUS_SUCCESS, null, "Deck not created, deck exists already");
            response = Response.status(Response.Status.OK).entity(message).build();
        }
        
        return response;
    }
    
    
    @DELETE
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDeck(@PathParam("name") String name) 
    {       
        boolean result = dealer.deleteDeck(name);
        Response response = null;
        ResponseMessage message = null;
        
        if(result == true)
        {
            message = new ResponseMessage(ResponseMessage.STATUS_SUCCESS, null, "Deck deleted");
            response = Response.status(Response.Status.OK).entity(message).build();
        }
        else
        {
            message = new ResponseMessage(ResponseMessage.STATUS_FAIL, null, "Deck not deleted");
            response = Response.status(Response.Status.ACCEPTED).entity(message).build();
        }
        
        return response;
    }
    
    
    
}
