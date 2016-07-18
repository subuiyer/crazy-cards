package io.github.subuiyer.crazycards.util;


import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;


public class ResponseMessageTest 
{

    @Test
    public void testGetStatus()
    {
        ResponseMessage msg = new ResponseMessage("blah", null, null);
        Assert.assertEquals("blah", msg.getStatus());
    }
    
    @Test
    public void testGetMessage()
    {
        ResponseMessage msg = new ResponseMessage(null, null, "blah");
        Assert.assertEquals("blah", msg.getMessage());
    }
    
    @Test
    public void testGetData()
    {
        List data = new ArrayList();
        data.add("blah-1");
        data.add("blah-2");
        ResponseMessage msg = new ResponseMessage(null, data, null);
        Assert.assertTrue(msg.getData().size() == 2);
        Assert.assertEquals(data, msg.getData());
    }
    
    @Test
    public void testResponseMessage_cstDefault()
    {
        ResponseMessage msg = new ResponseMessage();
        Assert.assertNull(msg.getStatus());
        Assert.assertNull(msg.getMessage());
        Assert.assertNull(msg.getData());
    }
    
}
