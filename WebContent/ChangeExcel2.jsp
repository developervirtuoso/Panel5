<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="api.daoImpl.Smpp_DaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://res.cloudinary.com/dxfq3iotg/raw/upload/v1569818907/jquery.table2excel.min.js"></script>
<style type="text/css">
	 body {
     background-color: #f9f9fa
 }

 .flex {
     -webkit-box-flex: 1;
     -ms-flex: 1 1 auto;
     flex: 1 1 auto
 }

 @media (max-width:991.98px) {
     .padding {
         padding: 1.5rem
     }
 }

 @media (max-width:767.98px) {
     .padding {
         padding: 1rem
     }
 }

 .padding {
     padding: 5rem
 }

 .card {
     box-shadow: none;
     -webkit-box-shadow: none;
     -moz-box-shadow: none;
     -ms-box-shadow: none
 }

 .pl-3,
 .px-3 {
     padding-left: 1rem !important
 }

 .card {
     position: relative;
     display: flex;
     flex-direction: column;
     min-width: 0;
     word-wrap: break-word;
     background-color: #fff;
     background-clip: border-box;
     border: 1px solid #d2d2dc;
     border-radius: 0
 }

 .card .card-title {
     color: #000000;
     margin-bottom: 0.625rem;
     text-transform: capitalize;
     font-size: 0.875rem;
     font-weight: 500
 }

 .card .card-description {
     margin-bottom: .875rem;
     font-weight: 400;
     color: #76838f
 }

 p {
     font-size: 0.875rem;
     margin-bottom: .5rem;
     line-height: .2rem
 }

 .table-responsive {
     display: block;
     width: 100%;
     overflow-x: auto;
     -webkit-overflow-scrolling: touch;
     -ms-overflow-style: -ms-autohiding-scrollbar
 }

 .table,
 .jsgrid .jsgrid-table {
     width: 100%;
     max-width: 100%;
     margin-bottom: 1rem;
     background-color: transparent
 }

 .table thead th,
 .jsgrid .jsgrid-table thead th {
     border-top: 0;
     border-bottom-width: 1px;
     font-weight: 500;
     font-size: .875rem;
     text-transform: uppercase
 }

 .table td,
 .jsgrid .jsgrid-table td {
     font-size: 0.875rem;
     padding: .875rem 0.9375rem
 }

 .badge {
     border-radius: 0;
     font-size: 12px;
     line-height: 1;
     padding: .375rem .5625rem;
     font-weight: normal
 }

 .btn {
     border-radius: 0
 }
</style>
</head>
<body>
	
<div class="page-content page-container" id="page-content">
    <div class="padding">
        <div class="row container d-flex justify-content-center">
            <div class="col-lg-8 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-8">
                               <form action="uploadFile.jsp" method="post" enctype="multipart/form-data">
                               	<div class="input-group mb-3">
                               	 <input type="hidden" name="pageurl"  value="ChangeExcel2"  class="form-control" >
								  <input type="file" name="file" class="form-control" >
								  <div class="input-group-append">
								    <button class="btn btn-success" type="submit">Submit</button>
								  </div>
								</div>
                               </form>
                            </div>
                            <div class="col-md-4 text-right"> <button id="exporttable" class="btn btn-primary">Export</button> </div>
                        </div>
                        <%
                        Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
                        JSONArray jsonArray=new JSONArray();
                        if(request.getParameter("filename")!=null){
                        	String ecrpt=request.getParameter("filename");
                        	String filename = daoImpl.decrypt(ecrpt.replace(" ", "+"));
                        	
                        	daoImpl.readJSonArr_5Col(jsonArray, filename);
                        }
                        %>
                        <div class="table-responsive">
                            <table id="htmltable" class="table">
                                <thead>
                                    <tr>
                                        <th>Vendor ID</th>
                                        <th>Name</th>
                                        <th>Total</th>
                                        <th>Success</th>
                                        <th>Pending</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                	for(int i=1;i<jsonArray.length();i++){
                                		JSONObject jsonObject=jsonArray.getJSONObject(i);
                                		
                                		%>
                                			 <tr>
		                                        <td><%=jsonObject.getString("id") %></td>
		                                        <td><%=jsonObject.getString("name") %></td>
		                                         <td><%=jsonObject.getString("total") %></td>
		                                         <td><%=jsonObject.getString("success") %></td>
		                                         <td><%=jsonObject.getString("pending") %></td>
		                                      </tr>
                                		<%
                                	}
                                	
                                %>
                                   
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
$(function() {
	$("#exporttable").click(function(e){
	var table = $("#htmltable");
	if(table && table.length){
	$(table).table2excel({
	exclude: ".noExl",
	name: "Excel Document Name",
	filename: "Count" + new Date().toISOString().replace(/[\-\:\.]/g, "") + ".xls",
	fileext: ".xls",
	exclude_img: true,
	exclude_links: true,
	exclude_inputs: true,
	preserveColors: false
	});
	}
	});

	});
</script>

<script type="text/javascript">
	function uploadFileExcel() {
		 $.getJSON("demo_ajax_json.js", function(result){
		      $.each(result, function(i, field){
		        $("div").append(field + " ");
		      });
		    });
	}
</script>
</html>