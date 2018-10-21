package net.gcolin.httpquery.examples;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import net.gcolin.httpquery.Http;
import net.gcolin.httpquery.examples.util.App;
import net.gcolin.httpquery.examples.util.Data;
import net.gcolin.httpquery.examples.util.SerializationUtil;
import net.gcolin.httpquery.examples.util.TomcatServer;
import net.gcolin.httpquery.obj.ObjectDeserializer;

import org.apache.commons.io.FileUtils;

public final class SimpleObjectSerialized {

    private static final Logger LOG = Logger.getLogger(SimpleObjectSerialized.class.getName());
    
    private SimpleObjectSerialized() {
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // create data for serialization
        FileUtils.writeByteArrayToFile(new File(
                "src/main/resources/webapp/obj.o"), SerializationUtil
                .createData());

        TomcatServer.start();

        Data data = Http.get("http://localhost:8880/obj.o")
                .deserializeWith(ObjectDeserializer.class).as(Data.class);

        for (App app : data.getApp()) {
            LOG.info(app.getName() + " : " + app.getStatus());
        }

        TomcatServer.stop();
        
        new File("src/main/resources/webapp/obj.o").delete();
    }

}
