package io.github.subuiyer.crazycards.util;


import org.junit.Assert;
import org.junit.Test;


public class CardTest 
{

    @Test
    public void testToString()
    {
        Card card = new Card("suitA", "rank1");
        Assert.assertEquals("rank1-suitA", card.toString());
    }
    
    @Test
    public void testToString_blank()
    {
        Card card = new Card();
        Assert.assertEquals("null-null", card.toString());
    }
}
