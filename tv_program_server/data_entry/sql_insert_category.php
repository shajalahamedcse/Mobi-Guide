<?php

include '../connect.php';

$categoryName = $_POST['categoryName'];

$sql = "INSERT INTO category_info (category_name) VALUES('$categoryName')";
mysql_query($sql);

$sql = "SELECT MAX(category_id) as max_id FROM category_info";
$result = mysql_query($sql);
  
while($row = mysql_fetch_array($result))
{
   $imageName = $row['max_id'] . ".png";
}

if(!mysql_error()) 
{
	move_uploaded_file($_FILES["file"]["tmp_name"],"../category_images/" . $imageName);
	echo "Data inserted\n";
}
else echo mysql_error();

?>