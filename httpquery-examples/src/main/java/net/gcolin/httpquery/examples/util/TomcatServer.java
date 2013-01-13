package net.gcolin.httpquery.examples.util;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.LoggerFactory;

public final class TomcatServer {
	
	private static final int TOMCAT_START_TIMEOUT = 100;
	private static Tomcat server;
	private static LifecycleEvent event;
	
	private TomcatServer(){}

	public static void start(){
		new Thread(new TomcatRunner()).start();	
		
		while(event == null || !Lifecycle.AFTER_START_EVENT.equals(event.getType())){
			try {
				Thread.sleep(TOMCAT_START_TIMEOUT);
			} catch (InterruptedException e) {
				LoggerFactory.getLogger(TomcatServer.class).error(e.getMessage(),e);
			}
		}
	}
	
	
	public static void stop(){
		try {
			server.stop();
		
			server.destroy();
		} catch (LifecycleException e) {
			LoggerFactory.getLogger(TomcatServer.class).error(e.getMessage(),e);
		}
	}
	
	private static class TomcatRunner implements Runnable{

		private static final int TOMCAT_PORT = 8880;

		@Override
		public void run() {
			server = new Tomcat();
			server.setPort(TOMCAT_PORT);
			
			try {
				server.addWebapp("/", new File("src/main/resources/webapp").toURI().getPath());
			
				server.getServer().addLifecycleListener(new LifecycleListener() {
					
					@Override
					public void lifecycleEvent(LifecycleEvent event) {
						TomcatServer.event=event;
					}
				});
				
				server.start();
			
				
			} catch (ServletException e) {
				LoggerFactory.getLogger(TomcatServer.class).error(e.getMessage(),e);
			} catch (LifecycleException e) {
				LoggerFactory.getLogger(TomcatServer.class).error(e.getMessage(),e);
			}
		}
		
	}
	
}
