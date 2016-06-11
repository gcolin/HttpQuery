package net.gcolin.httpquery.examples;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;

import net.gcolin.httpquery.Http;
import net.gcolin.httpquery.examples.util.TomcatServer;

public final class SimpleRequestAsStream {

    private static final Logger LOG = Logger.getLogger(SimpleRequestAsStream.class.getName());
    
    private SimpleRequestAsStream() {
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        TomcatServer.start();

        InputStream in = Http.get("http://localhost:8880/index.html")
                .asStream();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        IOUtils.copy(in, bout);
        in.close();
        LOG.info(new String(bout.toByteArray()));
        
        TomcatServer.stop();
    }

}
