package io.github.subuiyer.crazycards.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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
        if(isValidName(name) == false || exists(name) == true)
        {
            return false;
        }
        synchronized(this)
        {
            mapDecks.put(name, deck);
        }
        return true;
    }

    
    @Override
    public CardDeck get(String name) 
    {
        synchronized(this)
        {
            return mapDecks.get(name);
        }
    }

    
    @Override
    public List<String> getNames() 
    {
        String[] names = null;
        synchronized(this)
        {
            names = mapDecks.keySet().toArray(new String[mapDecks.size()]);
        }
        final List<String> listNames = new ArrayList(names.length);
        for(int i = 0; i < names.length; i++)
        {
            listNames.add(names[i]);
        }
        
        return listNames;
    }

    
    @Override
    public boolean delete(String name) 
    {
        if(name == null)
        {
            return false;
        }
        synchronized(this)
        {
            return mapDecks.remove(name) != null ? true : false;
        }
    }

    
    @Override
    public void deleteAll() 
    {
        synchronized(this)
        {
            mapDecks.clear();
        }
    }
    
    
    
    @Override
    public boolean exists(String name) 
    {
        synchronized(this)
        {
            return mapDecks.containsKey(name);
        }
    }

    
    @Override
    public boolean isValidName(String name) 
    {
        return (name == null || name.length() <= 0) ? false : true;
    }

}
