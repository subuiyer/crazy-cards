package io.github.subuiyer.crazycards.util;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;



public class SimpleRandomShufflerTest 
{

    @Test
    public void testShuffle_null()
    {
        SimpleRandomShuffler shuffler = new SimpleRandomShuffler();
        Assert.assertNull(shuffler.shuffle(null));
    }
    
    @Test
    public void testShuffle()
    {
        CardDeck deckUnshuffled = new CardDeck();
        CardDeck deck = new CardDeck();
        SimpleRandomShuffler shuffler = new SimpleRandomShuffler();
        Assert.assertNotNull(shuffler.shuffle(deck));
        Assert.assertFalse(deckUnshuffled.isSimilar(deck));
    }
    
}
