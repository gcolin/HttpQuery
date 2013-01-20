<div class="span9">
<h1>AuthBasic</h1>

<p>The method put basic credentials with the header "Authorization". This method of authentification is weak if it is not coupled with https.</p>

<h2>setAuthBasic( username, password )</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: <a href="doc.php?page=request">Request</a></p>

<table class="table table-bordered table-striped">
      <tr>
        <th>Name</th>
        <th>Description</th>
      </tr>
      <tr>
      	<td>username</td>
      	<td>The clear login</td>
      </tr>
       <tr>
      	<td>pasword</td>
      	<td>The clear password</td>
      </tr>
</table>

<pre>

Request request = Http.get("http://mysite.com").setAuthBasic("admin","admin");

</pre>

</div>