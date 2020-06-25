<%@page import="org.jsoup.select.Elements"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.jsoup.nodes.Element"%>
<%@page import="org.jsoup.Jsoup"%>
<%@page import="org.jsoup.nodes.Document"%>
<%@page import="com.dao.JavaPostRequest"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="org.json.JSONArray"%>
<%@page import="java.util.TimeZone"%>
<%@page import="api.daoImpl.Smpp_DaoImpl"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">


</head>
<body>

<%!static int totalResult=0; %>

<% 
Smpp_DaoImpl smpp_dao = new Smpp_DaoImpl();
JSONObject jsonObject=new JSONObject();
smpp_dao.getAdminCookie(jsonObject);
SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd"); 
Date d1 = sdfo.parse(java.time.LocalDate.now().toString()); 
Date d2 = sdfo.parse(jsonObject.getString("datetime")); 
String cookiename=jsonObject.getString("name");
if (d1.compareTo(d2) == 0) { 
	   
}else{
	%>
		 <div class="alert alert-danger alert-dismissible fade in">
		    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		    <strong>Cookie!</strong> Please update cookie.
		  </div>
	<%
}
String username=request.getParameter("username");
if(username!=null){
	 if(username.equalsIgnoreCase("null")) {
		 username="0";
 		 }
  }else{
	  username="0";
	  
  }


%> 
<div id="wrapper">


        <!-- Navigation -->
        

        <div id="page-wrapper" class="container-fluid" style="margin: 30px;">
           <div class="row">
           <div class="panel-group">
				<div class="panel panel-primary">
			      <div class="panel-heading">Push Smsc Connect
			      	<button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#myModal" style="float: right; margin: -5px;">set Cookie</button>
							<!-- Modal -->
							<div id="myModal" class="modal fade" role="dialog">
							  <div class="modal-dialog">
							
							    <!-- Modal content-->
							    <div class="modal-content">
							      <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal">&times;</button>
							        <h4 class="modal-title" style="color: black;">Set Cookie</h4>
							      </div>
							      <form action="SetAdminCookie" method="post">
								      <div class="modal-body">
								        <div class="form-group">
								        	<input type="text" name="cookie" value="" class="form-control">
								        </div>
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								        <input type="submit" class="btn btn-primary">
								      </div>
							      </form>
							    </div>
							
							  </div>
							</div>
			      </div>
			      <div class="panel-body">
			      	<div class="row">
			      		<div class="col-sm-12">
			      			<table class="table table-hover">
							    <thead>
							      <tr>
							        <th>Gate Way</th>
							        <th>Name</th>
							        <th>Host</th>
							        <th>Account</th>
							        <th>Tps</th>
							        <th>Type</th>
							        <th>Internal</th>
							        <th>Kannel</th>
							        <th>Session</th>
							      </tr>
							    </thead>
							    <tbody>
							    <%
							    JavaPostRequest javaPostRequest=new JavaPostRequest();
							    String data=javaPostRequest.pushSmppConnectApiData(cookiename);
							   if(!data.equalsIgnoreCase("")){
							    JSONObject pushobj=new JSONObject(data);
							    if(pushobj.has("data")){
							    	
							    
							    JSONArray jsonArray=pushobj.getJSONArray("data");
							    for(int i=0; i<jsonArray.length();i++ ){
							    	JSONObject jsondata=jsonArray.getJSONObject(i);
							    	//if(jsondata.getString("name").equalsIgnoreCase("camp2")){
							    %>
							      <tr>
							        <td><%=jsondata.getString("id") %></td>
							        <td><%=jsondata.getString("name") %></td>
							        <td><%=jsondata.getString("host") %></td>
							        <td><%=jsondata.getString("user") %></td>
							        <td><%=jsondata.getString("tps") %></td>
							        <td><%=jsondata.getString("type") %></td>
							        <td><%=jsondata.getInt("internalQ") %></td>
							        <td><%=jsondata.getInt("kannelQ") %></td>
							        <td><%=jsondata.getString("sessions") %></td>
							      </tr>
							      <%
							    	//}
							    }
							    }
							    } %>
							    </tbody>
							  </table>
			      		</div>
			      	</div>
			      </div>
			    </div>
			
			  </div>
						
		
		

		
	</div>
			 
		</div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		
  
      <script type="text/javascript">
    $(document).ready(function() {
        $(".dropdown-toggle").dropdown();
    });
    </script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
		
   
    
   <script type="text/javascript" src="JS/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="JS/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>

<script>
	    $(document).ready(function(){
	        $('.currentYear').click(function(){ 
	        	var e = document.getElementById("syaer");
	        	var strUser = e.options[e.selectedIndex].text;
	            var oldUrl = $(this).attr("href"); // Get current url
	            var newUrl = oldUrl.replace("currentYear", strUser); // Create new url
	            //panel?requested_date=currentYear-01&clr=languagesList2&act=languagesList2
	            $(this).attr("href", newUrl); // Set herf value
	        });
	        
	    });
	</script>
</body>
</html>