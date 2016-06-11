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
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

public class ResponseImpl extends AbstractElement implements Response {

    private HttpURLConnection httpCon;
    private Deserializer deserializer;

    public ResponseImpl(HttpURLConnection httpCon, Deserializer deserializer) {
        this.httpCon = httpCon;
        this.deserializer = deserializer;
    }

    public <T> T as(Class<T> target) {
        return callback(ClientDeserializers.object(target, deserializer));
    }

    public String asString() {
        return callback(ClientDeserializers.STRING);
    }

    public byte[] asBytes() {
        return callback(ClientDeserializers.BYTE);
    }

    public InputStream asStream() {
        return callback(ClientDeserializers.STREAM);
    }

    @Override
    public String header(String key) {
        return httpCon.getHeaderField(key);
    }

    @Override
    public void close() {
        httpCon.disconnect();
    }

    @Override
    public Collection<String> headers(String key) {
        Collection<String> list = new ArrayList<String>();
        for (String s : httpCon.getHeaderField(key).split(";")) {
            list.add(s.trim());
        }
        return list;
    }

    @Override
    public Collection<Entry<String, String>> headers() {
        Collection<Entry<String, String>> list = new ArrayList<Entry<String, String>>();
        for (Entry<String, List<String>> h : httpCon.getHeaderFields()
                .entrySet()) {
            for (String e : h.getValue()) {
                list.add(new HeaderEntry(h.getKey(), e));
            }
        }
        return list;
    }

    @Override
    public int status() {
        try {
            return httpCon.getResponseCode();
        } catch (IOException e) {
            throw new ResponseException(e);
        }
    }

    private static final class HeaderEntry implements Entry<String, String> {

        private String k;
        private String v;

        public HeaderEntry(String k, String v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public String setValue(String value) {
            String old = v;
            v = value;
            return old;
        }

        @Override
        public String getValue() {
            return v;
        }

        @Override
        public String getKey() {
            return k;
        }

    }

    @Override
    protected HttpURLConnection getResponse() throws IOException {
        return httpCon;
    }

}
