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

<h2>Insert New Channel</h2>

<body>

<form name="insertChannelForm" method="post" enctype="multipart/form-data" action="sql_insert_channel.php">

<label>Channel Name:</label>
<input type="text" name="channelName" onblur="this.value=myTrim(this.value);" required/>

<br />

<label for="file">Select Image: <br/><i>Formate: PNG <br/>Background: Transperent <br/>Max Size: 250x250 px</i></label>

<input type="file" name="file" accept="image/png" required />

<br />
<br />

<input type="submit" value="Insert">

</form>

</body>

</html>