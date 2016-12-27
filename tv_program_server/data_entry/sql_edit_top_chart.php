<?php

include '../connect.php';

$name = $_POST['name'];
$type = $_POST['type'];
$rank = $_POST['rank'];

$sql = "DELETE FROM top_chart_info WHERE type = $type AND rank = $rank";
mysql_query($sql);

$sql = "INSERT INTO top_chart_info VALUES($type, $rank, '$name')";
mysql_query($sql);

$path = "../top_chart_" . $type . "/" . $rank . ".png";

if(!mysql_error()) 
{
	move_uploaded_file($_FILES["file"]["tmp_name"], $path);
	echo "Data inserted\n";
}
else echo mysql_error();

?>