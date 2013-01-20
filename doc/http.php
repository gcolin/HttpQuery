<div class="span9">
<h1>Http</h1>
<p>This class is the facade for creating Http requests.</p>
<br/>
<p>The implementations : </p>
<ul>
	<li>Apache HttpClient</li>
	<li>Mock for the test</li>
</ul>
<br/>
<h2>HttpClient</h2>

<p>In order to use HttpClient, just put this librairy in the classpath.</p>
<?php include 'maven/httpclient.php'; ?>

<br/>
<h2>Mock</h2>

<p>In order to use Mock, put this librairy in the classpath.</p>
<?php include 'maven/mock.php'; ?>

<p>Active it with</p>
<pre>
MockHttpHandler.bind(true);
</pre>

<p>Disactivate it with</p>
<pre>
MockHttpHandler.bind(false);
</pre>
<p></p>
</div>