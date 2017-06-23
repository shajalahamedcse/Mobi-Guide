<?php

include '../connect.php';

$programName = $_POST['programName'];
$channelName = $_POST['channelName'];
$categoryName = $_POST['categoryName'];
$dateTime = $_POST['dateTime'];

$date = date_create_from_format('m/d/Y h:i a', $dateTime);
$dateFormated = date_format($date, 'Y-m-d H:i:s');

$sql = "INSERT INTO program_info (program_name, channel_id, category_id, date_time) VALUES(
'$programName',
(SELECT channel_id FROM channel_info WHERE channel_name = '$channelName'),
(SELECT category_id FROM category_info WHERE category_name = '$categoryName'),
'$dateFormated')";

mysql_query($sql);

$sql = "SELECT MAX(program_id) as max_id FROM program_info";
$result = mysql_query($sql);
  
while($row = mysql_fetch_array($result))
{
   $imageName = $row['max_id'] . ".png";
}

if(!mysql_error()) 
{
	move_uploaded_file($_FILES["file"]["tmp_name"],"../program_images/" . $imageName);
	echo "Data inserted\n";
}
else echo mysql_error();

?>