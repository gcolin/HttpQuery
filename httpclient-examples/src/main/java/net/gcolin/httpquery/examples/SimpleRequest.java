package net.gcolin.httpquery.examples;

import net.gcolin.httpquery.Http;
import net.gcolin.httpquery.examples.util.TomcatServer;

public class SimpleRequest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TomcatServer.start();
		
		System.out.println(Http.get("http://localhost:8880/index.html").asString());
		
		TomcatServer.stop();
	}

}
