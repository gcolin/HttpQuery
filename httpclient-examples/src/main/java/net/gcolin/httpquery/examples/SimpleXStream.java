package net.gcolin.httpquery.examples;

import net.gcolin.httpquery.Http;
import net.gcolin.httpquery.examples.util.App;
import net.gcolin.httpquery.examples.util.Data;
import net.gcolin.httpquery.examples.util.TomcatServer;
import net.gcolin.httpquery.xstream.XStreamDeserializer;

public class SimpleXStream {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TomcatServer.start();
		
		Data data = Http.get("http://localhost:8880/data.xml").deserializeWith(XStreamDeserializer.class).as(Data.class);
		
		for(App app:data.getApp()){
			System.out.println(app.getName()+" : "+app.getStatus());
		}
		
		TomcatServer.stop();
	}

}

