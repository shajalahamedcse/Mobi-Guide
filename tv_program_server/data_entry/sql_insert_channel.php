<?php

include '../connect.php';

$channelName = $_POST['channelName'];

$sql = "INSERT INTO channel_info (channel_name) VALUES('$channelName')";
mysql_query($sql);

$sql = "SELECT MAX(channel_id) as max_id FROM channel_info";
$result = mysql_query($sql);
  
while($row = mysql_fetch_array($result))
{
   $imageName = $row['max_id'] . ".png";
}

if(!mysql_error()) 
{
	move_uploaded_file($_FILES["file"]["tmp_name"],"../channel_images/" . $imageName);
	echo "Data inserted\n";
}
else echo mysql_error();

?>