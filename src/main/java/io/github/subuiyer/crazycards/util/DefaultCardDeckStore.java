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
    public List<String> getNames() 
    {
        String[] names = mapDecks.keySet().toArray(new String[mapDecks.size()]);
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
        
        return mapDecks.remove(name) != null ? true : false;
    }

    
    @Override
    public void deleteAll() 
    {
        mapDecks.clear();
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
