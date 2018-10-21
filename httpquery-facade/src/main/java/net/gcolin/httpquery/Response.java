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
import java.util.Collection;
import java.util.Map.Entry;

public interface Response {

    <T> T as(Class<T> target);

    String asString();

    InputStream asStream();

    byte[] asBytes();

    String header(String key);

    Collection<String> headers(String key);

    Collection<Entry<String, String>> headers();

    void close();

    int status();

}