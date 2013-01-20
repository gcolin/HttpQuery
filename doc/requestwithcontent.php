<div class="span9">
<h1><?php echo $name; ?></h1>

<p>The method create a <b><?php echo $name; ?></b> request with body.</p>

<br/>
<h2><?php echo $name; ?>( url, obj )</h2>

<p>since: <a href="doc.php?page=v1">1.0</a></p>
<p>Returns: <a href="doc.php?page=requestwithpayload">RequestWithPayload</a></p>

<table class="table table-bordered table-striped">
      <tr>
        <th>Name</th>
        <th>Description</th>
      </tr>
      <tr>
      	<td>url</td>
      	<td>The url of the request</td>
      </tr>
      <tr>
      	<td>obj</td>
      	<td>A POJO</td>
      </tr>
</table>

<pre>

RequestWithPayload request = Http.<?php echo $name; ?>("http://mysite.com", pojo);

</pre>

<br/>
<h2><?php echo $name; ?>( url, data )</h2>

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
      <tr>
      	<td>data</td>
      	<td>a byte array</td>
      </tr>
</table>

<pre>

Request request = Http.<?php echo $name; ?>("http://mysite.com", bytes);

</pre>

<br/>
<h2><?php echo $name; ?>( url, str )</h2>

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
      <tr>
      	<td>str</td>
      	<td>A string</td>
      </tr>
</table>

<pre>

Request request = Http.<?php echo $name; ?>("http://mysite.com", string);

</pre>

<br/>
<h2><?php echo $name; ?>( url, inStream )</h2>

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
      <tr>
      	<td>inStream</td>
      	<td>An inputStream</td>
      </tr>
</table>

<pre>

Request request = Http.<?php echo $name; ?>("http://mysite.com", string);

</pre>

</div>