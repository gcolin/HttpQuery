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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.util.logging.Logger;

public final class ClientDeserializers {

	private ClientDeserializers(){}
	
	public static final ClientDeserializer<String> STRING = new ClientDeserializer<String>() {

		public String call(HttpURLConnection conn)
				throws IOException {
		    char[] b = new char[512];
            int c = 0;
            Reader r = new InputStreamReader(conn.getInputStream(),
                    IO.getCharset());
            StringWriter s = new StringWriter();
            while((c=r.read(b))>0)
            {
                s.write(b,0,c);
            }
            return s.toString();
		}

		@Override
		public boolean closable() {
			return true;
		}
	};

	public static final ClientDeserializer<byte[]> BYTE = new ClientDeserializer<byte[]>() {

		public byte[] call(HttpURLConnection conn)
				throws IOException {
		    byte[] b = new byte[512];
		    int c = 0;
		    InputStream in = conn.getInputStream();
		    ByteArrayOutputStream out = new ByteArrayOutputStream();
		    while((c=in.read(b))>0)
		    {
		        out.write(b,0,c);
		    }
			return out.toByteArray();
		}

		@Override
		public boolean closable() {
			return true;
		}
	};
	
	public static final ClientDeserializer<Integer> VOID = new ClientDeserializer<Integer>() {

		public Integer call(HttpURLConnection conn)
				throws IOException {
			return conn.getResponseCode();
		}

		@Override
		public boolean closable() {
			return true;
		}
	};

	public static final ClientDeserializer<InputStream> STREAM = new ClientDeserializer<InputStream>() {

		public InputStream call(HttpURLConnection conn)
				throws IOException {
			return conn.getInputStream();
		}

		@Override
		public boolean closable() {
			return false;
		}
	};

	public static <T> ClientDeserializer<T> object(final Class<T> target,
			final Deserializer deserializer) {
		return new ObjectHttpClientDeserializer<T>(target,deserializer);
	}

	public static ClientDeserializer<Response> reponse(final Deserializer deserializer) {

		return new ClientDeserializer<Response>() {

			public Response call(HttpURLConnection conn)
					throws IOException {
				return new ResponseImpl(conn, deserializer);
			}

			@Override
			public boolean closable() {
				return false;
			}
		};
	}
	
	public static final class ObjectHttpClientDeserializer<T> implements ClientDeserializer<T> {

		private final Class<T> target;
		private final Deserializer deserializer;
		
		public ObjectHttpClientDeserializer(Class<T> target,Deserializer deserializer){
			this.target = target;
			this.deserializer = deserializer;
		}
		
		public T call(HttpURLConnection conn)
				throws IOException {
			Deserializer d = deserializer;
			if (conn != null) {
				if (d == null) {
					d = IO.deserializer(
					        conn.getHeaderField("Content-Type"), target);
				}
				if (d != null) {
					return d.toObject(conn.getInputStream(), target);
				} else {
					Logger.getLogger(this.getClass().getName()).warning(
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
