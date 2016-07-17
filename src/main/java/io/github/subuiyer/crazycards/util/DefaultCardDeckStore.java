package io.github.subuiyer.crazycards.util;

import java.util.HashMap;


public class DefaultCardDeckStore implements CardDeckStore
{
    
    private HashMap<String, CardDeck> mapDecks = null;
    
    
    public DefaultCardDeckStore()
    {
        mapDecks = new HashMap<>();
    }

    
    @Override
    public boolean put(String name, CardDeck deck) 
    {
        if(isValidName(name) == false)
        {
            return false;
        }
        
        mapDecks.put(name, deck);
        return true;
    }

    
    @Override
    public CardDeck get(String name) 
    {
        return mapDecks.get(name);
    }

    
    @Override
    public String[] getNames() 
    {
        return mapDecks.keySet().toArray(new String[mapDecks.size()]);
    }

    
    @Override
    public boolean delete(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public boolean exists(String name) 
    {
        return mapDecks.containsKey(name);
    }

    
    @Override
    public boolean isValidName(String name) 
    {
        return (name == null || name.length() <= 0 || exists(name) == true) ? false : true;
    }
    
}
