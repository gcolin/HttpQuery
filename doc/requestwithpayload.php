<div class="span9">
<h1>RequestWithPayload</h1>

<p>This is a request with a payload for setting a <a href="doc.php?page=serializer">Serializer</a></p>

<h2>serializeWith( serializerClass )</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: <a href="doc.php?page=request">Request</a></p>

<table class="table table-bordered table-striped">
      <tr>
        <th>Name</th>
        <th>Description</th>
      </tr>
      <tr>
      	<td>serializerClass</td>
      	<td>The class of a serializer. The serializer is a singleton.</td>
      </tr>
</table>

<pre>

Request request = requestWithPayload.serializeWith( JAXBSerializer.class );

</pre>

<h2>serializeWith( serializerInstance )</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: <a href="doc.php?page=request">Request</a></p>

<table class="table table-bordered table-striped">
      <tr>
        <th>Name</th>
        <th>Description</th>
      </tr>
      <tr>
      	<td>serializerInstance</td>
      	<td>An instance of a serializer</td>
      </tr>
</table>

<pre>

Request request = requestWithPayload.serializeWith( new XStreamSerializer() );

</pre>

<h2>serialize</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: <a href="doc.php?page=request">Request</a></p>

<p>The method try to find a good serializer.</p>

<pre>

Request request = requestWithPayload.serialize();

</pre>

</div>