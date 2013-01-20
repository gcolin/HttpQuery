<div class="span9">
<h1>DeserializeWith</h1>

<p>This method specifies a <a href="doc.php?page=deserializer">Deserializer</a> for unmashalling the response with <a href="doc.php?page=request#as">as</a> method.</p>

<h2>deserializeWith( deserializerClass )</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: <a href="doc.php?page=request">Request</a></p>

<table class="table table-bordered table-striped">
      <tr>
        <th>Name</th>
        <th>Description</th>
      </tr>
      <tr>
      	<td>deserializerClass</td>
      	<td>The class of a deserializer. The deserializer is a singleton.</td>
      </tr>
</table>

<pre>

Request request = requestWithPayload.deserializeWith( JAXBSerializer.class );

</pre>

<h2>deserializeWith( deserializerInstance )</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: <a href="doc.php?page=request">Request</a></p>

<table class="table table-bordered table-striped">
      <tr>
        <th>Name</th>
        <th>Description</th>
      </tr>
      <tr>
      	<td>deserializerInstance</td>
      	<td>An instance of a deserializer</td>
      </tr>
</table>

<pre>

Request request = requestWithPayload.deserializeWith( new XStreamSerializer() );

</pre>

</div>