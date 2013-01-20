
<h2 id="as">as( targetClass&lt;T&gt; )</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: T</p>

<table class="table table-bordered table-striped">
      <tr>
        <th>Name</th>
        <th>Description</th>
      </tr>
      <tr>
      	<td>targetClass&lt;T&gt;</td>
      	<td>The class of the returned object</td>
      </tr>
</table>

<pre>

Rss rss = Http.get("http://mysite.com")<?php echo $option; ?>.as( Rss.class );

</pre>

<h2 id="asBytes">asBytes()</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: byte[]</p>

<pre>

byte[] data = Http.get("http://mysite.com")<?php echo $option; ?>.asBytes();

</pre>

<h2 id="asResponse">asResponse()</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: <a href="doc.php?page=response">Response</a></p>

<pre>

Response response = Http.get("http://mysite.com")<?php echo $option; ?>.asResponse();

</pre>

<h2 id="asStream">asStream()</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: InputStream</p>

<pre>

InputStream inStream = Http.get("http://mysite.com")<?php echo $option; ?>.asStream();

</pre>

<h2 id="asString">asString()</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: String</p>

<pre>

String content = Http.get("http://mysite.com")<?php echo $option; ?>.asString();

</pre>