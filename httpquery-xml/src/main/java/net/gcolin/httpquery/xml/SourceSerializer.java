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
package net.gcolin.httpquery.xml;

import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import net.gcolin.httpquery.ContentType;
import net.gcolin.httpquery.For;
import net.gcolin.httpquery.Serializer;

@For({ DOMSource.class, SAXSource.class, StAXSource.class, StreamSource.class })
@ContentType("application/xml")
public class SourceSerializer implements Serializer {

    private TransformerFactory tf = TransformerFactory.newInstance();

    @Override
    public void write(OutputStream outStream, Object obj) {
        StreamResult sr = new StreamResult(outStream);
        try {
            tf.newTransformer().transform((Source) obj, sr);
        } catch (TransformerConfigurationException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    e.getMessage(), e);
        } catch (TransformerException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    e.getMessage(), e);
        }
    }
}
