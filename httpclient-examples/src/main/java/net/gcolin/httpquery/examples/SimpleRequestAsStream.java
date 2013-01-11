package net.gcolin.httpquery.examples;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import net.gcolin.httpquery.Http;
import net.gcolin.httpquery.examples.util.TomcatServer;

public class SimpleRequestAsStream {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		TomcatServer.start();
		
		InputStream in = Http.get("http://localhost:8880/index.html").asStream();
		IOUtils.copy(in, System.out);
		in.close();
		
		TomcatServer.stop();
	}

}
