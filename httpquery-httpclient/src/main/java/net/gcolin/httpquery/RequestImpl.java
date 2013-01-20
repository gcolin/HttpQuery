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

import java.io.IOException;
import java.io.InputStream;

import net.gcolin.httpquery.Deserializer;
import net.gcolin.httpquery.IO;
import net.gcolin.httpquery.Request;
import net.gcolin.httpquery.Response;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;

public class RequestImpl extends AbstractElement implements Request {

	private HttpRequestBase delegate;
	private Deserializer deserializer;

	private static class BaseClient extends DefaultHttpClient {
		private static final int HTTPS = 443;
		private static final int HTTP = 80;

		/**
		 * gestion du multi thread
		 */
		@Override
		protected org.apache.http.conn.ClientConnectionManager createClientConnectionManager() {
			final SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", BaseClient.HTTP,
					PlainSocketFactory.getSocketFactory()));
			registry.register(new Scheme("https", BaseClient.HTTPS,
					SSLSocketFactory.getSocketFactory()));
			return new PoolingClientConnectionManager(registry);
		};
	}

	/**
	 * http client
	 */
	private static HttpClient http = new BaseClient();

	public RequestImpl(HttpRequestBase delegate) {
		this.delegate = delegate;
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

	public Response asResponse() {
		return callback(HttpClientDeserializers.reponse(deserializer));
	}

	public RequestImpl setContentType(String s) {
		delegate.addHeader("Content-Type", s);
		return this;
	}

	public Request setAcceptType(String s) {
		return header("Accept", s);
	}

	public Request deserializeWith(Deserializer p) {
		deserializer = p;
		return this;
	}

	public Request setAuthBasic(String username, String password) {
		return header("Authorization","Basic "
				+ Base64.encodeBase64((username + ":" + password)
						.getBytes()));
	}

	protected HttpResponse getResponse() throws IOException {
		return http.execute(delegate);
	}

	public Request header(String key, String value) {
		delegate.addHeader(key, value);
		return this;
	}

	public Request deserializeWith(Class<? extends Deserializer> p) {
		deserializer = IO.deserializer(p);
		return this;
	}
}
