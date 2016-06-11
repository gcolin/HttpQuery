package net.gcolin.httpquery.examples;

import java.io.IOException;
import java.util.logging.Logger;

import net.gcolin.httpquery.Http;
import net.gcolin.httpquery.examples.util.TomcatServer;

public final class SimpleRequestAsByte {

    private static final Logger LOG = Logger.getLogger(SimpleRequestAsByte.class.getName());
    
    private SimpleRequestAsByte() {
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        TomcatServer.start();

        byte[] r = Http.get("http://localhost:8880/index.html").asBytes();
        LOG.info(new String(r));

        TomcatServer.stop();
    }

}
