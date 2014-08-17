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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestWithPayloadImpl implements RequestWithPayload{

	private Object obj;
	private String method;
	private String uri;

	public RequestWithPayloadImpl(String method,String uri,
			Object obj) {
	    this.method = method;
        this.uri = uri;
		this.obj = obj;
	}

	public Request serializeWith(Serializer s) {
		if(s!=null){
			try {
				ByteArrayOutputStream out=new ByteArrayOutputStream();
				s.write(out, obj);
				RequestImpl r =  new RequestImpl(method, uri).data(out.toByteArray());
				String type = IO.contentType(s);
				if(type!=null){
					r.header("Content-Type", type);
				}
				return r;
			} catch (IOException e) {
			    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
			}
			
		}
		throw new IllegalArgumentException("serializer cannot be null");
	}

	public Request serialize() {
		return serializeWith(IO.serializerAs(obj.getClass()));
	}

	@Override
	public Request serializeWith(Class<? extends Serializer> serializer) {
		return serializeWith(IO.serializer(serializer));
	}

}
