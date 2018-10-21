package net.gcolin.httpquery.jaxb;

import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import net.gcolin.httpquery.ContentType;
import net.gcolin.httpquery.For;
import net.gcolin.httpquery.Serializer;

@ContentType("application/xml")
@For(Object.class)
public class JAXBSerializer implements Serializer {

    @Override
    public void write(OutputStream outStream, Object obj) throws IOException {
        JAXBContext context = JAXBContexts.context(obj.getClass());
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(obj, outStream);
        } catch (JAXBException e) {
            throw new IOException(e);
        }
    }

}
