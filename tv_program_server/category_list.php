<?php

   include 'connect.php';
   
   $response["data"] = array();
   $response["success"] = 0;
   
   $sql = "SELECT * FROM category_info ORDER BY category_id ASC";
   
   $result = mysql_query($sql);
   
   if(!mysql_error()) 
   {
	   $response["success"] = 1;
	   
	   while($row = mysql_fetch_array($result))
	   {
		   $single_info = array();
		   $single_info["category_id"] = $row["category_id"];	
		   $single_info["category_name"] = $row["category_name"];	
		   array_push($response["data"],$single_info);
	   }
   }
   
   echo json_encode($response);
   
?>   