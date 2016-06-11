package net.gcolin.httpquery.examples;

import java.util.logging.Logger;

import net.gcolin.httpquery.Http;
import net.gcolin.httpquery.examples.util.App;
import net.gcolin.httpquery.examples.util.Data;
import net.gcolin.httpquery.examples.util.TomcatServer;
import net.gcolin.httpquery.jaxb.JAXBDeserializer;

public final class SimpleJAXB {

    private static final Logger LOG = Logger.getLogger(SimpleJAXB.class.getName());
    
    private SimpleJAXB() {
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TomcatServer.start();

        Data data = Http.get("http://localhost:8880/data.xml")
                .deserializeWith(JAXBDeserializer.class).as(Data.class);

        for (App app : data.getApp()) {
            LOG.info(app.getName() + " : " + app.getStatus());
        }

        TomcatServer.stop();
    }

}
