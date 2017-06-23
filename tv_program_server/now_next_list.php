<?php
     
include 'connect.php';

$queryDate = $_GET['queryDate'];
     
$response["data"] = array();
$response["success"] = 0;
     	 	 
$sql = "CREATE TEMPORARY TABLE tb1 AS (SELECT * FROM program_info WHERE date_time <= '$queryDate')";

mysql_query($sql);

$sql = "CREATE TEMPORARY TABLE tb2 AS 
( SELECT a.* FROM program_info a JOIN 
( SELECT channel_id, MAX(date_time) as MaxTime FROM tb1 GROUP BY channel_id ) AS b 
ON a.channel_id = b.channel_id AND a.date_time = b.MaxTime)";

mysql_query($sql);

$sql = "CREATE TEMPORARY TABLE tb3 AS 
( SELECT program_id, program_name, tb2.channel_id, channel_name, tb2.category_id, category_name, 
DATE_FORMAT(date_time, '%b %d, %Y') as date, 
DATE_FORMAT(date_time, '%h:%i %p') as time,
DATE_FORMAT(date_time, '%W') as day FROM tb2 
JOIN channel_info a ON tb2.channel_id = a.channel_id 
JOIN category_info b ON tb2.category_id = b.category_id )";

mysql_query($sql);

$sql = "CREATE TEMPORARY TABLE tb4 AS (SELECT * FROM program_info WHERE date_time > '$queryDate')";

mysql_query($sql);

$sql = "CREATE TEMPORARY TABLE tb5 AS 
( SELECT a.* FROM program_info a JOIN 
( SELECT channel_id, MIN(date_time) as MinTime FROM tb4 GROUP BY channel_id ) AS b 
ON a.channel_id = b.channel_id AND a.date_time = b.MinTime)";

mysql_query($sql);

$sql = "CREATE TEMPORARY TABLE tb6 AS 
( SELECT program_id AS program_id_next, program_name AS program_name_next, channel_id, tb5.category_id AS category_id_next, category_name AS category_name_next, 
DATE_FORMAT(date_time, '%b %d, %Y') as date_next, 
DATE_FORMAT(date_time, '%h:%i %p') as time_next,
DATE_FORMAT(date_time, '%W') as day_next FROM tb5 
JOIN category_info a ON tb5.category_id = a.category_id )";

mysql_query($sql);

$sql = "SELECT * FROM tb3 JOIN tb6 ON tb3.channel_id = tb6.channel_id";
    
$result = mysql_query($sql);

mysql_query("DROP TABLE tb1");
mysql_query("DROP TABLE tb2");
mysql_query("DROP TABLE tb3");
mysql_query("DROP TABLE tb4");
mysql_query("DROP TABLE tb5");
mysql_query("DROP TABLE tb6");
     
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
			   
		$single_info["program_id_next"] = $row["program_id_next"];
    	$single_info["program_name_next"] = $row["program_name_next"];
        $single_info["category_id_next"]=$row["category_id_next"];				   
     	$single_info["category_name_next"]=$row["category_name_next"];	
    	$single_info["date_next"]=$row["date_next"];
    	$single_info["time_next"]=$row["time_next"];
    	$single_info["day_next"]=$row["day_next"];
			   
    	array_push($response["data"],$single_info);
    }
}
     
echo json_encode($response);
     
?>   