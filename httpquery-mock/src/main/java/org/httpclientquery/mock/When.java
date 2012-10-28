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

public class When {

	public static MockRequest get(String uri){
		return new MockRequest("Get",uri);
	}
	
	public static MockRequest delete(String uri){
		return new MockRequest("Delete",uri);
	}
	
	public static MockRequest trace(String uri){
		return new MockRequest("Trace",uri);
	}
	
	public static MockRequest head(String uri){
		return new MockRequest("Head",uri);
	}
	
	public static MockRequest options(String uri){
		return new MockRequest("Option",uri);
	}
	
	public static MockRequestWithPayload post(String uri, Object obj) {
		return new MockRequestWithPayload("Post", uri, obj);
	}

	public static MockRequest post(String uri, byte[] data) {
		return new MockRequestForBytePayload("Post", uri, data);
	}

	public static MockRequest post(String uri, InputStream inStream) {
		return new MockRequestForStreamPayload("Post", uri, inStream);
	}

	public static MockRequest post(String uri, String str) {
		return new MockRequestForStringPayload("Post", uri, str);
	}
	
	public static MockRequestWithPayload put(String uri, Object obj) {
		return new MockRequestWithPayload("Put", uri, obj);
	}

	public static MockRequest put(String uri, byte[] data) {
		return new MockRequestForBytePayload("Put", uri, data);
	}

	public static MockRequest put(String uri, InputStream inStream) {
		return new MockRequestForStreamPayload("Put", uri, inStream);
	}

	public static MockRequest put(String uri, String str) {
		return new MockRequestForStringPayload("Put", uri, str);
	}
}
