<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form id="searchForm" method="post" action="/search/initialSearch">         
  <fieldset class="searchFields">
  <input tabindex="1" type="text" maxlength="15" name="sgc_username" id="sgc_username" class="form-control" placeholder="Your Username">
    <input type="text" name="searchTerm" id="searchTerm" value="nnn"/>
    <input type="submit" value="Find stops"/>
  </fieldset>
</form>
</body>
</html>