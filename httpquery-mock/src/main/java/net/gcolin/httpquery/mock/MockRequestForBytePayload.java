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


import java.lang.reflect.Method;
import java.util.Map;

import org.slf4j.LoggerFactory;

import net.gcolin.httpquery.Request;

public class MockRequestForBytePayload extends MockRequest{

	private byte[] payload;
	
	public MockRequestForBytePayload(String method,String uri,byte[] payload) {
		super(method,uri);
		this.payload = new byte[payload.length];
		System.arraycopy(payload, 0, this.payload, 0, payload.length);
	}
	
	@Override
	protected void add(Request r){
		try {
			@SuppressWarnings("unchecked")
			Map<Item<String, byte[]>, Request>  map = (Map<Item<String, byte[]>, Request>) getter().invoke(MockHttpHandler.getInstance());
			map.put(new Item<String, byte[]>(getUri(),payload), r);
		} catch (Exception e) {
			LoggerFactory.getLogger(this.getClass()).error("cannot add request",e);
		}
	}
	
	@Override
	protected RequestImpl get(){
		try {
			@SuppressWarnings("unchecked")
			Map<Item<String, byte[]>, Request>  map = (Map<Item<String, byte[]>, Request>) getter().invoke(MockHttpHandler.getInstance());
			Request r = map.get(new Item<String, byte[]>(getUri(),payload));
			if(r!=null){
				return (RequestImpl) r;
			}
		} catch (Exception e) {
			LoggerFactory.getLogger(this.getClass()).error("cannot get request",e);
		}
		return new RequestImpl();
	}
	
	protected Method getter() throws NoSuchMethodException{
		return MockHttpHandler.class.getMethod("get"+getMethod()+"sByte");
	}
}
