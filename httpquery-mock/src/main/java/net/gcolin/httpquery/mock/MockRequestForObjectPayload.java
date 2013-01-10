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
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;

import net.gcolin.httpquery.Deserializer;
import net.gcolin.httpquery.RequestWithPayload;
import net.gcolin.httpquery.Serializer;

public class MockRequestForObjectPayload{

	private Object payload;
	private String uri;
	private String method;
	private Class<? extends Deserializer> deserializer;
	private Class<? extends Serializer> serializerSerializeWith;
	private Class<? extends Serializer> serializerSerializeWithClass;
	
	public MockRequestForObjectPayload(
			String method,
			String uri,
			Object payload,
			Class<? extends Serializer> serializerSerializeWith,
			Class<? extends Serializer> serializerSerializeWithClass) {
		this.uri=uri;
		this.method=method;
		this.payload=payload;
		this.serializerSerializeWith=serializerSerializeWith;
		this.serializerSerializeWithClass=serializerSerializeWithClass;
	}
	
	public void asStringReturn(String s){
		RequestWithPayloadImpl r=get();
		RequestImpl req=get(r);
		req.setAsString(s);
		add(r);
	}
	
	public void asByteReturn(byte[] s){
		RequestWithPayloadImpl r=get();
		RequestImpl req=get(r);
		req.setAsBytes(s);
		add(r);
	}
	
	public void asInputStreamReturn(InputStream s){
		RequestWithPayloadImpl r=get();
		RequestImpl req=get(r);
		req.setInStream(s);
		add(r);
	}
	
	public void asReturn(Class<?> target,Object o){
		RequestWithPayloadImpl r=get();
		RequestImpl req=get(r);
		Map<Class<?>,Object> map=req.getAsMap().get(deserializer);
		if(map==null){
			map=new HashMap<Class<?>, Object>();
			req.getAsMap().put(deserializer, map);
		}
		map.put(target, o);
		add(r);
	}
	
	public MockRequestForObjectPayload deserializeWith(Deserializer p) {
		deserializer=p.getClass();
		return this;
	}

	public MockRequestForObjectPayload deserializeWith(Class<? extends Deserializer> p) {
		deserializer=p;
		return this;
	}
	
	protected void add(RequestWithPayloadImpl r){
		try {
			@SuppressWarnings("unchecked")
			Map<Item<String, Object>, RequestWithPayload>  map = (Map<Item<String, Object>, RequestWithPayload>) getter().invoke(MockHttpHandler.getInstance());
			map.put(new Item<String, Object>(uri,payload), (RequestWithPayload) r);
		} catch (Exception e) {
			LoggerFactory.getLogger(this.getClass()).error("cannot add request",e);
		}
	}
	
	protected RequestImpl get(RequestWithPayloadImpl r){
		if(serializerSerializeWithClass!=null){
			RequestImpl req=r.getRequestSerializeWithClass();
			if(req==null){
				req=new RequestImpl();
				r.setRequestSerializeWithClass(req);
			}
			return req;
		}else if(serializerSerializeWith!=null){
			RequestImpl req=r.getRequestSerializeWith();
			if(req==null){
				req=new RequestImpl();
				r.setRequestSerializeWith(req);
			}
			return req;
		}else{
			RequestImpl req=r.getRequestSerialize();
			if(req==null){
				req=new RequestImpl();
				r.setRequestSerialize(req);
			}
			return req;
		}
	}
	
	protected RequestWithPayloadImpl get(){
		try {
			@SuppressWarnings("unchecked")
			Map<Item<String, Object>, RequestWithPayload>  map = (Map<Item<String, Object>, RequestWithPayload>) getter().invoke(MockHttpHandler.getInstance());
			RequestWithPayload r = map.get(new Item<String, Object>(uri,payload));
			if(r!=null){
				return (RequestWithPayloadImpl) r;
			}
		} catch (Exception e) {
			LoggerFactory.getLogger(this.getClass()).error("cannot get request",e);
		}
		return new RequestWithPayloadImpl();
	}
	
	protected Method getter() throws NoSuchMethodException{
		return MockHttpHandler.class.getMethod("get"+method+"sObject");
	}
}
