/**
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * @author Gael COLIN
 */
package org.httpclientquery.spi;


import java.io.OutputStream;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.httpclientquery.ContentType;
import org.httpclientquery.For;
import org.httpclientquery.Serializer;
import org.slf4j.LoggerFactory;

@For({DOMSource.class,SAXSource.class,StAXSource.class,StreamSource.class})
@ContentType("application/xml")
public class SourceSerializer implements Serializer{

	protected TransformerFactory tf=TransformerFactory.newInstance();
	
	@Override
	public void write(OutputStream outStream, Object obj) {
		StreamResult sr = new StreamResult(outStream);
		try {
			tf.newTransformer().transform((Source) obj, sr);
		} catch (TransformerConfigurationException e) {
			LoggerFactory.getLogger(this.getClass()).error(e.getMessage(), e);
		} catch (TransformerException e) {
			LoggerFactory.getLogger(this.getClass()).error(e.getMessage(), e);
		}
	}
}
