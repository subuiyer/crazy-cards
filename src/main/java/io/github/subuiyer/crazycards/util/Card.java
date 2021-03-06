package io.github.subuiyer.crazycards.util;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the Card data structure. It defines the ranks and suits that a card 
 * can have. The constructor is used to set a suit and rank for the card being 
 * instantiated. That's really it.
 * 
 * @author Subu Iyer
 */
@XmlRootElement
public class Card 
{
    public static final String[] SUITS = {"SPADE", "HEART", "DIAMOND", "CLUB"};
    public static final String[] RANKS = {"A", "K", "Q", "J", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    
    private String suit = null;
    private String rank = null;
    private String rankSuit = null;
    
    public Card()
    {
        
    }
    
    
    public Card(String suit, String rank)
    {
        this.suit = suit;
        this.rank = rank;
    }
    
    
    public String getRankSuit()
    {
        return toString();
    }
    
    
    @Override
    public String toString()
    {
        return rank + "-" + suit;
    }
    
}
