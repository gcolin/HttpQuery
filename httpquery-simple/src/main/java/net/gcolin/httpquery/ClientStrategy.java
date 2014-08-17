package net.gcolin.httpquery;


public class ClientStrategy {

    private static CookieManager singleton = null;
    
    public static void createClientPerRequest()
    {
        singleton = null;
    }
    
    public static void singleClient()
    {
        singleton = new CookieManager();
    }
    
    public static CookieManager createClient()
    {
        if(singleton==null)
        {
            return new CookieManager();
        }else{
            return singleton;
        }
    }
}
