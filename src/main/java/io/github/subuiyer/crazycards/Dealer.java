package io.github.subuiyer.crazycards;


import io.github.subuiyer.crazycards.util.CardDeck;
import io.github.subuiyer.crazycards.util.CardDeckStore;
import io.github.subuiyer.crazycards.util.Shuffler;
import java.util.List;


/**
 * This Dealer class handles everything a card dealer does - creates a deck of cards,
 * retrieves a specific deck, removes decks, shuffles a deck and so on.
 * 
 * Dealer is a singleton and there can only be 1 dealer instance in the application.
 * 
 * @author Subu Iyer
 */
public class Dealer 
{
    
    private static Dealer instance = null;
    private CardDeckStore deckStore = null;
    private Shuffler shuffler = null;
    

    private Dealer(CardDeckStore store) throws InstantiationException
    {
        if(store == null)
        {
            throw new InstantiationException();
        }
        deckStore = store;
    }
    
    
    /**
     * Get a singleton instance of the Dealer. This is the only way to get to an instance.
     * 
     * @param store
     * @return
     * @throws InstantiationException 
     */
    public static Dealer getInstance(CardDeckStore store) throws InstantiationException
    {
        if(instance == null)
        {
            instance = new Dealer(store);
        }
        
        return instance;
    }
    
    
    /**
     * Set a Shuffler to set/alter the shuffling mechanism.
     * 
     * @param shuffler 
     */
    public void setShuffler(Shuffler shuffler)
    {
        this.shuffler = shuffler;
    }
    
    
    /**
     * Check if a named deck exists.
     * 
     * @param name
     * @return True if the card deck has already been created with the given name 
     */
    public boolean deckExists(String name)
    {
        return deckStore.exists(name);
    }
    
    
    /**
     * Create a card deck with the given name.
     * 
     * @param name
     * @return True if the card deck is created 
     */
    public boolean createDeck(String name)
    {
        if(deckStore.isValidName(name) == false)
        {
            return false;
        }
        
        CardDeck deck = new CardDeck();
        return deckStore.put(name, deck);
    }
    
    
    /**
     * Shuffle a given deck of cards. If no shuffler is registered, just don't shuffle.
     * 
     * @param deck
     * @return The shuffled deck. 
     */
    public CardDeck shuffle(CardDeck deck)
    {
        if(shuffler == null)
        {
            return deck;
        }
        return shuffler.shuffle(deck);
    }
    
    
    /**
     * Get the named card deck.
     * 
     * @param name
     * @return Card deck with the given name (or null) 
     */
    public CardDeck getDeck(String name)
    {
        if(deckStore.isValidName(name) == false)
        {
            return null;
        }
        
        return deckStore.get(name);
    }
    
    
    /**
     * Delete the card deck with the given name.
     * 
     * @param name
     * @return True if the deck is deleted successfully 
     */
    public boolean deleteDeck(String name)
    {
        return deckStore.delete(name);
    }
    
    
    /**
     * Get a list of names of card decks.
     * 
     * @return List of card deck names 
     */
    public List<String> getDeckNames()
    {
        return deckStore.getNames();
    }
    
    
    /**
     * Remove all decks.
     */
    public void clearAll()
    {
        deckStore.deleteAll();
    }
    
}
