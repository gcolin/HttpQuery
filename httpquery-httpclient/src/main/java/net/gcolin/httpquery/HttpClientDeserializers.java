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
import java.util.logging.Logger;

import net.gcolin.httpquery.spi.InputStreamSerializer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public final class HttpClientDeserializers {

    public static final HttpClientDeserializer<String> STRING = new StringHttpClientDeserializer();

    public static final HttpClientDeserializer<byte[]> BYTE = new HttpClientDeserializer<byte[]>() {

        @Override
        public byte[] call(HttpEntity entity, HttpResponse response)
                throws IOException {
            byte[] b = new byte[InputStreamSerializer.BUFFER_SIZE];
            int c = 0;
            InputStream in = entity.getContent();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            while ((c = in.read(b)) > 0) {
                out.write(b, 0, c);
            }
            return out.toByteArray();
        }

        @Override
        public boolean closable() {
            return true;
        }
    };

    public static final HttpClientDeserializer<Integer> VOID = new HttpClientDeserializer<Integer>() {

        @Override
        public Integer call(HttpEntity entity, HttpResponse response)
                throws IOException {
            return response.getStatusLine().getStatusCode();
        }

        @Override
        public boolean closable() {
            return true;
        }
    };

    public static final HttpClientDeserializer<InputStream> STREAM = new HttpClientDeserializer<InputStream>() {

        @Override
        public InputStream call(HttpEntity entity, HttpResponse response)
                throws IOException {
            return entity.getContent();
        }

        @Override
        public boolean closable() {
            return false;
        }
    };
    
    private HttpClientDeserializers() {
    }

    public static <T> HttpClientDeserializer<T> object(final Class<T> target,
            final Deserializer deserializer) {
        return new ObjectHttpClientDeserializer<T>(target, deserializer);
    }

    public static HttpClientDeserializer<Response> reponse(
            final Deserializer deserializer) {

        return new HttpClientDeserializer<Response>() {

            @Override
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
    
    public static final class StringHttpClientDeserializer implements HttpClientDeserializer<String> {

        @Override
        public String call(HttpEntity entity, HttpResponse response)
                throws IOException {
            char[] b = new char[InputStreamSerializer.BUFFER_SIZE];
            int c = 0;
            Reader r = new InputStreamReader(entity.getContent(),
                    IO.getCharset());
            StringWriter s = new StringWriter();
            while ((c = r.read(b)) > 0) {
                s.write(b, 0, c);
            }
            return s.toString();
        }

        @Override
        public boolean closable() {
            return true;
        }
    }

    public static final class ObjectHttpClientDeserializer<T> implements
            HttpClientDeserializer<T> {

        private final Class<T> target;
        private final Deserializer deserializer;

        public ObjectHttpClientDeserializer(Class<T> target,
                Deserializer deserializer) {
            this.target = target;
            this.deserializer = deserializer;
        }

        @Override
        public T call(HttpEntity entity, HttpResponse response)
                throws IOException {
            Deserializer d = deserializer;
            if (entity != null) {
                if (d == null) {
                    d = IO.deserializer(response.getEntity().getContentType()
                            .getValue(), target);
                }
                if (d != null) {
                    return d.toObject(entity.getContent(), target);
                } else {
                    Logger.getLogger(this.getClass().getName()).warning(
                            "no deserializer found : \n" + entity.getContent());
                }
            }
            return null;
        }

        @Override
        public boolean closable() {
            return true;
        }
    }
}
