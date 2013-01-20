<div class="span9">
<h1>ContentType</h1>

<p>The method specifies the content-types of the request payload with the header "Content-Type". This method should be used only for <a href="doc.php?page=post">post</a> and <a href="doc.php?page=put">put</a> request.</p>

<h2>setContentType( type )</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: <a href="doc.php?page=request">Request</a></p>

<table class="table table-bordered table-striped">
      <tr>
        <th>Name</th>
        <th>Description</th>
      </tr>
      <tr>
      	<td>types</td>
      	<td>The content-type of the request</td>
      </tr>
</table>

<pre>

Request request = Http.post("http://mysite.com").setContentType("application/x-www-form-urlencoded");

</pre>

</div>