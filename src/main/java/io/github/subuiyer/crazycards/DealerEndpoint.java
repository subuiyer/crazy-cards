package io.github.subuiyer.crazycards;


import io.github.subuiyer.crazycards.util.CardDeck;
import io.github.subuiyer.crazycards.util.CardDeckStore;
import io.github.subuiyer.crazycards.util.DefaultCardDeckStore;
import io.github.subuiyer.crazycards.util.ResponseMessage;
import io.github.subuiyer.crazycards.util.Shuffler;
import io.github.subuiyer.crazycards.util.SimpleRandomShuffler;
import java.util.List;
import java.util.Properties;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * This is the Dealer REST API endpoint. Mostly, this is a wrapper around the 
 * Dealer functionality with standard REST responses. 
 * 
 * @author Subu Iyer
 */
@Path("dealer")
public class DealerEndpoint 
{
    
    private Dealer dealer = null;
   
    /**
     * This gets the Dealer instance used by this app. It registers the right store and 
     * shuffling mechanism that are defined in the configuration file.
     * In case of any error loading the configuration or creating the mechanisms,
     * the default ones are used - DefaultCardDeckStore and SimpleRandomShuffler.
     * 
     * @throws InstantiationException 
     */
    public DealerEndpoint() throws InstantiationException
    {
        CardDeckStore store = null;
        Shuffler shuffler = null;
        try
        {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
            store = (CardDeckStore)Class.forName(properties.getProperty("store")).newInstance();
            shuffler = (Shuffler)Class.forName(properties.getProperty("shuffler")).newInstance();
        }
        catch(Exception e)
        {
            store = new DefaultCardDeckStore();
            shuffler = new SimpleRandomShuffler();
        }
        dealer = Dealer.getInstance(store);
        dealer.setShuffler(shuffler);
    }
    
    /**
     * Setter... mostly just used in unit tests at this point.
     * 
     * @param dealer 
     */
    public void setDealer(Dealer dealer)
    {
        this.dealer = dealer;
    }
    
    
    /**
     * Returns information about this app.
     * 
     * @return 
     */
    @GET
    @Path("/info")
    @Produces(MediaType.APPLICATION_JSON)
    public Response info() 
    {
        ResponseMessage message = new ResponseMessage(
            ResponseMessage.STATUS_SUCCESS, null, 
            "name:crazy-cards;shuffler:" + dealer.getShufflerClassName());
        return Response.status(Response.Status.OK).entity(message).build();
    }
    
    /**
     * GET Method to return list of card deck names.
     * 
     * @return Response with message containing status and list of deck names
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response listDecks()
    {
        List<String> listNames = dealer.getDeckNames();
        ResponseMessage message = new ResponseMessage(
                ResponseMessage.STATUS_SUCCESS, listNames, null);
        
        return Response.status(Response.Status.OK).entity(message).build();
    }
    
    
    /**
     * PUT method to create a new named deck.
     * 
     * @param name
     * @return Response with status (success if new deck is created)
     */
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
   
    
    /**
     * GET the card deck with the given name.
     * 
     * @param name
     * @return Response with the cards in the current sort/shuffled order 
     */
    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeck(@PathParam("name") String name)
    {
        CardDeck deck = dealer.getDeck(name);
        Response response = null;
        ResponseMessage message = null;
        
        if(deck == null)
        {
            message = new ResponseMessage(ResponseMessage.STATUS_SUCCESS, null, null);
            response = Response.status(Response.Status.OK).entity(message).build();
        }
        else
        {
            message = new ResponseMessage(ResponseMessage.STATUS_SUCCESS, deck.getCards(), null);
            response = Response.status(Response.Status.OK).entity(message).build();
        }
            
        return response;
    }
    
    
    /**
     * POST request to shuffle the named deck.
     * 
     * @param name
     * @return Response with the status - success if the given deck is found and shuffled 
     */
    @POST
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response shuffleDeck(@PathParam("name") String name) 
    {
        boolean result = dealer.shuffle(name);
        Response response = null;
        
        if(result == true)
        {
            response = Response.status(Response.Status.OK).entity(
                new ResponseMessage(ResponseMessage.STATUS_SUCCESS, null, null)).build();
        }
        else
        {
            response = Response.status(Response.Status.OK).entity(
                new ResponseMessage(ResponseMessage.STATUS_FAIL, null, null)).build();
        }
        
        return response; 
    }
    
    
    /**
     * DELETE the given deck.
     * 
     * @param name
     * @return Response with status - success if deck is deleted 
     */
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
