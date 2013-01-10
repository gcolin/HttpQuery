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
import java.io.InputStreamReader;

import net.gcolin.httpquery.Deserializer;
import net.gcolin.httpquery.IO;
import net.gcolin.httpquery.Response;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public final class HttpClientDeserializers {

	private HttpClientDeserializers(){}
	
	public static final HttpClientDeserializer<String> STRING = new HttpClientDeserializer<String>() {

		public String call(HttpEntity entity, HttpResponse response)
				throws IOException {
			return IOUtils.toString(new InputStreamReader(entity.getContent(),
					IO.getCharset()));
		}

		@Override
		public boolean closable() {
			return true;
		}
	};

	public static final HttpClientDeserializer<byte[]> BYTE = new HttpClientDeserializer<byte[]>() {

		public byte[] call(HttpEntity entity, HttpResponse response)
				throws IOException {
			return IOUtils.toByteArray(entity.getContent());
		}

		@Override
		public boolean closable() {
			return true;
		}
	};

	public static final HttpClientDeserializer<InputStream> STREAM = new HttpClientDeserializer<InputStream>() {

		public InputStream call(HttpEntity entity, HttpResponse response)
				throws IOException {
			return entity.getContent();
		}

		@Override
		public boolean closable() {
			return false;
		}
	};

	public static final <T> HttpClientDeserializer<T> OBJECT(final Class<T> target,
			final Deserializer deserializer) {
		return new HttpClientDeserializer<T>() {

			public T call(HttpEntity entity, HttpResponse response)
					throws IOException {
				Deserializer d = deserializer;
				if (entity != null) {
					if (d == null) {
						d = IO.deserializer(
								response.getLastHeader("Content-Type")
										.getValue(), target);
					}
					if (d != null) {
						return d.toObject(entity.getContent(), target);
					} else {
						LogFactory.getLog(this.getClass()).warn(
								"no deserializer found");
					}
				}
				return null;
			}

			@Override
			public boolean closable() {
				return true;
			}
		};
	}

	public static final HttpClientDeserializer<Response> RESPONSE(final Deserializer deserializer) {

		return new HttpClientDeserializer<Response>() {

			public Response call(HttpEntity entity, HttpResponse response)
					throws IOException {
				return new ResponseImpl(response, deserializer);
			}

			@Override
			public boolean closable() {
				return false;
			}
		};
	}
}
