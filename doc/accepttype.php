<div class="span9">
<h1>AcceptType</h1>

<p>The method specifies the content-types that the server can reply with the header "Accept".</p>

<h2>setAcceptType( types )</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: <a href="doc.php?page=request">Request</a></p>

<table class="table table-bordered table-striped">
      <tr>
        <th>Name</th>
        <th>Description</th>
      </tr>
      <tr>
      	<td>types</td>
      	<td>The content-types list that are acceptable</td>
      </tr>
</table>

<pre>

Request request = Http.get("http://mysite.com").setAcceptType("text/html");

</pre>

</div>