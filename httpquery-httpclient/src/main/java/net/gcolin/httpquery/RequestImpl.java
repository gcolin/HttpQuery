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

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;

public class RequestImpl extends AbstractElement implements Request {

    private HttpRequestBase delegate;
    private Deserializer deserializer;

    /**
     * http client
     */
    private HttpClient http = ClientStrategy.createClient();

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
        return header(
                "Authorization",
                "Basic "
                        + Base64.encodeBase64((username + ":" + password)
                                .getBytes()));
    }

    protected HttpResponse getResponse() throws IOException {
        if (!delegate.containsHeader(HttpHeaders.ACCEPT)) {
            delegate.addHeader(HttpHeaders.ACCEPT, "*/*");
        }
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

    public int send() {
        return callback(HttpClientDeserializers.VOID);
    }

    @Override
    public void setDelegate(Object o) {
        if (o != null && o instanceof HttpClient) {
            http = (HttpClient) o;
        }
    }
}
