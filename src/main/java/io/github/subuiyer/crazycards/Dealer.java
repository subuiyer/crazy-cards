package io.github.subuiyer.crazycards;

import java.util.HashMap;

public class Dealer 
{
    
    private HashMap mapDecks = null;
    

    public Dealer()
    {
        mapDecks = new HashMap();
    }
    
    
    public boolean createDeck(String name)
    {
        if(name == null || name.length() <= 0 || mapDecks.containsKey(name) == true)
        {
            return false;
        }
        
        CardDeck deck = new CardDeck();
        mapDecks.put(name, deck);
        
        return true;
    }
    
    
}
