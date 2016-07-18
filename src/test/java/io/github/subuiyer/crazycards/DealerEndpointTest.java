package io.github.subuiyer.crazycards;


import io.github.subuiyer.crazycards.util.CardDeck;
import io.github.subuiyer.crazycards.util.ResponseMessage;
import java.util.ArrayList;
import javax.ws.rs.core.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class DealerEndpointTest 
{

    private Dealer dealerMock = null;
    private DealerEndpoint endpoint = null;
    
    
    @Before
    public void setup() throws InstantiationException
    {
        dealerMock = Mockito.mock(Dealer.class);
        endpoint = new DealerEndpoint();
        endpoint.setDealer(dealerMock);
        
    }
    
    @Test
    public void testInfo()
    {
        Response response = endpoint.info();
        ResponseMessage msg = (ResponseMessage)response.getEntity();
        Assert.assertTrue(msg.getStatus().equals(ResponseMessage.STATUS_SUCCESS));
    }
    
    @Test
    public void testListDecks_empty()
    {
        Mockito.when(dealerMock.getDeckNames()).thenReturn(new ArrayList());
        
        Response response = endpoint.listDecks();
        ResponseMessage msg = (ResponseMessage)response.getEntity();
        Assert.assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        Assert.assertEquals(ResponseMessage.STATUS_SUCCESS, msg.getStatus());
        Assert.assertTrue(msg.getData().isEmpty());
    }
    
    @Test
    public void testListDecks()
    {
        ArrayList<String> listNames = new ArrayList<>();
        listNames.add("red");
        listNames.add("green");
        listNames.add("blue");
        Mockito.when(dealerMock.getDeckNames()).thenReturn(listNames);
        
        Response response = endpoint.listDecks();
        ResponseMessage msg = (ResponseMessage)response.getEntity();
        Assert.assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        Assert.assertEquals(ResponseMessage.STATUS_SUCCESS, msg.getStatus());
        Assert.assertTrue(msg.getData().size() == 3);
    }
    
    @Test
    public void testCreateDeck_exists()
    {
        Mockito.when(dealerMock.deckExists("abc")).thenReturn(true);
        
        Response response = endpoint.createDeck("abc");
        ResponseMessage msg = (ResponseMessage)response.getEntity();
        Assert.assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        Assert.assertTrue(msg.getStatus().equals(ResponseMessage.STATUS_SUCCESS));
        Assert.assertEquals("Deck not created, deck exists already", msg.getMessage());
    }
    
    @Test
    public void testCreateDeck()
    {
        Mockito.when(dealerMock.deckExists("def")).thenReturn(false);
        Mockito.when(dealerMock.createDeck("def")).thenReturn(true);
        
        Response response = endpoint.createDeck("def");
        ResponseMessage msg = (ResponseMessage)response.getEntity();
        Assert.assertTrue(response.getStatus() == Response.Status.CREATED.getStatusCode());
        Assert.assertTrue(msg.getStatus().equals(ResponseMessage.STATUS_SUCCESS));
        Assert.assertEquals("New deck created", msg.getMessage());
    }
    
    @Test
    public void testCreateDeck_fail()
    {
        Mockito.when(dealerMock.deckExists("def")).thenReturn(false);
        Mockito.when(dealerMock.createDeck("def")).thenReturn(false);
        
        Response response = endpoint.createDeck("def");
        ResponseMessage msg = (ResponseMessage)response.getEntity();
        Assert.assertTrue(response.getStatus() == Response.Status.CONFLICT.getStatusCode());
        Assert.assertTrue(msg.getStatus().equals(ResponseMessage.STATUS_FAIL));
        Assert.assertEquals("New deck not created", msg.getMessage());
    }
    
    @Test
    public void testdeleteDeck()
    {
        Mockito.when(dealerMock.deleteDeck("def")).thenReturn(true);
        
        Response response = endpoint.deleteDeck("def");
        ResponseMessage msg = (ResponseMessage)response.getEntity();
        Assert.assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        Assert.assertTrue(msg.getStatus().equals(ResponseMessage.STATUS_SUCCESS));
        Assert.assertEquals("Deck deleted", msg.getMessage());
    }
    
    @Test
    public void testdeleteDeck_fail()
    {
        Mockito.when(dealerMock.deleteDeck("def")).thenReturn(false);
        
        Response response = endpoint.deleteDeck("def");
        ResponseMessage msg = (ResponseMessage)response.getEntity();
        Assert.assertTrue(response.getStatus() == Response.Status.ACCEPTED.getStatusCode());
        Assert.assertTrue(msg.getStatus().equals(ResponseMessage.STATUS_FAIL));
        Assert.assertEquals("Deck not deleted", msg.getMessage());
    }
    
    @Test
    public void testGetDeck_none()
    {
        Mockito.when(dealerMock.getDeck("noDeck")).thenReturn(null);
        
        Response response = endpoint.getDeck("noDeck");
        ResponseMessage msg = (ResponseMessage)response.getEntity();
        Assert.assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        Assert.assertTrue(msg.getStatus().equals(ResponseMessage.STATUS_SUCCESS));
        Assert.assertNull(msg.getData());
        Assert.assertNull(msg.getMessage());
    }
    
    @Test
    public void testGetDeck()
    {
        Mockito.when(dealerMock.getDeck("red")).thenReturn(new CardDeck());
        
        Response response = endpoint.getDeck("red");
        ResponseMessage msg = (ResponseMessage)response.getEntity();
        Assert.assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        Assert.assertTrue(msg.getStatus().equals(ResponseMessage.STATUS_SUCCESS));
        Assert.assertNotNull(msg.getData());
        Assert.assertTrue(msg.getData().size() == 52);
        Assert.assertNull(msg.getMessage());
    }
    
}
