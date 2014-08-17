package net.gcolin.httpquery.examples;

import java.util.logging.Logger;

import net.gcolin.httpquery.Http;
import net.gcolin.httpquery.examples.util.TomcatServer;

public final class SimpleRequest {

	private SimpleRequest(){}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    Logger log = Logger.getLogger(SimpleRequest.class.getName());
		
		TomcatServer.start();
		
		log.info(Http.get("http://localhost:8880/index.html").asString());
		
		TomcatServer.stop();
	}

}
