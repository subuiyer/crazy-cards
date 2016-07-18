package io.github.subuiyer.crazycards.util;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;



public class CardDeckTest 
{

    @Test
    public void testGetCards()
    {
        CardDeck deck = new CardDeck();
        List<Card> cards = deck.getCards();
        Assert.assertTrue(cards.size() == CardDeck.NUM_CARDS_STANDARD);
    }
    
    @Test
    public void testGetCards_repeat()
    {
        CardDeck deck = new CardDeck();
        List<Card> cards = deck.getCards();
        deck.create();
        List<Card> cardsRepeat = deck.getCards();
        Assert.assertTrue(cardsRepeat.size() == CardDeck.NUM_CARDS_STANDARD);
        Assert.assertEquals(cards, cardsRepeat);
    }
    
    @Test
    public void testIsSimilar_null()
    {
        CardDeck deck = new CardDeck();
        Assert.assertFalse(deck.isSimilar(null));
    }
    
    @Test
    public void testIsSimilar_unshuffled()
    {
        CardDeck deckOne = new CardDeck();
        CardDeck deckTwo = new CardDeck();
        Assert.assertTrue(deckOne.isSimilar(deckTwo));
    }
    
    @Test
    public void testIsSimilar_missingCards()
    {
        CardDeck deckOne = new CardDeck();
        CardDeck deckTwo = new CardDeck();
        deckTwo.getCards().remove(0);
        Assert.assertFalse(deckOne.isSimilar(deckTwo));
    }
    
    @Test
    public void testIsSimilar_shuffled()
    {
        CardDeck deckOne = new CardDeck();
        SimpleRandomShuffler shuffler = new SimpleRandomShuffler();
        shuffler.shuffle(deckOne);
        CardDeck deckTwo = new CardDeck();
        Assert.assertFalse(deckOne.isSimilar(deckTwo));
    }
    
    @Test
    public void testIsSimilar_shuffleBoth()
    {
        CardDeck deckOne = new CardDeck();
        SimpleRandomShuffler shuffler = new SimpleRandomShuffler();
        shuffler.shuffle(deckOne);
        CardDeck deckTwo = new CardDeck();
        shuffler.shuffle(deckTwo);
        Assert.assertFalse(deckOne.isSimilar(deckTwo));
    }
    
    
}
