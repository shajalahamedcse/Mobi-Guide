    <html>
	
    <head>
    <title>Mobile TV Guide</title>
	
	<style>
         label{display:inline-block;width:200px;margin-bottom:10px;}
    </style>

    </head>
	
    <body>
	
    <h1>Login</h1>
	
    <form name="login">
	
	<label>Username:</label>
    <input type="text" name="userid"/>
	<br/>
	
	<label>Password:</label>
    <input type="password" name="pswrd"/>
	<br/>
	<br/>
	
    <input type="button" onclick="check(this.form)" value="Login"/>
    </form>
	
    <script language="javascript">
    function check(form)
    {
     if(form.userid.value == "admin" && form.pswrd.value == "null")
      {
        window.open('data_entry/data_insert.php','_self')
      }
     else
     {
       alert("Error Password or Username")
     }
    }
    </script>
	
    </body>
    </html>

