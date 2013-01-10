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

public interface HttpHandler {

	Request get(String uri);
	Request delete(String uri);
	RequestWithPayload put(String uri,Object obj);
	Request put(String uri,byte[] data);
	Request put(String uri,String str);
	Request put(String uri,InputStream inStream);
	RequestWithPayload post(String uri,Object obj);
	Request post(String uri,byte[] data);
	Request post(String uri,InputStream inStream);
	Request post(String uri,String str);
	Request trace(String uri);
	Request head(String uri);
	Request options(String uri);
}
