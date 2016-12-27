<?php

   include 'connect.php';
   
   $response["data"] = array();
   $response["success"] = 0;
   
   $sql = "SELECT * FROM top_chart_info ORDER BY type ASC, rank ASC";
   
   $result = mysql_query($sql);
   
   if(!mysql_error()) 
   {
	   $response["success"] = 1;
	   
	   while($row = mysql_fetch_array($result))
	   {
		   $single_info = array();
		   $single_info["type"] = $row["type"];	
		   $single_info["rank"] = $row["rank"];
           $single_info["name"] = $row["name"];		   
		   array_push($response["data"],$single_info);
	   }
   }
   
   echo json_encode($response);
   
?>   