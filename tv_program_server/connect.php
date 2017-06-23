<?php

   $host = "localhost";
   $username = "root";
   $password = "";
   $database = "tv_program_db";
   
   @mysql_connect($host, $username, $password);

   if (!mysql_select_db($database)) {

      // create db and import tables
      
      mysql_query("CREATE DATABASE IF NOT EXISTS " . $database);
      mysql_select_db($database);

      $fp = file('tv_program_db.sql', FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
      $query = '';
      foreach ($fp as $line) {
         if ($line != '' && strpos($line, '--') === false) {
            $query .= $line;
            if (substr($query, -1) == ';') {
               mysql_query($query);
               $query = '';
            }
         }
      }
   }

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