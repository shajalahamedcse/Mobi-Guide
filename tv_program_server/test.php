<html>

  <head>
    <title>Progam Info</title>
  </head>
  
  <body>
  
	<h2>Progam Info</h2>
  
    <table border="1">
    <tbody>

	<?php
		$response["data"] = array();
		$response["success"] = 0;
		include 'connect.php';

		$sql = "SELECT program_id, program_name, a.channel_id, channel_name, a.category_id, category_name, 
				DATE_FORMAT(date_time, '%b %d, %Y') as date, 
				DATE_FORMAT(date_time, '%h:%i %p') as time,
				DATE_FORMAT(date_time, '%W') as day FROM 
				program_info a JOIN channel_info b ON a.channel_id = b.channel_id 
				JOIN category_info c ON a.category_id = c.category_id";
		   
		$result = mysql_query($sql);

		if (!mysql_error())
		{
			$response["success"] = 1;
			while ($row = mysql_fetch_array($result))
			{
				array_push($response["data"], $row);
				$imgSrc = "channel_images/" . $row['channel_id'] . ".png";
		
				echo 
				'<tr>' . 
				'<td><img src = ' . $imgSrc . ' height=100 width=100></img></td>' . 
				'<td>' . $row['program_name'] . '</td>' . 
				'<td>' . $row['channel_name'] . '</td>' .
				'<td>' . $row['category_name'] . '</td>' .
				'<td>' . $row['date'] . '</td>' .
				'<td>' . $row['time'] . '</td>' .
				'</tr>';
			}
		}
	?>
	
    </tbody>
    </table>
	  
  </body>
</html>

