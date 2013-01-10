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
import java.io.UnsupportedEncodingException;

import net.gcolin.httpquery.Http;
import net.gcolin.httpquery.HttpHandler;
import net.gcolin.httpquery.Request;
import net.gcolin.httpquery.RequestWithPayload;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.slf4j.LoggerFactory;

public class HttpHandlerImpl implements HttpHandler{

	public static final String ERROR_MESSAGE = "cannot send request";
	
	@Override
	public Request get(String uri) {
		return new RequestImpl(new HttpGet(uri));
	}

	@Override
	public Request delete(String uri) {
		return new RequestImpl(new HttpDelete(uri));
	}

	@Override
	public RequestWithPayload put(String uri, Object obj) {
		return new RequestWithPayloadImpl(new HttpPut(uri), obj);
	}

	@Override
	public Request put(String uri, byte[] data) {
		HttpPut put=new HttpPut(uri);
		put.setEntity(new ByteArrayEntity(data));
		return new RequestImpl(put);
	}

	@Override
	public Request put(String uri, String str) {
		HttpPut put=new HttpPut(uri);
		try {
			put.setEntity(new StringEntity(str));
		} catch (UnsupportedEncodingException e) {
			LoggerFactory.getLogger(Http.class).error(e.getMessage(),e);
		}
		return new RequestImpl(put);
	}

	@Override
	public Request put(String uri, InputStream inStream) {
		HttpPut put=new HttpPut(uri);
		put.setEntity(new InputStreamEntity(inStream,-1));
		return new RequestImpl(put);
	}

	@Override
	public RequestWithPayload post(String uri, Object obj) {
		return new RequestWithPayloadImpl(new HttpPost(uri), obj);
	}

	@Override
	public Request post(String uri, byte[] data) {
		HttpPost post=new HttpPost(uri);
		post.setEntity(new ByteArrayEntity(data));
		return new RequestImpl(post);
	}

	@Override
	public Request post(String uri, InputStream inStream) {
		HttpPost post=new HttpPost(uri);
		post.setEntity(new InputStreamEntity(inStream,-1));
		return new RequestImpl(post);
	}

	@Override
	public Request post(String uri, String str) {
		HttpPost post=new HttpPost(uri);
		try {
			post.setEntity(new StringEntity(str));
		} catch (UnsupportedEncodingException e) {
			LoggerFactory.getLogger(Http.class).error(e.getMessage(),e);
		}
		return new RequestImpl(post);
	}

	@Override
	public Request trace(String uri) {
		return new RequestImpl(new HttpTrace(uri));
	}

	@Override
	public Request head(String uri) {
		return new RequestImpl(new HttpHead(uri));
	}

	@Override
	public Request options(String uri) {
		return new RequestImpl(new HttpOptions(uri));
	}


}
