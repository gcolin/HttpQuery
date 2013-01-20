<!DOCTYPE html>
<html>

  <?php include 'header.php'; ?>

  <body>
     <?php include 'navbar.php'; ?>
    <div class="container">
	    <div class="hero-unit">
	        <h1>HttpQuery</h1>
	        <p></p>
	        <p>This java library makes Http request simplier. It uses Apache HttpClient. And it can be extended.</p>
	        <p><a href="doc.php" class="btn btn-primary btn-large">Learn more &raquo;</a></p>
	     </div>
     
     	<div class="row">
        <div class="span4">
          <h2>Why using HttpQuery?</h2>
          <p>You need to use a httpClient and you are lazy writing lots of lines. HttpQuery manage the connection releasing and deserialize the request content.</p>
          <p><a class="btn" href="doc.php">View details &raquo;</a></p>
        </div>
        <div class="span8">
          <h2>Maven</h2>
<?php include 'doc/maven.php'; ?>
          <p><a class="btn" href="doc.php">View details &raquo;</a></p>
       </div>
        
      </div>
      <div class="row">
        <div class="span6">
        	<h2>Authors and Contributors</h2>
        		<p>Gaël Colin (<a href="https://github.com/gcolin" class="user-mention">@gcolin</a>)</p>
        </div>
        <div class="span6">
        	<h2>Support or Contact</h2>
        	<p>You can send me mail for any suggestion.</p>
        	<p class="copyright">Httpquery maintained by <a href="https://github.com/gcolin">gcolin</a></p>
        </div>
       </div>
     
    </div>
  </body>
</html>
