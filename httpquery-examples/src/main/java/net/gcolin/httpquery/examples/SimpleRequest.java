package net.gcolin.httpquery.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.gcolin.httpquery.Http;
import net.gcolin.httpquery.examples.util.TomcatServer;

public final class SimpleRequest {

	private SimpleRequest(){}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(SimpleRequest.class);
		
		TomcatServer.start();
		
		log.info(Http.get("http://localhost:8880/index.html").asString());
		
		TomcatServer.stop();
	}

}
