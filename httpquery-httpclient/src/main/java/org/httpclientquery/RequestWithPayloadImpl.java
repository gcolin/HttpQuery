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
package org.httpclientquery;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.httpclientquery.IO;
import org.httpclientquery.Request;
import org.httpclientquery.RequestWithPayload;
import org.httpclientquery.Serializer;
import org.slf4j.LoggerFactory;

public class RequestWithPayloadImpl implements RequestWithPayload{

	private HttpEntityEnclosingRequestBase delegate;
	private Object obj;

	public RequestWithPayloadImpl(HttpEntityEnclosingRequestBase delegate,
			Object obj) {
		this.delegate = delegate;
		this.obj = obj;
	}

	public Request serializeWith(Serializer s) {
		if(s!=null){
			try {
				ByteArrayOutputStream out=new ByteArrayOutputStream();
				s.write(out, obj);
				delegate.setEntity(new ByteArrayEntity(out.toByteArray()));
				String type = IO.contentType(s);
				if(type!=null){
					delegate.addHeader("Content-Type", type);
				}
			} catch (IOException e) {
				LoggerFactory.getLogger(this.getClass()).error(e.getMessage(),e);
			}
			
		}
		return new RequestImpl(delegate);
	}

	public Request serialize() {
		return serializeWith(IO.serializerAs(obj.getClass()));
	}

	@Override
	public Request serializeWith(Class<? extends Serializer> serializer) {
		return serializeWith(IO.serializer(serializer));
	}

}
