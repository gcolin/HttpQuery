package net.gcolin.httpquery.examples;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import net.gcolin.httpquery.Http;
import net.gcolin.httpquery.examples.util.App;
import net.gcolin.httpquery.examples.util.Data;
import net.gcolin.httpquery.examples.util.SerializationUtil;
import net.gcolin.httpquery.examples.util.TomcatServer;import net.gcolin.httpquery.spi.ObjectDeserializer;

public final class SimpleObjectSerialized {
	
	private SimpleObjectSerialized(){}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Logger log = LoggerFactory.getLogger(SimpleObjectSerialized.class);
		
		//create data for serialization
		FileUtils.writeByteArrayToFile(new File("src/main/resources/webapp/obj.o"), SerializationUtil.createData());
				
		TomcatServer.start();
		
		Data data = Http.get("http://localhost:8880/obj.o").deserializeWith(ObjectDeserializer.class).as(Data.class);
		
		for(App app:data.getApp()){
			log.info(app.getName()+" : "+app.getStatus());
		}
		
		TomcatServer.stop();
	}

}

