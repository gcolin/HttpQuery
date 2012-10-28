package org.httpclientquery.jaxb;

import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.httpclientquery.Serializer;

public class JAXBPrettySerializer implements Serializer{

	@Override
	public void write(OutputStream outStream, Object obj) throws IOException {
		JAXBContext context = JAXBContexts.context(obj.getClass());
		try {
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
			marshaller.marshal(obj, outStream);
		} catch (JAXBException e) {
			throw new IOException(e);
		}
	}

}
