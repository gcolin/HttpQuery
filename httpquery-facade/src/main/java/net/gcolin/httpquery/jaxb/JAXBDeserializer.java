package net.gcolin.httpquery.jaxb;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import net.gcolin.httpquery.Accept;
import net.gcolin.httpquery.Deserializer;
import net.gcolin.httpquery.For;

@Accept("application/xml")
@For(Object.class)
public class JAXBDeserializer implements Deserializer{

	@SuppressWarnings("unchecked")
    @Override
	public <T> T toObject(InputStream inStream, Class<T> target) {
		JAXBContext context = JAXBContexts.context(target);
		try {
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(inStream);
		} catch (JAXBException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
			return null;
		}
	}

}
