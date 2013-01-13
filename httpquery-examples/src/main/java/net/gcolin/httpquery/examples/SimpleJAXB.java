package net.gcolin.httpquery.examples;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.gcolin.httpquery.Http;
import net.gcolin.httpquery.examples.util.App;
import net.gcolin.httpquery.examples.util.Data;
import net.gcolin.httpquery.examples.util.TomcatServer;
import net.gcolin.httpquery.jaxb.JAXBDeserializer;

public final class SimpleJAXB {
	
	private SimpleJAXB(){}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(SimpleJAXB.class);
		
		TomcatServer.start();
		
		Data data = Http.get("http://localhost:8880/data.xml").deserializeWith(JAXBDeserializer.class).as(Data.class);
		
		for(App app:data.getApp()){
			log.info(app.getName()+" : "+app.getStatus());
		}
		
		TomcatServer.stop();
	}

}


