<div class="span9">
<h1>Serializer</h1>

<p>This interface writes objects to bytes.</p>

<p>The list of serializers :</p>
<ul>
	 <li><a href="doc.php?page=serializerJackson">Jackson</a></li>
     <li><a href="doc.php?page=serializerJAXB">JAXB</a></li>
     <li><a href="doc.php?page=serializerObject">Object</a></li>
     <li><a href="doc.php?page=serializerSource">Source</a></li>
     <li><a href="doc.php?page=serializerXMLObject">XMLObject</a></li>
     <li><a href="doc.php?page=serializerXStream">XStream</a></li>
</ul>

<h2>Create a custom Serializer</h2>

<p>Create a class which implements Serializer. Then, in the same project add a service file "/myMavenProject/src/resources/META-INF/services/net.gcolin.httpquery.Serializer" with the content :</p>

<pre>
com.mycompany.serializer.MyCustomSerializer1
com.mycompany.serializer.MyCustomSerializer2
</pre>

<p>To see an example, look at Jackson or XStream modules.</p>


</div>