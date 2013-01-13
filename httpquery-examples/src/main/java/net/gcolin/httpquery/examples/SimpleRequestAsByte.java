package net.gcolin.httpquery.examples;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import net.gcolin.httpquery.Http;
import net.gcolin.httpquery.examples.util.TomcatServer;

public final class SimpleRequestAsByte {
	
	private SimpleRequestAsByte(){}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		TomcatServer.start();
		
		byte[] r = Http.get("http://localhost:8880/index.html").asBytes();
		IOUtils.copy(new ByteArrayInputStream(r), System.out);
		
		TomcatServer.stop();
	}

}
