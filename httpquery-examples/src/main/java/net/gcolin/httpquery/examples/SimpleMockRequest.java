package net.gcolin.httpquery.examples;

import java.util.logging.Logger;

import net.gcolin.httpquery.Http;
import net.gcolin.httpquery.mock.MockHttpHandler;
import net.gcolin.httpquery.mock.When;

public final class SimpleMockRequest {

	private SimpleMockRequest(){}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    Logger log = Logger.getLogger(SimpleMockRequest.class.getName());
		
		//active mocking
		MockHttpHandler.bind(true);
		
		When.get("http://localhost:8880/index.html").asStringReturn("hello world");
		
		log.info(Http.get("http://localhost:8880/index.html").asString());
		
		//remove mocking
		MockHttpHandler.bind(false);
		
	}

}
