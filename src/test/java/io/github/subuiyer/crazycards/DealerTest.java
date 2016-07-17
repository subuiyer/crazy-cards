package io.github.subuiyer.crazycards;

import io.github.subuiyer.crazycards.util.CardDeckStore;
import io.github.subuiyer.crazycards.util.DefaultCardDeckStore;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class DealerTest 
{
    
    private Dealer dealer = null;
    
    @Before
    public void setup() throws InstantiationException
    {
        dealer = new Dealer(new DefaultCardDeckStore());
    }
    
    @Test(expected=InstantiationException.class)
    public void testDealer_cst() throws InstantiationException
    {
        Dealer dealer_invalid = new Dealer(null);
    }
    
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
        Assert.assertTrue(dealer.getDeckNames().length == 0);
    }
    
    @Test
    public void testGetDeckNames()
    {
        dealer.createDeck("red");
        dealer.createDeck("blue");
        dealer.createDeck("green");
        String[] names = dealer.getDeckNames();
        Assert.assertTrue(names.length == 3);
        Assert.assertTrue(Arrays.asList(names).contains("red"));
        Assert.assertTrue(Arrays.asList(names).contains("blue"));
        Assert.assertTrue(Arrays.asList(names).contains("green"));
    }
    
    
    
}
