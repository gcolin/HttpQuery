package net.gcolin.httpquery.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.gcolin.httpquery.Http;
import net.gcolin.httpquery.mock.MockHttpHandler;
import net.gcolin.httpquery.mock.When;

public final class MockPostRequest {

	private MockPostRequest(){}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(MockPostRequest.class);
		
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
