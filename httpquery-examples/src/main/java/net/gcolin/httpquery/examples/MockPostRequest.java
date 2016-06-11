package net.gcolin.httpquery.examples;

import java.util.logging.Logger;

import net.gcolin.httpquery.Http;
import net.gcolin.httpquery.mock.MockHttpHandler;
import net.gcolin.httpquery.mock.When;

public final class MockPostRequest {

    private static final String LOGIN_URL = "http://localhost:8880/form.html";
    private static final Logger LOG = Logger.getLogger(MockPostRequest.class.getName());
    
    private MockPostRequest() {
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        // active mocking
        MockHttpHandler.bind(true);

        When.post(LOGIN_URL,
                "login=admin&password=admin").asStringReturn("you are logged");
        When.post(LOGIN_URL,
                "login=admin&password=admin2").asStringReturn("invalid login");

        LOG.info(Http.post(LOGIN_URL,
                "login=admin&password=admin").asString());
        LOG.info(Http.post(LOGIN_URL,
                "login=admin&password=admin2").asString());

        // remove mocking
        MockHttpHandler.bind(false);

    }

}
