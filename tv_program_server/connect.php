<?php

   $host = "localhost";
   $username = "root";
   $password = "";
   $database = "wepokaor_tv_program_db";
   
   mysql_connect($host, $username, $password);
   mysql_select_db($database);

   mysql_query("SET time_zone='+6:00' ");
   mysql_query("SET CHARACTER SET utf8");
   mysql_query("SET SESSION collation_connection = 'utf8_general_ci' ") or die (mysql_error()); 

   // adjust date for demo data

   $sql = "SELECT DATEDIFF(NOW(), (SELECT MIN(date_time) FROM program_info)) AS days";
   $result = mysql_query($sql);
   $diff = mysql_fetch_array($result)['days'] - 1;

   $sql = "UPDATE program_info SET date_time = ADDDATE(date_time," . $diff . ")";
   mysql_query($sql);
?>