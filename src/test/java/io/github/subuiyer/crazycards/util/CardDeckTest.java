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
    
}
