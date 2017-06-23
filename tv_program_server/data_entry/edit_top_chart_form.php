<!DOCTYPE html>
<html>

<head>

<title>Insert Channel Info</title>

<style>
label{display:inline-block;width:200px;margin-bottom:10px;}
</style>

<script>
function myTrim(x) {
    return x.replace(/^\s+|\s+$/gm,'');
}
</script>

</head>

<h2>Edit Top Chart</h2>

<body>

<form name="editTopChartForm" method="post" enctype="multipart/form-data" action="sql_edit_top_chart.php">

<label>Type:</label>

<select name="type">
<option value="1">Holywood</option>
<option value="2">Bolywood</option>
</select>

<br/>

<label>Rank:</label>

<select name="rank">
<?php 
for ($i=1; $i<=10; $i++)
{
	echo "<option>" . $i . "</option>";
}
?>
</select>

<br />

<label>Movie Name:</label>
<input type="text" name="name" onblur="this.value=myTrim(this.value);" required/>
<br />

<label for="file">Select Image: <br/><i>Formate: PNG <br/>Background: Transperent <br/>Max Size: 250x250 px</i></label>

<input type="file" name="file" accept="image/png" required />

<br />
<br />

<input type="submit" value="Insert">

</form>

</body>

</html>