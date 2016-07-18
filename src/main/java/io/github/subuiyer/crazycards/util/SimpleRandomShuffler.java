package io.github.subuiyer.crazycards.util;

import java.util.Collections;



public class SimpleRandomShuffler implements Shuffler
{
   
    public SimpleRandomShuffler()
    {
        
    }
    
    
    @Override
    public CardDeck shuffle(CardDeck deck) 
    {
        if(deck == null)
        {
            return null;
        }
        
        Collections.shuffle(deck.getCards());
        return deck;
    }
    
}
