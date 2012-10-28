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
package org.httpclientquery.spi;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.httpclientquery.ContentType;
import org.httpclientquery.For;
import org.httpclientquery.Serializer;


@For(Object.class)
@ContentType("application/x-java-serialized-object")
public class ObjectSerializer implements Serializer{


	@Override
	public void write(OutputStream outStream, Object obj) throws IOException{
		ObjectOutputStream oo = new ObjectOutputStream(outStream);
		oo.writeObject(obj);
	}

}
