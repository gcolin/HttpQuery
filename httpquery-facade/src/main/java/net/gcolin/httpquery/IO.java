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

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.StringTokenizer;

public final class IO {

    private static Charset charset = Charset.forName("UTF8");

    private static Map<Class<?>, Serializer> serializers = new HashMap<Class<?>, Serializer>();
    private static Map<String, Map<Class<?>, Deserializer>> deserializers = new HashMap<String, Map<Class<?>, Deserializer>>();
    private static Map<Class<? extends Deserializer>, Deserializer> deserializersInstances = new HashMap<Class<? extends Deserializer>, Deserializer>();
    private static Map<Class<? extends Serializer>, Serializer> serializersInstances = new HashMap<Class<? extends Serializer>, Serializer>();

    private static Serializer defaultSerializer;

    static {
        loadDeserializers();

        loadSerializers();
    }
    
    private IO() {
    }

    private static void loadSerializers() {
        for (Serializer s : ServiceLoader.load(Serializer.class)) {
            serializersInstances.put(s.getClass(), s);
            For f = s.getClass().getAnnotation(For.class);
            if (f != null) {
                addSerializer0(s, f);
            }
        }
    }

    private static void addSerializer0(Serializer s, For f) {
        for (Class<?> c : f.value()) {
            if (c == Object.class) {
                defaultSerializer = s;
            } else {
                serializers.put(c, s);
            }
        }
    }

    private static void loadDeserializers() {
        for (Deserializer s : ServiceLoader.load(Deserializer.class)) {
            deserializersInstances.put(s.getClass(), s);
            For f = s.getClass().getAnnotation(For.class);
            Accept a = s.getClass().getAnnotation(Accept.class);
            if (f != null && a != null) {
                addDeserializer0(s, f, a);
            }
        }
    }

    private static void addDeserializer0(Deserializer s, For f, Accept a) {
        StringTokenizer str = new StringTokenizer(a.value(), ",");
        while (str.hasMoreElements()) {
            String t = str.nextToken();
            Map<Class<?>, Deserializer> map = deserializers.get(t);
            if (map == null) {
                map = new HashMap<Class<?>, Deserializer>();
                deserializers.put(t, map);
            }
            for (Class<?> c : f.value()) {
                if (c == Object.class) {
                    map.put(null, s);
                } else {
                    map.put(c, s);
                }
            }
        }
    }

    public static Serializer serializerAs(Class<?> clazz) {
        Serializer s = serializers.get(clazz);
        if (s == null) {
            s = defaultSerializer;
        }
        return s;
    }

    public static Deserializer deserializer(String contentType, Class<?> clazz) {
        Map<Class<?>, Deserializer> map = deserializers
                .get(formatContentType(contentType));
        if (map == null) {
            return null;
        }
        Deserializer s = map.get(clazz);
        if (s == null) {
            s = map.get(null);
        }
        return s;
    }

    private static String formatContentType(String contentType) {
        int index = contentType.indexOf(';');
        if (index == -1) {
            return contentType;
        } else {
            return contentType.substring(0, index).trim();
        }
    }

    public static Deserializer deserializer(Class<? extends Deserializer> clazz) {
        return deserializersInstances.get(clazz);
    }

    public static Serializer serializer(Class<? extends Serializer> clazz) {
        return serializersInstances.get(clazz);
    }

    public static String contentType(Object o) {
        ContentType ct = o.getClass().getAnnotation(ContentType.class);
        if (ct != null) {
            return ct.value();
        } else {
            return null;
        }
    }

    public static String accept(Object o) {
        Accept ct = o.getClass().getAnnotation(Accept.class);
        if (ct != null) {
            return ct.value();
        } else {
            return "*/*";
        }
    }

    public static void setCharset(Charset charset) {
        IO.charset = charset;
    }

    public static Charset getCharset() {
        return charset;
    }
}
