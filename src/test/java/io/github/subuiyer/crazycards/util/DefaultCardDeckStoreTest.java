package io.github.subuiyer.crazycards.util;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;



public class DefaultCardDeckStoreTest 
{

    @Test
    public void testPut_invalid()
    {
        DefaultCardDeckStore store = new DefaultCardDeckStore();
        Assert.assertFalse(store.put(null, new CardDeck()));
        Assert.assertFalse(store.put("", new CardDeck()));
        store.put("deck1", new CardDeck());
        Assert.assertFalse(store.put("deck1", new CardDeck()));
    }
    
    @Test
    public void testPut()
    {
        DefaultCardDeckStore store = new DefaultCardDeckStore();
        Assert.assertTrue(store.put("deck1", new CardDeck()));
        Assert.assertTrue(store.put("deck2", new CardDeck()));
    }
    
    @Test
    public void testGet()
    {
        DefaultCardDeckStore store = new DefaultCardDeckStore();
        store.put("deck1", new CardDeck());
        Assert.assertNotNull(store.get("deck1"));
    }
    
    @Test
    public void testGet_none()
    {
        DefaultCardDeckStore store = new DefaultCardDeckStore();
        store.put("deck1", new CardDeck());
        Assert.assertNull(store.get("noSuchDeck"));
        Assert.assertNull(store.get(null));
        Assert.assertNull(store.get(""));
    }
    
    @Test
    public void testGetNames()
    {
        DefaultCardDeckStore store = new DefaultCardDeckStore();
        store.put("deck1", new CardDeck());
        store.put("deck2", new CardDeck());
        List names = store.getNames();
        Assert.assertTrue(names.size() == 2);
        Assert.assertTrue(names.contains("deck1"));
        Assert.assertTrue(names.contains("deck2"));
    }
    
    @Test
    public void testGetNames_empty()
    {
        DefaultCardDeckStore store = new DefaultCardDeckStore();
        List names = store.getNames();
        Assert.assertTrue(names.size() == 0);
    }
    
    @Test
    public void testExists()
    {
        DefaultCardDeckStore store = new DefaultCardDeckStore();
        store.put("deck1", new CardDeck());
        Assert.assertTrue(store.exists("deck1"));
        Assert.assertFalse(store.exists("noSuchDeck"));
        Assert.assertFalse(store.exists(null));
        Assert.assertFalse(store.exists(""));
    }
    
    @Test
    public void testIsValidName_none()
    {
        DefaultCardDeckStore store = new DefaultCardDeckStore();
        Assert.assertFalse(store.isValidName(null));
        Assert.assertFalse(store.isValidName(""));
    }
    
    @Test
    public void testIsValidName()
    {
        DefaultCardDeckStore store = new DefaultCardDeckStore();
        Assert.assertTrue(store.isValidName("deckOne"));
        store.put("deck1", new CardDeck());
        Assert.assertTrue(store.isValidName("deckOne"));
        Assert.assertFalse(store.isValidName("deck1"));
    }
    
    @Test
    public void testDelete()
    {
        DefaultCardDeckStore store = new DefaultCardDeckStore();
        store.put("deck1", new CardDeck());
        Assert.assertTrue(store.delete("deck1"));
    }
    
    @Test
    public void testDelete_none()
    {
        DefaultCardDeckStore store = new DefaultCardDeckStore();
        store.put("deck1", new CardDeck());
        Assert.assertFalse(store.delete(null));
        Assert.assertFalse(store.delete("noSuchDeck"));
    }
    
    
}
