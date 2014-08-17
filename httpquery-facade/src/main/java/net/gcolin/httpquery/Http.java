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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * facade for using httpQuery.
 * 
 * @author Gael
 *
 */
public final class Http {

	/**
	 * the handler is the implementation of this class.
	 */
	private static HttpHandler handler;
	
	static{
		initHandler();
	}
	
	/**
	 * this facade cannot be instantiate.
	 */
	private Http(){}
	
	/**
	 * look for the implementation of the handler.
	 */
	public static void initHandler() {
		try {
			Class<?> c=Http.class.getClassLoader().loadClass("net.gcolin.httpquery.HttpHandlerImpl");
			handler = (HttpHandler) c.newInstance();
		} catch (Exception e) {
			Logger.getLogger(Http.class.getName()).log(Level.SEVERE,"cannot find an implementation of HttpHandler");
		}
	}
	
	/**
	 * set manually the handler. for mocking for example.
	 * 
	 * @param handler : implementation of the handler
	 */
	public static void setHandler(HttpHandler handler) {
		Http.handler = handler;
	}

	/**
	 * build a get request.
	 * 
	 * @param uri : the URL
	 * @return a request
	 */
	public static Request get(String uri){
		return handler.get(uri);
	}
	
	/**
	 * build a delete request.
	 * 
	 * @param uri : the URL
	 * @return a request
	 */
	public static Request delete(String uri){
		return handler.delete(uri);
	}
	
	/**
	 * build a put request.
	 * 
	 * @param uri : the URL
	 * @param obj : the data in payload
	 * @return a request
	 */
	public static RequestWithPayload put(String uri,Object obj){
		return handler.put(uri, obj);
	}
	
	/**
	 * build a put request.
	 * 
	 * @param uri : the URL
	 * @param data : the data in payload
	 * @return a request
	 */
	public static Request put(String uri,byte[] data){
		return handler.put(uri, data);
	}
		
	/**
	 * build a put request.
	 * 
	 * @param uri : the URL
	 * @param str : the data in payload
	 * @return a request
	 */
	public static Request put(String uri,String str){
		return handler.put(uri, str);
	}
	
	/**
	 * build a put request.
	 * 
	 * @param uri : the URL
	 * @param inStream : the data in payload
	 * @return a request
	 */
	public static Request put(String uri,InputStream inStream){
		return handler.put(uri,inStream);
	}
	
	/**
	 * build a post request.
	 * 
	 * @param uri : the URL
	 * @param obj : the data in payload
	 * @return a request
	 */
	public static RequestWithPayload post(String uri,Object obj){
		return handler.post(uri, obj);
	}
	
	/**
	 * build a post request.
	 * 
	 * @param uri : the URL
	 * @param data : the data in payload
	 * @return a request
	 */
	public static Request post(String uri,byte[] data){
		return handler.post(uri,data);
	}
	
	/**
	 * build a post request.
	 * 
	 * @param uri : the URL
	 * @param inStream : the data in payload
	 * @return a request
	 */
	public static Request post(String uri,InputStream inStream){
		return handler.post(uri, inStream);
	}
		
	/**
	 * build a post request.
	 * 
	 * @param uri : the URL
	 * @param str : the data in payload
	 * @return a request
	 */
	public static Request post(String uri,String str){
		return handler.post(uri,str);
	}
	
	/**
	 * build a trace request.
	 * 
	 * @param uri : the URL
	 * @return a request
	 */
	public static Request trace(String uri){
		return handler.trace(uri);
	}
	
	/**
	 * build a head request.
	 * 
	 * @param uri : the URL
	 * @return a request
	 */
	public static Request head(String uri){
		return handler.head(uri);
	}
	
	/**
	 * build a options request.
	 * 
	 * @param uri : the URL
	 * @return a request
	 */
	public static Request options(String uri){
		return handler.options(uri);
	}
}
