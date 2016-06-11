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
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class RequestImpl extends AbstractElement implements Request {

    private Deserializer deserializer;
    private String method;
    private String uri;
    private byte[] data;
    private Map<String, String> headers = new HashMap<String, String>();
    private CookieManager cm = ClientStrategy.createClient();

    public RequestImpl(String method, String uri) {
        this.method = method;
        this.uri = uri;
    }

    public RequestImpl data(byte[] b) {
        this.data = b;
        return this;
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

    public Response asResponse() {
        return callback(ClientDeserializers.reponse(deserializer));
    }

    public RequestImpl setContentType(String s) {
        header("Content-Type", s);
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
        return header(
                "Authorization",
                "Basic "
                        + Base64.getEncoder().encodeToString(
                                (username + ":" + password).getBytes()));
    }

    protected HttpURLConnection getResponse() throws IOException {
        URL url = new URL(uri);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setRequestMethod(method);
        cm.setCookies(httpCon);
        if (data != null) {
            httpCon.setDoOutput(true);
            httpCon.getOutputStream().write(data);
        }
        httpCon.connect();
        cm.storeCookies(httpCon);
        return httpCon;
    }

    public Request header(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public Request deserializeWith(Class<? extends Deserializer> p) {
        deserializer = IO.deserializer(p);
        return this;
    }

    public int send() {
        return callback(ClientDeserializers.VOID);
    }

    @Override
    public void setDelegate(Object o) {
        throw new UnsupportedOperationException();
    }
}
