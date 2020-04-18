<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<input tabindex="1" type="text" maxlength="15" name="sgc_username" id="sgc_username" class="form-control" placeholder="Your Username">
<iframe src="http://172.105.57.57/login" height="500" width="500" onload="loaded()" name="myframe" id="iFrameID">
	<script type="text/javascript">
	document.getElementById("sgc_username").value = "Johnny Bravo";
	</script>
</iframe>
</body>
<script>

function loaded() {
	document.getElementById('iFrameID').contentWindow.document.getElementById('sgc_username').value='hello';

}

	
	
	document.getElementById("sgc_username").value = "Johnny Bravo";
	//document.getElementById("sgc_username").value="sdkfhksdjf";
	//document.getElementById("sgc_password").value="sdkfhksdjf";
 // document.getElementById("sgc-btn-progress").click();

</script>
</html>