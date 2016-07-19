package io.github.subuiyer.crazycards.util;

import org.junit.Assert;
import org.junit.Test;



public class HandShufflerTest 
{
    
    @Test
    public void testShuffle_null()
    {
       HandShuffler shuffler = new HandShuffler();
       Assert.assertNull(shuffler.shuffle(null));
    }
    
    @Test
    public void testShuffle()
    {
       HandShuffler shuffler = new HandShuffler();
       CardDeck deckUnshuffled = new CardDeck();
       CardDeck deckShuffled = shuffler.shuffle(new CardDeck());
       Assert.assertNotNull(deckShuffled);
       Assert.assertFalse(deckUnshuffled.isSimilar(deckShuffled));
    }
    
}
