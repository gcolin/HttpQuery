package net.gcolin.httpquery.examples;

import java.util.logging.Logger;

import net.gcolin.httpquery.Http;
import net.gcolin.httpquery.mock.MockHttpHandler;
import net.gcolin.httpquery.mock.When;

public final class MockPostRequest {

	private MockPostRequest(){}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    Logger log = Logger.getLogger(MockPostRequest.class.getName());
		
		//active mocking
		MockHttpHandler.bind(true);
		
		When.post("http://localhost:8880/form.html","login=admin&password=admin").asStringReturn("you are logged");
		When.post("http://localhost:8880/form.html","login=admin&password=admin2").asStringReturn("invalid login");
		
		log.info(Http.post("http://localhost:8880/form.html","login=admin&password=admin").asString());
		log.info(Http.post("http://localhost:8880/form.html","login=admin&password=admin2").asString());
		
		//remove mocking
		MockHttpHandler.bind(false);
		
	}

}
