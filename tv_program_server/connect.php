<?php

   $host = "localhost";
   $username = "wepokaor_user";
   $password = "admin.wepoka.2015";
   $database = "wepokaor_tv_program_db";
   
   mysql_connect($host, $username, $password);
   mysql_select_db($database);

   mysql_query("SET time_zone='+6:00' ");
   mysql_query("SET CHARACTER SET utf8");
   mysql_query("SET SESSION collation_connection = 'utf8_general_ci' ") or die (mysql_error()); 
   
?>