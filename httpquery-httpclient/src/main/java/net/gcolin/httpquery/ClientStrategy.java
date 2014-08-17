package net.gcolin.httpquery;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public class ClientStrategy {

    private static HttpClient singleton = null;
    
    public static void createClientPerRequest()
    {
        singleton = null;
    }
    
    public static void singleClient()
    {
        singleton = new DefaultHttpClient();
    }
    
    public static HttpClient createClient()
    {
        if(singleton==null)
        {
            return new DefaultHttpClient();
        }else{
            return singleton;
        }
    }
}
