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
smpp_dao.getCookie(jsonObject);
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
			      <div class="panel-heading">User Delivery Summary
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
							      <form action="SetCookie" method="post">
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
			      <form action="" method="get">
			      <input type="hidden" value="fivepanelid" name="clr">
			      <input type="hidden" value="fivepanelid" name="act">
			      	<div class="row">
			      		<div class="col-sm-4">
			      			<div class="form-group">
							    <label for="username">Username:</label>
							   	<select class="form-control" id="username" name="username">
							   		<option value="0">Please select an option</option>
							   		<option value="22" <%if(username.equalsIgnoreCase("22")){%>selected="selected"<%} %> >valuefirst</option>
							   		<option value="23" <%if(username.equalsIgnoreCase("23")){%>selected="selected"<%} %>>gupshupildo</option>
							   		<option value="24" <%if(username.equalsIgnoreCase("24")){%>selected="selected"<%} %>>anuragtest</option>
							   		<option value="25" <%if(username.equalsIgnoreCase("25")){%>selected="selected"<%} %>>3Mildo</option>
							   		<option value="26" <%if(username.equalsIgnoreCase("26")){%>selected="selected"<%} %>>vfildo</option>
							   		<option value="27" <%if(username.equalsIgnoreCase("27")){%>selected="selected"<%} %>>vleaftr1</option>
							   		<option value="28" <%if(username.equalsIgnoreCase("28")){%>selected="selected"<%} %>>vleaftr2</option>
							   	</select>
							  </div>
			      		</div>
			      		<div class="col-sm-4">
			      			<div class="form-group">
							    <label for="username">From Date:</label>
							    <div class="input-group date form_date" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
				                    <input class="form-control" size="16" type="text" value="" name="fromdate" readonly >
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				                </div>
							</div>
						</div>
						<div class="col-sm-4">
			      			<div class="form-group">
							    <label for="username">To Date:</label>
							   <div class="input-group date to_date" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
				                    <input class="form-control" size="16" type="text" value="" name="todate" readonly >
				                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				                </div>
							</div>
						</div>
			      		<div class="col-sm-2">
			      			<div class="form-group">
							    <label for="fromdate">Search:</label>
			      				<input type="submit" class="btn btn-primary form-control">
							</div>
			      		</div>
			      		
			      		
			      	</div>
			      </form>
			      	<hr>
			      	
			      	<div class="row">
			      		<div class="col-sm-12">
			      		<%
			      		
			      		String fromdate=request.getParameter("fromdate");
			      		if(fromdate!=null){
			      			 if(fromdate.equalsIgnoreCase("null")) {
			      				fromdate=java.time.LocalDate.now().toString();
			      		 		 }
			      		  }else{
			      			fromdate=java.time.LocalDate.now().toString();
			      			  
			      		  }
			      		String todate=request.getParameter("todate");
			      		if(todate!=null){
			      			 if(todate.equalsIgnoreCase("null")) {
			      				todate=java.time.LocalDate.now().toString();
			      		 		 }
			      		  }else{
			      			todate=java.time.LocalDate.now().toString();
			      			  
			      		  }
			    		JavaPostRequest javaPostRequest=new JavaPostRequest();
			    		if(username.equalsIgnoreCase("0")){}else{
			    			String data=javaPostRequest.vnsApiData(username, fromdate, todate,cookiename);
			    		if(data.equalsIgnoreCase("")){
			    			
			    		}else{
			    		 Document html = Jsoup.parse(data);
			           
			             Element table = html.select("table").get(0); 
			             out.println( table);
			            
			    		}
			    		}
			      		%>
			      		
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
<script type="text/javascript">
	$('.form_date').datetimepicker({
		format: "yyyy-mm-dd",
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    }).datetimepicker('update', "<%=fromdate%>");
	$('.to_date').datetimepicker({
		format: "yyyy-mm-dd",
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    }).datetimepicker('update',"<%=todate%>");
	
</script>
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