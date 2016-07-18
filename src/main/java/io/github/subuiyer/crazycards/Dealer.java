package io.github.subuiyer.crazycards;


import io.github.subuiyer.crazycards.util.CardDeck;
import io.github.subuiyer.crazycards.util.CardDeckStore;
import java.util.List;


public class Dealer 
{
    
    private static Dealer instance = null;
    private CardDeckStore deckStore = null;
    

    private Dealer(CardDeckStore store) throws InstantiationException
    {
        if(store == null)
        {
            throw new InstantiationException();
        }
        deckStore = store;
    }
    
    
    public static Dealer getInstance(CardDeckStore store) throws InstantiationException
    {
        if(instance == null)
        {
            instance = new Dealer(store);
        }
        
        return instance;
    }
    
    
    public boolean deckExists(String name)
    {
        return deckStore.exists(name);
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
    
    
    public CardDeck getDeck(String name)
    {
        if(deckStore.isValidName(name) == false)
        {
            return null;
        }
        
        return deckStore.get(name);
    }
    
    
    public boolean deleteDeck(String name)
    {
        return deckStore.delete(name);
    }
    
    
    public List<String> getDeckNames()
    {
        return deckStore.getNames();
    }
    
    
    public void clearAll()
    {
        deckStore.deleteAll();
    }
    
}
