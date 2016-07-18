package io.github.subuiyer.crazycards.util;


import java.util.List;


public class ResponseMessage 
{
    
    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_FAIL = "fail";
    public static final String STATUS_ERROR = "error";

    private String status = null;
    private List data = null;
    private String message = null;
    
    
    public ResponseMessage()
    {
        
    }
    
    public ResponseMessage(String status, List data, String message)
    {
        this.status = status;
        this.data = data;
        this.message = message;
    }
    
    
    public String getStatus()
    {
        return status;
    }
    
    
    public List getData()
    {
        return data;
    }
    
    
    public String getMessage()
    {
        return message;
    }
    
}
