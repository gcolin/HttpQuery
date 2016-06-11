package net.gcolin.httpquery.examples;

import java.util.logging.Logger;

import net.gcolin.httpquery.Http;
import net.gcolin.httpquery.examples.util.TomcatServer;

public final class SimpleRequest {

    private static final Logger LOG = Logger.getLogger(SimpleRequest.class.getName());
    
    private SimpleRequest() {
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TomcatServer.start();

        LOG.info(Http.get("http://localhost:8880/index.html").asString());

        TomcatServer.stop();
    }

}
