<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.beans.Campaign_data"%>
<%@page import="java.util.List"%>
<%@page import="api.daoImpl.Smpp_DaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: left;
  padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}
#data {
    padding-right: 250px;
    padding-left: 250px;
    padding-top: 29px;
}
div#time {
    padding-top: 10px;
    padding-left: 250px;
}
div#icn {
    padding-top: 10px;
    padding-left: 64em;
}
</style>
</head>
<body>
<div id="icn">
<button onclick="reload()"><i class="fa fa-refresh fa-spin" style="font-size:24px"></i></button>

</div>
<%
Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
Calendar cal = Calendar.getInstance();
System.out.println(dateFormat.format(cal.getTime()).toString());
%>
<div id="time">
<label>Last checked:-</label><%=daoImpl.getLastCheckedTimeForCampaigns()%><br><br>
<label>Current time:-</label><%=dateFormat.format(cal.getTime()).toString()%>
</div>
<div style="overflow-x:auto;" id="data">
  <table>
    <tr>
      <th></th>
      <th>Submitted</th>
      <th>Delivered</th>
      <th>%age</th>
      <th>Pending</th>
      
    </tr>
    <%
    String requested_date=java.time.LocalDate.now().toString();
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
  
    List<Campaign_data> cd=daoImpl.getCampaignData(requested_date, requested_date,dateFormat.format(cal.getTime()).toString());
    long SUB=0;
	long DEL=0;
	long percentage=0;
	long Pending=0;
	for(Campaign_data campaign_data:cd) {
    %>
    <tr>
      <td><%=campaign_data.getAccount() %></td>
      <td><%=campaign_data.getSub()%></td>
      <td><%=campaign_data.getDel()%></td>
      <td><%=campaign_data.getPer()%></td>
      <td><%=campaign_data.getPending()%></td>
      </tr>
      <%
      SUB=SUB+campaign_data.getSub();
      DEL=DEL+campaign_data.getDel();
      percentage=percentage+campaign_data.getPer();
      Pending=Pending+campaign_data.getPending();
	
	}
	System.out.println("delll==>"+DEL);
	System.out.println("SUB==>"+SUB);
	percentage=(DEL*100)/(SUB);
 	long lastcount=daoImpl.lastSubCountForCampaign();
 	long traffic=SUB-lastcount;
 			
	%>
       <tr>
      <td>Total</td>
      <td><%=SUB%></td>
      <td><%=DEL%></td>
      <td><%=percentage%></td>
      <td><%=Pending%><td>
      </tr>
       <tr>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      <td></td>
      </tr>
       <tr>
      <td>Add on</td>
      <td><%=traffic%></td>
      <td></td>
      <td></td>
      <td></td>
      </tr>
   <%
   daoImpl.insertCampaignData(cd);
   %>
  </table>
</div>
</body>
<script>
function reload(){
	location.reload();
}
</script>
</html>