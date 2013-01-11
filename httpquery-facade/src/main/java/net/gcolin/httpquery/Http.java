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

import org.slf4j.LoggerFactory;

public final class Http {

	private static HttpHandler handler;
	
	static{
		initHandler();
	}
	
	private Http(){}
	
	public static void initHandler() {
		try {
			Class<?> c=Http.class.getClassLoader().loadClass("net.gcolin.httpquery.HttpHandlerImpl");
			handler = (HttpHandler) c.newInstance();
		} catch (Exception e) {
			LoggerFactory.getLogger(Http.class).error("cannot find an implementation of HttpHandler");
		}
	}
	
	public static void setHandler(HttpHandler handler) {
		Http.handler = handler;
	}

	public static Request get(String uri){
		return handler.get(uri);
	}
	
	public static Request delete(String uri){
		return handler.delete(uri);
	}
	
	public static RequestWithPayload put(String uri,Object obj){
		return handler.put(uri, obj);
	}
	
	public static Request put(String uri,byte[] data){
		return handler.put(uri, data);
	}
		
	public static Request put(String uri,String str){
		return handler.put(uri, str);
	}
	
	public static Request put(String uri,InputStream inStream){
		return handler.put(uri,inStream);
	}
	
	public static RequestWithPayload post(String uri,Object obj){
		return handler.post(uri, obj);
	}
	
	public static Request post(String uri,byte[] data){
		return handler.post(uri,data);
	}
	
	public static Request post(String uri,InputStream inStream){
		return handler.post(uri, inStream);
	}
		
	public static Request post(String uri,String str){
		return handler.post(uri,str);
	}
	
	public static Request trace(String uri){
		return handler.trace(uri);
	}
	
	public static Request head(String uri){
		return handler.head(uri);
	}
	
	public static Request options(String uri){
		return handler.options(uri);
	}
}
