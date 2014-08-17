package net.gcolin.httpquery.jaxb;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import net.gcolin.httpquery.Deserializer;

public class JAXBDeserializer implements Deserializer{

	@Override
	public <T> T toObject(InputStream inStream, Class<T> target) {
		JAXBContext context = JAXBContexts.context(target);
		try {
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return unmarshaller.unmarshal(new StreamSource(inStream),target).getValue();
		} catch (JAXBException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
			return null;
		}
	}

}
