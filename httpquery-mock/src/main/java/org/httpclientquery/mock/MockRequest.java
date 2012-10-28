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
package org.httpclientquery.mock;


import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.httpclientquery.Deserializer;
import org.httpclientquery.Request;
import org.httpclientquery.Response;

public class MockRequest {

	private String uri;
	private String method;
	private Class<? extends Deserializer> deserializer;
	
	public MockRequest(String method,String uri) {
		this.uri=uri;
		this.method=method;
	}

	public void asStringReturn(String s){
		RequestImpl r=get();
		r.setAsString(s);
		add(r);
	}
	
	public void asByteReturn(byte[] s){
		RequestImpl r=get();
		r.setAsBytes(s);
		add(r);
	}
	
	public void asResponseReturn(Response response){
		RequestImpl r=get();
		r.setAsResponse(response);
		add(r);
	}
	
	public void asInputStreamReturn(InputStream s){
		RequestImpl r=get();
		r.setInStream(s);
		add(r);
	}
	
	public void asReturn(Class<?> target,Object o){
		RequestImpl r=get();
		Map<Class<?>,Object> map=r.getAsMap().get(deserializer);
		if(map==null){
			map=new HashMap<Class<?>, Object>();
			r.getAsMap().put(deserializer, map);
		}
		map.put(target, o);
		add(r);
	}
	
	public MockRequest deserializeWith(Deserializer p) {
		deserializer=p.getClass();
		return this;
	}

	public MockRequest deserializeWith(Class<? extends Deserializer> p) {
		deserializer=p;
		return this;
	}
	
	protected void add(Request r){
		try {
			@SuppressWarnings("unchecked")
			Map<String, Request>  map = (Map<String, Request>) getter().invoke(MockHttpHandler.getInstance());
			map.put(uri, r);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected RequestImpl get(){
		try {
			@SuppressWarnings("unchecked")
			Map<String, Request>  map = (Map<String, Request>) getter().invoke(MockHttpHandler.getInstance());
			Request r = map.get(uri);
			if(r!=null){
				return (RequestImpl) r;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new RequestImpl();
	}
	
	protected Method getter() throws SecurityException, NoSuchMethodException{
		return MockHttpHandler.class.getMethod("get"+method+"s");
	}

	public String getUri() {
		return uri;
	}

	public String getMethod() {
		return method;
	}
}
