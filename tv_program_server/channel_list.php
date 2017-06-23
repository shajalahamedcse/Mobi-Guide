<?php

   include 'connect.php';
   
   $response["data"] = array();
   $response["success"] = 0;
   
   $sql = "SELECT * FROM channel_info ORDER BY channel_id ASC";
   
   $result = mysql_query($sql);
   
   if(!mysql_error()) 
   {
	   $response["success"] = 1;
	   
	   while($row = mysql_fetch_array($result))
	   {
		   $single_info = array();
		   $single_info["channel_id"] = $row["channel_id"];	
		   $single_info["channel_name"] = $row["channel_name"];	
		   array_push($response["data"],$single_info);
	   }
   }
   
   echo json_encode($response);
   
?>   