<!DOCTYPE html>
<html>

<head>

<title>Insert Program Info</title>

<style>
label{display:inline-block;width:200px;margin-bottom:10px;}
</style>

<script>
function myTrim(x) {
    return x.replace(/^\s+|\s+$/gm,'');
}
</script>

<link rel="stylesheet" media="all" type="text/css" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" media="all" type="text/css" href="date_time_picker/jquery-ui-timepicker-addon.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.min.js"></script>
<script type="text/javascript" src="date_time_picker/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="date_time_picker/jquery-ui-sliderAccess.js"></script>
<script type="text/javascript" src="date_time_picker/script.js"></script>

</head>

<h2>Insert New Program</h2>

<body>

<form name="programInsertForm" method="post" enctype="multipart/form-data" action="sql_insert_program.php">

<label>Program Name:</label>
<input type="text" name="programName" onblur="this.value=myTrim(this.value);" required/>
<br />

<label>Channel Name:</label>

<select name="channelName">
<?php 
include '../connect.php';
$sql = "SELECT * FROM channel_info ORDER BY channel_id ASC";
$result = mysql_query($sql);  
while($row = mysql_fetch_array($result))
{
  echo "<option>" . $row['channel_name'] . "</option>";
}
?>
</select>

<br />

<label>Category Name:</label>

<select name="categoryName">
<?php 
include '../connect.php';
$sql = "SELECT * FROM category_info ORDER BY category_id ASC";
$result = mysql_query($sql);  
while($row = mysql_fetch_array($result))
{
  echo "<option>" . $row['category_name'] . "</option>";
}
?>
</select>

<br />

<label>Date:</label>
		 
<input type="text" id="basic_example_1" name="dateTime" required/>

<br />

<label for="file">Select Image: <br/><i>Formate: PNG <br/>Background: Transperent <br/>Max Size: 250x250 px</i></label>

<input type="file" name="file" accept="image/png" required />

<br />
<br />

<input type="submit" value="Insert">

</form>

</body>

</html>