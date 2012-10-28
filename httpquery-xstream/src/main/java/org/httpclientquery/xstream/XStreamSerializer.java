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
package org.httpclientquery.xstream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.httpclientquery.IO;
import org.httpclientquery.Serializer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.CompactWriter;


public class XStreamSerializer implements Serializer{

	private XStream xStream;
	private Charset charset;
	
	public XStreamSerializer(){
		this(new XStream(),IO.getCharset());
	}
	
	public XStreamSerializer(XStream xStream,Charset charset){
		this.xStream=xStream;
		this.charset=charset;
	}
	
	@Override
	public void write(OutputStream outStream, Object obj) throws IOException {
		xStream.marshal(obj, new CompactWriter(new OutputStreamWriter(outStream, charset)));
	}

}
