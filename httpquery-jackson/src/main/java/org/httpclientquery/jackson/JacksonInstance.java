package org.httpclientquery.jackson;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

public class JacksonInstance {

	public static final ObjectMapper MAP = new ObjectMapper();
	
	static{
		AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
	    AnnotationIntrospector secondary = new JaxbAnnotationIntrospector();
	    AnnotationIntrospector introspector = new AnnotationIntrospector.Pair(primary, secondary);
	    // make deserializer use JAXB annotations (only)
		MAP.setDeserializationConfig(MAP.getDeserializationConfig().withAnnotationIntrospector(introspector));
	    // make serializer use JAXB annotations (only)
		MAP.setSerializationConfig(MAP.getSerializationConfig().withAnnotationIntrospector(introspector));
	}
}
