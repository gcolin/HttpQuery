package net.gcolin.httpquery.jaxb;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public final class JAXBContexts {

    private static Map<Class<?>, JAXBContext> contexts = new HashMap<Class<?>, JAXBContext>();

    private JAXBContexts() {
    }

    public static JAXBContext context(Class<?> clazz) {
        JAXBContext c = contexts.get(clazz);
        if (c == null) {
            try {
                c = JAXBContext.newInstance(clazz);
            } catch (JAXBException e) {
                Logger.getLogger(JAXBContexts.class.getName()).log(
                        Level.SEVERE, e.getMessage(), e);
            }
            contexts.put(clazz, c);
        }
        return c;
    }
}
