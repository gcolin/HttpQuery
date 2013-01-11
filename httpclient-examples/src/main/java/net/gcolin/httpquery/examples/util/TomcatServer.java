package net.gcolin.httpquery.examples.util;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.startup.Tomcat;

public class TomcatServer {
	
	private static Tomcat server;
	private static LifecycleEvent event;

	public static void start(){
		new Thread(){
			@Override
			public void run() {
				server = new Tomcat();
				server.setPort(8880);
				
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
					e.printStackTrace();
				} catch (LifecycleException e) {
					e.printStackTrace();
				}
			}
		}.start();	
		
		
		while(event == null || !Lifecycle.AFTER_START_EVENT.equals(event.getType())){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void stop(){
		try {
			server.stop();
		
			server.destroy();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
	}
	
}
