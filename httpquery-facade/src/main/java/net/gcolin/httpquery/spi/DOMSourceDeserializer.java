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

package net.gcolin.httpquery.spi;


import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;

import net.gcolin.httpquery.Accept;
import net.gcolin.httpquery.Deserializer;
import net.gcolin.httpquery.For;

import org.slf4j.LoggerFactory;

@For(DOMSource.class)
@Accept("application/xml,text/xml,xml,application/x-java-serialized-object+xml")
public class DOMSourceDeserializer implements Deserializer{

	@SuppressWarnings("unchecked")
	@Override
	public <T> T toObject(InputStream inStream, Class<T> target) {
		try {
			return (T) new DOMSource(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inStream));
		} catch (Exception e) {
			LoggerFactory.getLogger(this.getClass()).error(e.getMessage(),e);
		} 
		return null;
	}
}
