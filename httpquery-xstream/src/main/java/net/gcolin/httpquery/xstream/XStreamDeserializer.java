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
package net.gcolin.httpquery.xstream;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import net.gcolin.httpquery.Deserializer;
import net.gcolin.httpquery.IO;

import com.thoughtworks.xstream.XStream;

public class XStreamDeserializer implements Deserializer{

	private XStream xStream;
	private Charset charset;
	
	public XStreamDeserializer(){
		this(new XStream(),IO.getCharset());
	}
	
	public XStreamDeserializer(XStream xStream,Charset charset){
		this.xStream=xStream;
		this.charset=charset;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T toObject(InputStream inStream, Class<T> target) {
		return (T) xStream.fromXML(new InputStreamReader(inStream,charset));
	}
	

}
