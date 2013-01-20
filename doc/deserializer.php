<div class="span9">
<h1>Deserializer</h1>

<p>This interface unmarshall objects from bytes.</p>

<p>The list of deserializers :</p>
<ul>
	 <li><a href="doc.php?page=deserializerDOMSource">DOMSource</a></li>
     <li><a href="doc.php?page=deserializerJackson">Jackson</a></li>
     <li><a href="doc.php?page=deserializerJAXB">JAXB</a></li>
     <li><a href="doc.php?page=deserializerObject">Object</a></li>
     <li><a href="doc.php?page=deserializerSAXSource">SAXSource</a></li>
     <li><a href="doc.php?page=deserializerStAXSource">StAXSource</a></li>
     <li><a href="doc.php?page=deserializerStreamSource">StreamSource</a></li>
     <li><a href="doc.php?page=deserializerXMLObject">XMLObject</a></li>
     <li><a href="doc.php?page=deserializerXStream">XStream</a></li>
</ul>

<h2>Create a custom Deserializer</h2>

<p>Create a class which implements Deserializer. Then, in the same project add a service file "/myMavenProject/src/resources/META-INF/services/net.gcolin.httpquery.Deserializer" with the content :</p>

<pre>
com.mycompany.serializer.MyCustomDeserializer1
com.mycompany.serializer.MyCustomDeserializer2
</pre>

<p>To see an example, look at Jackson or XStream modules.</p>


</div>