<div class="span9">
<h1>Header</h1>

<p>The method add a header.</p>

<h2>header( key, value )</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: <a href="doc.php?page=request">Request</a></p>

<table class="table table-bordered table-striped">
      <tr>
        <th>Name</th>
        <th>Description</th>
      </tr>
      <tr>
      	<td>key</td>
      	<td>The key of the header</td>
      </tr>
      <tr>
      	<td>value</td>
      	<td>The value of the header</td>
      </tr>
</table>

<pre>

Request request = Http.get("http://mysite.com").header("x-my-app-version","1.0");

</pre>

</div>