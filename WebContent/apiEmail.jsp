<%@page import="api.daoImpl.Smpp_DaoImpl"%>
<%@page import="com.beans.ApiEmail"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="container">
<%
	if(request.getParameter("message")!=null){
		String message=request.getParameter("message");
		String value="";
		String color="";
		if(message.equals("1")){
			value="Added Email Successfully";
			color="alert-success";
		}else if(message.equals("2")){
			value="Updated Email Successfully";
			color="alert-success";
		}else if(message.equals("3")){
			value="Deleted Email Successfully";
			color="alert-success";
		}else if(message.equals("4")){
			value="Give Access Email Successfully";
			color="alert-success";
		}else if(message.equals("5")){
			value="Deny Access Email Successfully";
			color="alert-success";
		}else if(message.equals("5")){
			value="Failed";
			color="alert-warning";
		}
		%>
			<div class="alert <%=color %>" id="myalert">
			  <strong><%=value %>!</strong>
			</div>
		<%
	}
%>

  <h2>Api Mail</h2> 

  	<button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#myModal" style="float: right;">Add Mail</button>
  
   <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Email Address</h4>
        </div>
          <form action="AddApiEmail" method="post">
        <div class="modal-body">
        
          	<input class="form-control" type="email" placeholder="Enter Email Address" name="email" required>
         
        </div>
        <div class="modal-footer">
         <button type="submit" class="btn btn-primary">Add</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
         </form>
      </div>
      
    </div>
  </div>
  <table class="table table-hover">
    <thead>
      <tr class="text-center">
        <th class="text-center">S NO</th>
        <th class="text-center">Email</th>
        <th class="text-center">Access</th>
        <th class="text-center">Action</th>
      </tr>
    </thead>
    <tbody>
    <%
    Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
    ArrayList<ApiEmail> apiEmails=daoImpl.getApiEmail();
    int i=0;
    for(ApiEmail apiEmail:apiEmails){
    	i++;
    	%>
    		 <tr class="text-center">
		        <td><%=i %></td>
		        <td><%=apiEmail.getEmail() %></td>
		        <td>
		        	<%if(apiEmail.getAccess()==0){
		        		%><i class="fa fa-circle" style="font-size:28px;color:red"></i><%
		        	}if(apiEmail.getAccess()==1){
		        		%><i class="fa fa-circle" style="font-size:28px;color:green"></i><%
		        	} %>
		        </td>
		        <td>
		        	<%if(apiEmail.getAccess()==0){
		        		%><a href="AccesApiEmail?id=<%=apiEmail.getId()%>&access=1" class="btn btn-success btn-sm">Give Access</a><%
		        	}if(apiEmail.getAccess()==1){
		        		%><a href="AccesApiEmail?id=<%=apiEmail.getId()%>&access=0" class="btn btn-danger btn-sm">Deny Access</a><%
		        	} %>
		        	<br>
		        	<a href="DeleteApiEmail?id=<%=apiEmail.getId()%>">Delete</a>
		        	<br>
		        	<button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#updateModal<%=apiEmail.getId() %>" >Update Mail</button>
		        </td>
		         <!-- Modal -->
  <div class="modal fade" id="updateModal<%=apiEmail.getId() %>" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Update Email Address</h4>
        </div>
          <form action="UpdateApiEmail" method="post">
        <div class="modal-body">
        	<input type="hidden" name="id" value="<%=apiEmail.getId()%>">
          	<input class="form-control" type="email" placeholder="Enter Email Address" name="email" value="<%=apiEmail.getEmail() %>" required>
         	<br>
         	<select class="form-control" name="access">
         		<option label="" disabled="disabled" >Choose Access</option>
         		<option  <%if(apiEmail.getAccess()==1){%>selected="selected"<%}  %> value="1">Give Access</option>
         		<option  <%if(apiEmail.getAccess()==0){%>selected="selected"<%}  %> value="0">Deny Access</option>
         	</select>
        </div>
        <div class="modal-footer">
         <button type="submit" class="btn btn-primary">Update</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
         </form>
      </div>
      
    </div>
  </div>
		      </tr>
    	<%
    }
    %>
     
     
    </tbody>
  </table>
</div>
<script type="text/javascript">
setTimeout(function() {
    $('#myalert').fadeOut('slow');
}, 2000);
</script>
</body>
</html>