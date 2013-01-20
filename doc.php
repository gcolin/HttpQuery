<!DOCTYPE html>
<html>

  <?php include 'header.php'; ?>

  <body>
     <?php include 'navbar.php'; ?>
   
    <div class="container-fluid">
      <div class="row-fluid">
        <?php include 'doc/menu.php'; 
        	
        	if(empty($_GET['page'])){
        		$page = "index";
        	}else{
        		$page = $_GET['page'];
        	}
        	$page = 'doc/'.$page.'.php';
        	include $page;
        ?>
        </div>
      </div>
  </body>
</html>
