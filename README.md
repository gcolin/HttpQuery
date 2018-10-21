## Features

* Http request in a single line
* Internally stream are always closed at the end of the request
* Get the response in POJO with JAXB, Xstream or Jackson

See the project httpquery-examples

    LOG.info(Http.get("http://localhost:8880/index.html").asString());
    
    MyPoJo pojo = Http.post(LOGIN_URL,"login=admin&password=admin").as(MyPoJo.class);

### Build


    mvn install
    
### Serializers

| Name | Location  | Content Type  | Accepted Java Types |
|------|-----------|---|---|
| net.gcolin.httpquery.spi.ByteSerializer  | httpquery-facade  |   | byte[]  |
| net.gcolin.httpquery.gson.GsonSerializer   | httpquery-gson  | application/json  | Object  |
| net.gcolin.httpquery.spi.InputStreamSerializer|httpquery-facade   |   | java.io.InputStream  |
| net.gcolin.httpquery.json.JacksonSerializer   | httpquery-jackson | application/json  | Object  |
| net.gcolin.httpquery.jaxb.JAXBPrettySerializer   | httpquery-jaxb  |   | Object  |
| net.gcolin.httpquery.jaxb.JAXBSerializer   | httpquery-jaxb  | application/xml  | Object  |
| net.gcolin.httpquery.obj.ObjectSerializer   | httpquery-obj  | application/x-java-serialized-object  | Object    |
| net.gcolin.httpquery.xml.SourceSerializer   | httpquery-xml  | application/xml  |  DOMSource, SAXSource, StAXSource, StreamSource |
| net.gcolin.httpquery.spi.StringSerializer   | httpquery-jaxb  |   | String  |
| net.gcolin.httpquery.obj.XMLObjectSerializer   | httpquery-obj |  application/x-java-serialized-object+xml | Object  |
| net.gcolin.httpquery.xstream.XStreamJsonSerializer   | httpquery-xstream |  application/json | Object  |
| net.gcolin.httpquery.xstream.XStreamSerializer   | httpquery-xstream |  application/xml | Object  |

### Deserializers

| Name | Location  | Accpet Type  | Accepted Java Types |
|------|-----------|---|---|
| net.gcolin.httpquery.xml.DOMSourceDeserializer  | httpquery-xml  | application/xml,text/xml,xml  | DOMSource  |
| net.gcolin.httpquery.gson.GsonDeserializer   | httpquery-gson  | application/json  | Object  |
| net.gcolin.httpquery.json.JacksonDeserializer|httpquery-jackson   | application/json  | Object  |
| net.gcolin.httpquery.jaxb.JAXBDeserializer   | httpquery-jaxb | application/xml  | Object  |
| net.gcolin.httpquery.obj.ObjectDeserializer   | httpquery-obj  | application/x-java-serialized-object  | Object    |
| net.gcolin.httpquery.xml.SAXSourceDeserializer  | httpquery-xml  | application/xml,text/xml,xml  | SAXSource  |
| net.gcolin.httpquery.xml.StAXSourceDeserializer  | httpquery-xml  | application/xml,text/xml,xml  | StAXSource |
| net.gcolin.httpquery.xml.StreamSourceDeserializer | httpquery-xml  | application/xml,text/xml,xml  | StreamSource |
| net.gcolin.httpquery.obj.XMLObjectDeserializer | httpquery-obj |  application/x-java-serialized-object+xml | Object  |
| net.gcolin.httpquery.xstream.XStreamDeserializer | httpquery-xstream |  application/xml | Object  |
| net.gcolin.httpquery.xstream.XStreamJsonDeserializer | httpquery-xstream |  application/json | Object  |