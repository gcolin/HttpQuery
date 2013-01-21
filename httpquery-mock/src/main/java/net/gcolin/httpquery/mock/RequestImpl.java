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
package net.gcolin.httpquery.mock;


import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.gcolin.httpquery.Deserializer;
import net.gcolin.httpquery.Request;
import net.gcolin.httpquery.Response;

public class RequestImpl implements Request {

	private Map<Class<? extends Deserializer>,Map<Class<?>,Object>> asMap=new HashMap<Class<? extends Deserializer>,Map<Class<?>,Object>>();
	private String asString;
	private InputStream inStream;
	private byte[] asBytes;
	private String contentType,acceptType;
	private Map<String,String> headers=new HashMap<String,String>();
	private Class<? extends Deserializer> d;
	private String authBasicPassword;
	private String authBasicLogin;
	private Response response;
	private int status;
	
	public Map<Class<? extends Deserializer>,Map<Class<?>,Object>> getAsMap() {
		return asMap;
	}

	public String getContentType() {
		return contentType;
	}

	public String getAcceptType() {
		return acceptType;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public String getAuthBasicPassword() {
		return authBasicPassword;
	}

	public String getAuthBasicLogin() {
		return authBasicLogin;
	}

	public void setAsString(String asString) {
		this.asString = asString;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setInStream(InputStream inStream) {
		this.inStream = inStream;
	}

	public void setAsBytes(byte[] asBytes) {
		this.asBytes = new byte[asBytes.length];
		System.arraycopy(asBytes, 0, this.asBytes, 0, asBytes.length);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T as(Class<T> target) {
		return (T)asMap.get(d).get(target);
	}

	@Override
	public String asString() {
		return asString;
	}

	@Override
	public InputStream asStream() {
		return inStream;
	}

	@Override
	public byte[] asBytes() {
		return asBytes;
	}

	@Override
	public Request setContentType(String s) {
		contentType = s;
		return this;
	}

	@Override
	public Request setAcceptType(String s) {
		acceptType = s;
		return this;
	}

	@Override
	public Request header(String key, String value) {
		headers.put(key, value);
		return this;
	}

	@Override
	public Request deserializeWith(Deserializer p) {
		d = p.getClass();
		return this;
	}

	@Override
	public Request deserializeWith(Class<? extends Deserializer> p) {
		d = p;
		return this;
	}

	@Override
	public Request setAuthBasic(String username, String password) {
		authBasicPassword = password;
		authBasicLogin = username;
		return this;
	}

	@Override
	public Response asResponse() {
		return response;
	}

	public void setAsResponse(Response response) {
		this.response = response;
	}

	public int send() {
		return status;
	}
}
