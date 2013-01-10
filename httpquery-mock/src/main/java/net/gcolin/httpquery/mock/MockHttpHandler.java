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

import net.gcolin.httpquery.Http;
import net.gcolin.httpquery.HttpHandler;
import net.gcolin.httpquery.Request;
import net.gcolin.httpquery.RequestWithPayload;

public class MockHttpHandler implements HttpHandler {

	private Map<String, Request> gets = new HashMap<String, Request>();
	private Map<String, Request> deletes = new HashMap<String, Request>();
	private Map<String, Request> traces = new HashMap<String, Request>();
	private Map<String, Request> heads = new HashMap<String, Request>();
	private Map<String, Request> options = new HashMap<String, Request>();

	private Map<Item<String, Object>, RequestWithPayload> putsObject = new HashMap<Item<String, Object>, RequestWithPayload>();
	private Map<Item<String, byte[]>, Request> putsByte = new HashMap<Item<String, byte[]>, Request>();
	private Map<Item<String, String>, Request> putsString = new HashMap<Item<String, String>, Request>();
	private Map<Item<String, InputStream>, Request> putsInputStream = new HashMap<Item<String, InputStream>, Request>();

	private Map<Item<String, Object>, RequestWithPayload> postsObject = new HashMap<Item<String, Object>, RequestWithPayload>();
	private Map<Item<String, byte[]>, Request> postsByte = new HashMap<Item<String, byte[]>, Request>();
	private Map<Item<String, String>, Request> postsString = new HashMap<Item<String, String>, Request>();
	private Map<Item<String, InputStream>, Request> postsInputStream = new HashMap<Item<String, InputStream>, Request>();

	private static MockHttpHandler instance;

	public static void bind(boolean b) {
		if (b) {
			instance = new MockHttpHandler();
			Http.setHandler(instance);
		} else {
			Http.initHandler();
		}
	}

	public static MockHttpHandler getInstance() {
		return instance;
	}

	public Map<String, Request> getGets() {
		return gets;
	}

	public Map<String, Request> getDeletes() {
		return deletes;
	}

	public Map<String, Request> getTraces() {
		return traces;
	}

	public Map<String, Request> getHeads() {
		return heads;
	}

	public Map<String, Request> getOptions() {
		return options;
	}

	public Map<Item<String, Object>, RequestWithPayload> getPutsObject() {
		return putsObject;
	}

	public Map<Item<String, byte[]>, Request> getPutsByte() {
		return putsByte;
	}

	public Map<Item<String, String>, Request> getPutsString() {
		return putsString;
	}

	public Map<Item<String, InputStream>, Request> getPutsInputStream() {
		return putsInputStream;
	}

	public Map<Item<String, Object>, RequestWithPayload> getPostsObject() {
		return postsObject;
	}

	public Map<Item<String, byte[]>, Request> getPostsByte() {
		return postsByte;
	}

	public Map<Item<String, String>, Request> getPostsString() {
		return postsString;
	}

	public Map<Item<String, InputStream>, Request> getPostsInputStream() {
		return postsInputStream;
	}

	@Override
	public Request get(String uri) {
		return gets.get(uri);
	}

	@Override
	public Request delete(String uri) {
		return deletes.get(uri);
	}

	@Override
	public RequestWithPayload put(String uri, Object obj) {
		return putsObject.get(new Item<String, Object>(uri, obj));
	}

	@Override
	public Request put(String uri, byte[] data) {
		return putsByte.get(new Item<String, byte[]>(uri, data));
	}

	@Override
	public Request put(String uri, String str) {
		return putsString.get(new Item<String, String>(uri, str));
	}

	@Override
	public Request put(String uri, InputStream inStream) {
		return putsInputStream
				.get(new Item<String, InputStream>(uri, inStream));
	}

	@Override
	public RequestWithPayload post(String uri, Object obj) {
		return postsObject.get(new Item<String, Object>(uri, obj));
	}

	@Override
	public Request post(String uri, byte[] data) {
		return postsByte.get(new Item<String, byte[]>(uri, data));
	}

	@Override
	public Request post(String uri, InputStream inStream) {
		return postsInputStream
				.get(new Item<String, InputStream>(uri, inStream));
	}

	@Override
	public Request post(String uri, String str) {
		return postsString.get(new Item<String, String>(uri, str));
	}

	@Override
	public Request trace(String uri) {
		return traces.get(uri);
	}

	@Override
	public Request head(String uri) {
		return heads.get(uri);
	}

	@Override
	public Request options(String uri) {
		return options.get(uri);
	}

}
