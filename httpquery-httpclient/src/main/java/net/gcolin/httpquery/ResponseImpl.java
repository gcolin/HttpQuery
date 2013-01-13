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
package net.gcolin.httpquery;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;

import net.gcolin.httpquery.Deserializer;
import net.gcolin.httpquery.Response;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

public class ResponseImpl extends AbstractElement implements Response{

	private HttpResponse response;
	private Deserializer deserializer;
	
	public ResponseImpl(HttpResponse response,Deserializer deserializer){
		this.response=response;
		this.deserializer=deserializer;
	}
	
	public <T> T as(Class<T> target) {
		return callback(HttpClientDeserializers.object(target, deserializer));
	}

	public String asString() {
		return callback(HttpClientDeserializers.STRING);
	}

	public byte[] asBytes() {
		return callback(HttpClientDeserializers.BYTE);
	}
	
	public InputStream asStream() {
		return callback(HttpClientDeserializers.STREAM);
	}

	@Override
	public String header(String key) {
		return response.getFirstHeader(key).getValue();
	}

	public HttpResponse getResponse() {
		return response;
	}

	@Override
	public void close() {
		close(response.getEntity());
	}

	@Override
	public Collection<String> headers(String key) {
		Collection<String> list=new ArrayList<String>();
		for(Header h:response.getHeaders(key)){
			list.add(h.getValue());
		}
		return list;
	}

	@Override
	public Collection<Entry<String, String>> headers() {
		Collection<Entry<String, String>> list=new ArrayList<Entry<String, String>>();
		for(final Header h:response.getAllHeaders()){
			list.add(new HeaderEntry(h));
		}
		return list;
	}

	@Override
	public int status() {
		return response.getStatusLine().getStatusCode();
	}
	
	private static final class HeaderEntry implements Entry<String, String>{
		
		private final Header header;
		private String v;
		
		public HeaderEntry(Header h){
			this.header = h;
			v=h.getValue();
		}
		
		@Override
		public String setValue(String value) {
			String old = v;
			v=value;
			return old;
		}
		
		@Override
		public String getValue() {
			return header.getValue();
		}
		
		@Override
		public String getKey() {
			return header.getName();
		}
		
	}

}
