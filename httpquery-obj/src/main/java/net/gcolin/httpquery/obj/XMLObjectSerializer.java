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
package net.gcolin.httpquery.obj;

import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.OutputStream;

import net.gcolin.httpquery.ContentType;
import net.gcolin.httpquery.Serializer;

@ContentType("application/x-java-serialized-object+xml")
public class XMLObjectSerializer implements Serializer {

    @Override
    public void write(OutputStream outStream, Object obj) throws IOException {
        try (XMLEncoder encoder = new XMLEncoder(outStream)) {
            encoder.writeObject(obj);
        }
    }

}
