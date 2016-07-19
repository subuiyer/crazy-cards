package io.github.subuiyer.crazycards.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * The shuffle method here simulates human hand-shuffling.
 * 
 * @author Subu Iyer
 */
public class HandShuffler implements Shuffler 
{
    
    public HandShuffler()
    {
        
    }

    /**
     * Shuffle the given deck by splitting the deck into two and moving cards from 
     * each half into 1 deck and do this a few times.
     * 
     * @param deck
     * @return The shuffled deck 
     */
    @Override
    public CardDeck shuffle(CardDeck deck) 
    {
        if(deck == null)
        {
            return null;
        }
        
        // number of times to shuffle the deck is random between 1 - 10. the +1
        // makes sure that we shuffle at least once just in case the random number 
        // returned in 0
        int numTimesShuffle = new Random().nextInt(10) + 1;
        List<Card> cards = deck.getCards();
        ArrayList<Card> cardsShuffled = new ArrayList<>(cards.size());
        List<Card> cardsPart1 = null;
        List<Card> cardsPart2 = null;
        for(int shuffleCount = 0; shuffleCount < numTimesShuffle; shuffleCount++)
        {
            cardsShuffled.clear();
            cardsPart1 = cards.subList(0, cards.size()/2);
            cardsPart2 = cards.subList(cards.size()/2, cards.size());
            for(int i = 0; i < cardsPart1.size(); i++)
            {
                cardsShuffled.add(cardsPart1.get(i));
                cardsShuffled.add(cardsPart2.get(i));
            }
            cards = new ArrayList(cardsShuffled);
        }
        deck.setCards(cardsShuffled);
        return deck;
    }
    
}
