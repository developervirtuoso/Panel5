<%@page import="java.time.LocalDate"%>
<%@page import="com.beans.ReportRecord"%>
<%@page import="com.beans.VFEmails"%>
<%@page import="com.beans.ApiEmail"%>
<%@page import="java.util.ArrayList"%>
<%@page import="api.daoImpl.Smpp_DaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Value First Emails</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
#test-btn {
    margin-right: 12px;
}
#send-btn
 {
    margin-right: 14px;
}

table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}
#verify_div{
    width: 129%;
}
tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
</head>
<body onload="fetchSaveAndData()">
<div class="w3-container">
  <div class="w3-bar w3-black">
    <a href="#" class="w3-bar-item w3-button w3-mobile w3-green">Home</a>
    <div class="w3-dropdown-hover w3-mobile">
      <button class="w3-button">Manage<i class="fa fa-caret-down"></i></button>
      <div class="w3-dropdown-content w3-bar-block w3-dark-grey">
        <a href="ValueFirstEmail.jsp" class="w3-bar-item w3-button w3-mobile">Emails</a>
       <!--   <a href="DailyRecord.jsp" class="w3-bar-item w3-button w3-mobile">Daily Sent Record</a>-->
      </div>
    </div>
  </div>
  
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
		}else if(message.equals("6")){
			value="Report Sent!!";
			color="alert-success";
		}else if(message.equals("7")){
			value="Report Sending Failed !!";
			color="alert-warning";
		}else if(message.equals("8")){
			value="Test Report Sent!!";
			color="alert-success";
		}else if(message.equals("9")){
			value="Test Report Sending Failed !!";
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
  	    <button type="button" id="verify"  class="btn btn-info btn-sm" data-toggle="modal" data-target="#myModal4" style="float: right;">Verify the count</button>
    	<button type="button" id="test-btn"  class="btn btn-info btn-sm" data-toggle="modal" data-target="#myModal2" style="float: right;">Test Sending report</button>
      	<button type="button" id="send-btn"  class="btn btn-info btn-sm" data-toggle="modal" data-target="#myModal3" style="float: right;">Send Report</button>
  
   <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Email Address</h4>
         
        </div>
          <form action="AddVFEmail" method="post">
        <div class="modal-body">
        
          	<input class="form-control" type="email" placeholder="Enter Email Address" name="email" required>
          
          Send to type? <select class="form-control" name="type">
          <option value="To" selected>Select an option</option>
          <option value="To">To</option>
       <!-- -   <option value="Bc">Bc</option>--> 
          <option value="Cc">Cc</option>
          </select>
        </div>
        <div class="modal-footer">
         <button type="submit" class="btn btn-primary">Add</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
         </form>
      </div>
      
    </div>
  </div>
  <!-- Modal 4-->
  <div class="modal fade" id="myModal4" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content" id="verify_div">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Verify</h4>
         
        </div>
          <form action="VerifyCount" method="post">
        <div class="modal-body">
        <table>
        <tr>
        <th>Username</th>
        <th>Total Sent</th>
        <th>Delivered</th>
        <th>Del %age</th>
        <th>Failed</th>
        <th>Failed %age</th>
        <th>Waiting</th>
        <th>Waiting %age</th>
        <th>Mtd</th>
        </tr>
        <%
        LocalDate todaydate = LocalDate.now();
        Smpp_DaoImpl smpp_DaoImpl=new Smpp_DaoImpl();
        ArrayList<ReportRecord> rr=smpp_DaoImpl.recordForTodayMail(todaydate.minusDays(1).toString());
        long sub_total=0;
        long del_total=0;
        long failed_total=0;
        long waiting_total=0;
        long mtd_total=0;
        for(int i=0;i<rr.size();i++){
        	%>
        	<tr>
        <td><%=rr.get(i).getAccount()%></td>
        <th><%=rr.get(i).getSub()%></th>
        <th><%=rr.get(i).getDel()%></th>
        <th><%=rr.get(i).getDel_per()%></th>
        <th><%=rr.get(i).getFailed()%></th>
        <th><%=rr.get(i).getFailed_per()%></th>
        <th><%=rr.get(i).getWaiting()%></th>
        <th><%=rr.get(i).getWaiting_per()%></th>
        <th><%=rr.get(i).getMtd()%></th>
        </tr>
        <%
        sub_total=sub_total+rr.get(i).getSub();
        del_total=del_total+rr.get(i).getDel();
        failed_total=failed_total+rr.get(i).getFailed();
        waiting_total=waiting_total+rr.get(i).getWaiting();
        mtd_total=mtd_total+rr.get(i).getMtd();
        }
        %>
        <tr>
        <td>Total</td>
        <th><%=sub_total%></th>
        <th><%=del_total%></th>
        <th><%=(del_total*100 / (sub_total))%></th>
        <th><%=failed_total%></th>
        <th><%=(failed_total*100 / (sub_total))%></th>
        <th><%=waiting_total%></th>
        <th><%=(waiting_total*100 / (sub_total))%></th>
        <th><%=mtd_total%></th>
        </tr>
        </table>
        </div>
        <div class="modal-footer">
         <button type="submit" class="btn btn-primary">Verify</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
         </form>
      </div>
      
    </div>
  </div>
  <!-- Modal2 -->
  <div class="modal fade" id="myModal2" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Test Report</h4>
        </div>
          <form action="TestVFReport" method="post">
        <div class="modal-body">
        
          	To:- <input class="form-control" type="text" placeholder="Enter Email Address" name="to_email" required>
          	<br>
          	Cc:-
         <input class="form-control" type="text" placeholder="Enter Email Address" name="cc_email" required>
        </div>
        <div class="modal-footer">
         <button type="submit" class="btn btn-primary">Add</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
         </form>
      </div>
      
    </div>
  </div>
    <!-- Modal 3 -->
  <div class="modal fade" id="myModal3" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Send Report</h4>
        </div>
          <form action="SendReport" method="post">
        <div class="modal-body">
        Are you sure?<br>
          <button type="submit" class="btn btn-primary">Yes</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
        </div>
        <div class="modal-footer">
       
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
        <th class="text-center">Send To Type</th>
        <th class="text-center">Action</th>
      </tr>
    </thead>
    <tbody>
    <%
    Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
    ArrayList<VFEmails> vfEmails=daoImpl.getVFEmails();
    int i=0;
    for(VFEmails vfEmail:vfEmails){
    	i++;
    	System.out.println(vfEmail.getType());
    	%>
    		 <tr class="text-center">
		        <td><%=i %></td>
		        <td><%=vfEmail.getEmail() %></td>
		        <td>
		        	<%if(vfEmail.getAccess()==0){
		        		%><i class="fa fa-circle" style="font-size:28px;color:red"></i><%
		        	}if(vfEmail.getAccess()==1){
		        		%><i class="fa fa-circle" style="font-size:28px;color:green"></i><%
		        	} %>
		        </td>
		        <td>
		        	<%=vfEmail.getType()%>
		        </td>
		        <td>
		        	<%if(vfEmail.getAccess()==0){
		        		%><a href="AccesApiEmail?id=<%=vfEmail.getId()%>&access=1" class="btn btn-success btn-sm">Give Access</a><%
		        	}if(vfEmail.getAccess()==1){
		        		%><a href="AccesVFEmail?id=<%=vfEmail.getId()%>&access=0" class="btn btn-danger btn-sm">Deny Access</a><%
		        	} %>
		        	<br>
		        	<a href="DeleteVFEmail?id=<%=vfEmail.getId()%>">Delete</a>
		        	<br>
		        	<button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#updateModal<%=vfEmail.getId() %>" >Update Mail</button>
		        </td>
		         <!-- Modal -->
  <div class="modal fade" id="updateModal<%=vfEmail.getId() %>" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Update Email Address</h4>
        </div>
          <form action="UpdateVFEmail" method="post">
        <div class="modal-body">
        	<input type="hidden" name="id" value="<%=vfEmail.getId()%>">
          	<input class="form-control" type="email" placeholder="Enter Email Address" name="email" value="<%=vfEmail.getEmail() %>" required>
         	<br>
         	<select class="form-control" name="to_type">
         		<option label="" disabled="disabled" >Choose To type</option>
         		<option  <%if(vfEmail.getType().equals("To")){%>selected="selected"<%}  %> value="To">To</option>
         		<option  <%if(vfEmail.getType().equals("Cc")){%>selected="selected"<%}  %> value="Cc">Cc</option>
         	</select><br>
         	<select class="form-control" name="access">
         		<option label="" disabled="disabled" >Choose Access</option>
         		<option  <%if(vfEmail.getAccess()==1){%>selected="selected"<%}  %> value="1">Give Access</option>
         		<option  <%if(vfEmail.getAccess()==0){%>selected="selected"<%}  %> value="0">Deny Access</option>
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
</body>
<script>
function fetchSaveAndData(){
	////alert("update working=="+chatid);
	//alert("updateDbByDefault==");
	 $(document).ready(function() {
	        
	        
	        $.ajax({
	        url: "updateDb.jsp",
	        type: "post",
	        data: {
	        success : function(data){
	    	alert("success");
	        }
	        }
	        });
	        
	        });	
}
</script>
</html>