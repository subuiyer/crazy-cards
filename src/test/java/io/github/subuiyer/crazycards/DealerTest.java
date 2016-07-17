package io.github.subuiyer.crazycards;

import org.junit.Assert;
import org.junit.Test;


public class DealerTest 
{
    
    @Test
    public void testCreateDeck()
    {
        Dealer dealer = new Dealer();
        Assert.assertTrue(dealer.createDeck("blah"));
    }
    
    @Test
    public void testCreateDeck_noName()
    {
        Dealer dealer = new Dealer();
        Assert.assertFalse(dealer.createDeck(null));
        Assert.assertFalse(dealer.createDeck(""));
    }
    
    @Test
    public void testCreateDeck_repeatName()
    {
        Dealer dealer = new Dealer();
        dealer.createDeck("blah");
        Assert.assertFalse(dealer.createDeck("blah"));
    }
    
}
