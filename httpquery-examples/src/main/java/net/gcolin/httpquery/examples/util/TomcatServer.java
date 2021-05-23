package net.gcolin.httpquery.examples.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.startup.Tomcat;
import org.apache.commons.io.IOUtils;

public final class TomcatServer {

    private static final int TOMCAT_START_TIMEOUT = 100;
    private static Tomcat server;
    private static LifecycleEvent event;

    private TomcatServer() {
    }

    public static void start() {
        new Thread(new TomcatRunner()).start();

        while (event == null
                || !Lifecycle.AFTER_START_EVENT.equals(event.getType())) {
            try {
                Thread.sleep(TOMCAT_START_TIMEOUT);
            } catch (InterruptedException e) {
                Logger.getLogger(TomcatServer.class.getName()).log(
                        Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    public static void stop() {
        try {
            server.stop();

            server.destroy();
        } catch (LifecycleException e) {
            Logger.getLogger(TomcatServer.class.getName()).log(Level.SEVERE,
                    e.getMessage(), e);
        }
    }

    private static class TomcatRunner implements Runnable {

        private static final int TOMCAT_PORT = 8880;

        @Override
        public void run() {
            server = new Tomcat();
            server.setPort(TOMCAT_PORT);

            try {
                Context ctx = server.addContext("/", new File(
                        "src/main/resources/webapp").getAbsolutePath());

                Tomcat.addServlet(ctx, "hello", new HttpServlet() {
                    protected void service(HttpServletRequest req,
                            HttpServletResponse resp)

                    throws ServletException, IOException {
                        IOUtils.copy(new FileInputStream(
                                new File(new File("src/main/resources/webapp"),
                                        req.getPathInfo())), resp
                                .getOutputStream());
                    }
                });
                ctx.addServletMappingDecoded("/*", "hello");
                server.getServer().addLifecycleListener(
                        new LifecycleListener() {

                            @Override
                            public void lifecycleEvent(LifecycleEvent event) {
                                TomcatServer.event = event;
                            }
                        });

                server.start();

            } catch (LifecycleException e) {
                Logger.getLogger(TomcatServer.class.getName()).log(
                        Level.SEVERE, e.getMessage(), e);
            }
        }

    }

}
