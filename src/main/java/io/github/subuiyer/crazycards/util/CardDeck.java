package io.github.subuiyer.crazycards.util;

import java.util.ArrayList;
import java.util.List;


/**
 * A card deck consists of a bunch of cards (Card instances). The default constructor 
 * creates a deck (List) of cards. This implementation puts together a standard set 
 * of 52 cards.
 * 
 * Other implementations that need a different set of cards in the deck would just 
 * extend this class and override the create() method. 
 * 
 * @author Subu Iyer
 */
public class CardDeck 
{
    public static final int NUM_CARDS_STANDARD = 52;
 
    private List<Card> cards = null;
    
    
    public CardDeck()
    {
        create();
    }
    
    
    public void create()
    {
        if(cards != null)
        {
            return;
        }
        
        cards = new ArrayList<>(NUM_CARDS_STANDARD);
        int cardNum = 0;
        for(int suitNum = 0; suitNum < Card.SUITS.length; suitNum++)
        {
            for(int rankNum = 0; rankNum < Card.RANKS.length; rankNum++)
            {
                cards.add(new Card(Card.SUITS[suitNum], Card.RANKS[rankNum]));
            }
        }
    }
    
    
    public List<Card> getCards()
    {
        return cards;
    }
    
    
    public void setCards(List<Card> cards)
    {
        this.cards = cards;
    }
    
    
    public boolean isSimilar(CardDeck deckCompare)
    {
        if(deckCompare == null)
        {
            return false;
        }
        
        List<Card> deckCompareCards = deckCompare.getCards();
        if(cards.size() != deckCompareCards.size())
        {
            return false;
        }
        
        for(int i = 0; i < cards.size(); i++)
        {
            if(cards.get(i).getRankSuit().equals(deckCompareCards.get(i).getRankSuit()) == false)
            {
                return false;
            }
        }
        return true;
    }
    
}
