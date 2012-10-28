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

import org.httpclientquery.Request;
import org.httpclientquery.RequestWithPayload;
import org.httpclientquery.Serializer;
import org.junit.Assert;


public class RequestWithPayloadImpl implements RequestWithPayload{

	private Class<? extends Serializer> serializerSerializeWith;
	private Class<? extends Serializer> serializerSerializeWithClass;
	private RequestImpl requestSerializeWith;
	private RequestImpl requestSerialize;
	
	private RequestImpl requestSerializeWithClass;

	public void setSerializerSerializeWith(
			Class<? extends Serializer> serializerSerializeWith) {
		this.serializerSerializeWith = serializerSerializeWith;
	}

	public void setRequestSerializeWith(RequestImpl requestSerializeWith) {
		this.requestSerializeWith = requestSerializeWith;
	}

	public void setRequestSerialize(RequestImpl requestSerialize) {
		this.requestSerialize = requestSerialize;
	}

	public void setSerializerSerializeWithClass(
			Class<? extends Serializer> serializerSerializeWithClass) {
		this.serializerSerializeWithClass = serializerSerializeWithClass;
	}

	public void setRequestSerializeWithClass(RequestImpl requestSerializeWithClass) {
		this.requestSerializeWithClass = requestSerializeWithClass;
	}

	public RequestImpl getRequestSerializeWith() {
		return requestSerializeWith;
	}

	public RequestImpl getRequestSerialize() {
		return requestSerialize;
	}

	public RequestImpl getRequestSerializeWithClass() {
		return requestSerializeWithClass;
	}

	public Request serializeWith(Serializer s) {
		Assert.assertSame(serializerSerializeWith, s.getClass());
		return requestSerializeWith;
	}

	public Request serialize() {
		return requestSerialize;
	}

	@Override
	public Request serializeWith(Class<? extends Serializer> serializer) {
		Assert.assertSame(serializerSerializeWithClass, serializer);
		return requestSerializeWithClass;
	}

}
