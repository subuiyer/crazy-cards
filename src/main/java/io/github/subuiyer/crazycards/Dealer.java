package io.github.subuiyer.crazycards;

import io.github.subuiyer.crazycards.util.CardDeck;
import io.github.subuiyer.crazycards.util.CardDeckStore;


public class Dealer 
{
    
    private CardDeckStore deckStore = null;
    

    public Dealer(CardDeckStore store) throws InstantiationException
    {
        if(store == null)
        {
            throw new InstantiationException();
        }
        deckStore = store;
    }
    
    
    public boolean createDeck(String name)
    {
        if(deckStore.isValidName(name) == false)
        {
            return false;
        }
        
        CardDeck deck = new CardDeck();
        return deckStore.put(name, deck);
    }
    
    
    public String[] getDeckNames()
    {
        return deckStore.getNames();
    }
    
    
}
