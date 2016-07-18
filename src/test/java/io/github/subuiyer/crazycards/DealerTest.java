package io.github.subuiyer.crazycards;


import io.github.subuiyer.crazycards.util.DefaultCardDeckStore;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class DealerTest 
{
    
    private Dealer dealer = null;
    
    @Before
    public void setup() throws InstantiationException
    {
        dealer = Dealer.getInstance(new DefaultCardDeckStore());
        dealer.clearAll();
    }
    
//    @Test(expected=InstantiationException.class)
//    public void testDealer_cst() throws InstantiationException
//    {
//        Dealer dealer_invalid = Dealer.getInstance(null);
//    }
    
    @Test
    public void testCreateDeck() throws InstantiationException
    {
        Assert.assertTrue(dealer.createDeck("blah"));
    }
    
    @Test
    public void testCreateDeck_noName()
    {
        Assert.assertFalse(dealer.createDeck(null));
        Assert.assertFalse(dealer.createDeck(""));
    }
    
    @Test
    public void testCreateDeck_repeatName()
    {
        dealer.createDeck("blah");
        Assert.assertFalse(dealer.createDeck("blah"));
    }
    
    @Test
    public void testGetDeckNames_none()
    {
        Assert.assertTrue(dealer.getDeckNames().size() == 0);
    }
    
    @Test
    public void testGetDeckNames()
    {
        dealer.createDeck("red");
        dealer.createDeck("blue");
        dealer.createDeck("green");
        List names = dealer.getDeckNames();
        Assert.assertTrue(names.size() == 3);
        Assert.assertTrue(names.contains("red"));
        Assert.assertTrue(names.contains("blue"));
        Assert.assertTrue(names.contains("green"));
    }
    
    @Test
    public void testDeckExists()
    {
        dealer.createDeck("111");
        dealer.createDeck("222");
        Assert.assertTrue(dealer.deckExists("111"));
    }
    
    @Test
    public void testDeckExists_none()
    {
        dealer.createDeck("111");
        dealer.createDeck("222");
        Assert.assertFalse(dealer.deckExists("noSuchDeck"));
        Assert.assertFalse(dealer.deckExists(null));
        Assert.assertFalse(dealer.deckExists(""));
    }
    
    @Test
    public void testdeleteDeck()
    {
        dealer.createDeck("111");
        dealer.createDeck("222");
        Assert.assertTrue(dealer.deleteDeck("111"));
    }
    
    @Test
    public void testdeleteDeck_none()
    {
        dealer.createDeck("111");
        dealer.createDeck("222");
        Assert.assertFalse(dealer.deleteDeck("noSuchDeck"));
        Assert.assertFalse(dealer.deleteDeck(null));
        Assert.assertFalse(dealer.deleteDeck(""));
    }
    
    @Test
    public void testGetDeck_invalid()
    {
        Assert.assertNull(dealer.getDeck(null));
        Assert.assertNull(dealer.getDeck(""));
    }
    
    @Test
    public void testGetDeck()
    {
        dealer.createDeck("blue");
        Assert.assertNotNull(dealer.getDeck("blue"));
    }
    
    @Test
    public void testGetDeck_none()
    {
        dealer.createDeck("blue");
        Assert.assertNull(dealer.getDeck("red"));
    }
    
}
