<div class="span9">
<h1>Response</h1>
<p>This object contains the response of the request. It can be usefull for getting response headers.</p>

<br/>

<?php 
$option = '.asResponse()';
include 'abstractelement.php'; ?>


<h2>close()</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: void</p>

<p>Release the connection.</p>

<pre>

Http.get("http://mysite.com").asResponse().close();

</pre>


<h2>header( key )</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: String</p>

<p>Get an header.</p>

<table class="table table-bordered table-striped">
      <tr>
        <th>Name</th>
        <th>Description</th>
      </tr>
      <tr>
      	<td>key</td>
      	<td>The key of the searched header.</td>
      </tr>
</table>

<pre>

String server = Http.get("http://mysite.com").asResponse().header("Server");

</pre>

<h2>headers( key )</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: Collection&lt;String&gt;</p>

<p>Get all headers.</p>

<table class="table table-bordered table-striped">
      <tr>
        <th>Name</th>
        <th>Description</th>
      </tr>
      <tr>
      	<td>key</td>
      	<td>The key of the searched headers.</td>
      </tr>
</table>

<pre>

Collection&lt;String&gt; cookies = Http.get("http://mysite.com").asResponse().headers("Cookie");

</pre>

<h2>headers()</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: Collection&lt;Entry&lt;String,String&gt;&gt;</p>

<p>Get all headers.</p>

<pre>

Collection&lt;Entry&lt;String,String&gt;&gt; headers = Http.get("http://mysite.com").asResponse().headers();

</pre>

<h2>status()</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: int</p>

<p>Get the status of the response.</p>

<pre>

int status = Http.get("http://mysite.com").asResponse().status();

</pre>


</div>