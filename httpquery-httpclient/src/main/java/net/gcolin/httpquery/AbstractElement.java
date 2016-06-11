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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public abstract class AbstractElement {

    protected <T> T callback(HttpClientDeserializer<T> c) {
        T out = null;
        HttpEntity entity = null;
        try {
            HttpResponse response = getResponse();
            entity = response.getEntity();
            out = c.call(entity, response);
        } catch (final Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    HttpHandlerImpl.ERROR_MESSAGE, e);
        } finally {
            if (c.closable()) {
                close(entity);
            }
        }
        return out;
    }

    protected abstract HttpResponse getResponse() throws IOException;

    protected void close(HttpEntity entity) {
        try {
            EntityUtils.consume(entity);
        } catch (final IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    HttpHandlerImpl.ERROR_MESSAGE, e);
        }
    }
}
