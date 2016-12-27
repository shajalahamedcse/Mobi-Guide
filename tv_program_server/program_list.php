<?php

   include 'connect.php';
   
   $channel_id = $_GET['channel_id'];
   $category_id = $_GET['category_id'];
   $start_date = $_GET['start_date'];
   $end_date = $_GET['end_date'];
   
   $start_date = date_format( date_create_from_format('M d, Y', $start_date), 'Y-m-d');
   $end_date = date_format( date_create_from_format('M d, Y', $end_date), 'Y-m-d');
   
   $response["data"] = array();
   $response["success"] = 0;
   
   $sql = "SELECT program_id, program_name, a.channel_id, channel_name, a.category_id, category_name, 
           DATE_FORMAT(date_time, '%b %d, %Y') as date, 
           DATE_FORMAT(date_time, '%h:%i %p') as time,
           DATE_FORMAT(date_time, '%W') as day FROM 
           program_info a JOIN channel_info b ON a.channel_id = b.channel_id 
		   JOIN category_info c ON a.category_id = c.category_id
		   WHERE date_time between '$start_date' AND '$end_date'";
		   
   if($category_id != 'all') $sql = $sql . " AND c.category_id = $category_id";
   if($channel_id != 'all') $sql = $sql . " AND b.channel_id = $channel_id"; 
   
   $sql = $sql . " ORDER BY date_time ASC";

   $result = mysql_query($sql);
   
   if(!mysql_error()) 
   {
	   $response["success"] = 1;
	   
	   while($row = mysql_fetch_array($result))
	   {
		   $single_info = array();
		   $single_info["program_id"] = $row["program_id"];
		   $single_info["program_name"] = $row["program_name"];
		   $single_info["channel_id"] = $row["channel_id"];	
		   $single_info["channel_name"] = $row["channel_name"];	
		   $single_info["category_id"]=$row["category_id"];	
 		   $single_info["category_name"]=$row["category_name"];	
		   $single_info["date"]=$row["date"];
		   $single_info["time"]=$row["time"];
		   $single_info["day"]=$row["day"];
		   array_push($response["data"],$single_info);
	   }
   }
   
   echo json_encode($response);
   
?>   