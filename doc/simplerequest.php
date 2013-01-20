<div class="span9">
<h1><?php echo $name; ?></h1>


<p>The method create a <b><?php echo $name; ?></b> request. A request with body.</p>

<h2><?php echo $name; ?>( url )</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: <a href="doc.php?page=request">Request</a></p>

<table class="table table-bordered table-striped">
      <tr>
        <th>Name</th>
        <th>Description</th>
      </tr>
      <tr>
      	<td>url</td>
      	<td>The url of the request</td>
      </tr>
</table>

<pre>

Request request = Http.<?php echo $name; ?>("http://mysite.com");

</pre>

</div>