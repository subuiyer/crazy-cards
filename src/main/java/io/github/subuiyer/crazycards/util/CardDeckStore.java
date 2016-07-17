package io.github.subuiyer.crazycards.util;


public interface CardDeckStore 
{

    public boolean put(String name, CardDeck deck);
    
    public CardDeck get(String name);
    
    public String[] getNames();
    
    public boolean isValidName(String name);
    
    public boolean exists(String name);
    
    public boolean delete(String name);
    
}
