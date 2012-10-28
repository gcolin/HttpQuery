package org.httpclientquery.jaxb;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.httpclientquery.Deserializer;
import org.slf4j.LoggerFactory;

public class JAXBDeserializer implements Deserializer{

	@Override
	public <T> T toObject(InputStream inStream, Class<T> target) {
		JAXBContext context = JAXBContexts.context(target);
		try {
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return unmarshaller.unmarshal(new StreamSource(inStream),target).getValue();
		} catch (JAXBException e) {
			LoggerFactory.getLogger(this.getClass()).error(e.getMessage(),e);
			return null;
		}
	}

}
