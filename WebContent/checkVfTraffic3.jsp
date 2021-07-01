<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.poi.util.SystemOutLogger"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.beans.Server4"%>
<%@page import="java.util.List"%>
<%@page import="api.daoImpl.Smpp_DaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
table, td, th { border: 1px solid black;
				}
          		table {
          		border-collapse: collapse;
          		 width: 80%;}
          		th { text-align: left;}

</style>
</head>
<body>
<table>
<%
Date date = new Date();  
SimpleDateFormat formatter= new SimpleDateFormat("dd MMMM yyyy"); 
String strdate= formatter.format(date); 
formatter = new SimpleDateFormat("HH:mm:ss");  
Calendar cal = Calendar.getInstance();
cal.add(Calendar.HOUR, 5);
cal.add(Calendar.MINUTE, 30); 
 String strtime = formatter.format(cal.getTime()).toString();
%>
<tr align='center'>
<td align='center' style="background-color: #8080ff;">Date: <%=strdate %></td>
<td align='center' style="background-color: #8080ff;">Date: <%=strtime %></td>
<td align='center' colspan='4'></td>
</tr>
<tr align='center' style='background-color: #f0fc03;'>
<td align='center' ><b>Accounts</b></td>
<td align='center' ><b>Submitted</b></td>
<td align='center' ><b>Delivered</b></td>
<td align='center' ><b>%age</b></td>
<td align='center' ><b>Pending</b></td>
<td align='center' ><b>Pending %age</b></td>
</tr>
<%
try{
	String requested_date=java.time.LocalDate.now().toString();
    Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
    List<Server4> list=daoImpl.getServer4DataWithApi(requested_date,requested_date,"campaign");
    List<Server4> list1=daoImpl.getServer4DataWithApi(requested_date,requested_date,"transactional1");
    List<Server4> list2=daoImpl.getServer4DataWithApi(requested_date,requested_date,"transactional2");
   // System.out.println(list);
	String txt_msg="";
	long SUB=0;
	long SUB1=0;
	long SUB2=0;
	long GRANDSUB=0;
	long DEL=0;
	long DEL1=0;
	long DEL2=0;
	long GRANDDEL=0;
	long percentage=0;
	long percentage1=0;
	long percentage2=0;
	long GRANDpercentage=0;
	long Pending=0;
	long Pending1=0;
	long Pending2=0;
	long GRANDPending=0;
	long Pending_PER=0;
	long Pending_PER1=0;
	long Pending_PER2=0;
	long GRANDPending_PER=0;
	for(Server4 server4:list) {
		if(server4.getSUB()>0) {
			%>
				<tr><td align='center' style='background-color: #efa5a5;'><%=server4.getAccount()%></td>
 			<td align='center' style='background-color: #efa5a5;'><%=server4.getSUB()%></td>
 			<td align='center' style='background-color: #efa5a5;'><%=server4.getDEL()%></td>
 			<td align='center' style='background-color: #efa5a5;'><%=server4.getPercentage()%></td>
 			<td align='center' style='background-color: #efa5a5;'><%=server4.getPending()%></td>
 			<td align='center' style='background-color: #efa5a5;'><%=server4.getPending_per()%></td>
 			
 			</tr>
			<%
			System.out.println("Before sub=="+SUB);
	 		System.out.println("sub value=="+server4.getSUB());
	 		SUB=SUB+server4.getSUB();
	 		System.out.println("after add  sub=="+SUB);
	 		DEL=DEL+server4.getDEL();
	 		Pending=Pending+server4.getPending();
		}}
	if(SUB>0){
		percentage=(DEL*10)/(SUB/10);
	 	Pending_PER=(Pending*10)/(SUB/10);
	 	
	 	%>
	 	<tr>
	 	<td> </td>
	 	<td align='center'><%=SUB %></td>
	 	<td align='center'><%=DEL%></td>
	 	<td align='center'><%=percentage %></td>
	 	<td align='center'><%=Pending %></td>
	 	<td align='center'><%=Pending_PER %></td>
	 	</tr>
	<%}
	
	
	for(Server4 server4:list1) {
		if(server4.getSUB()>0) {
 			%>
 			<tr><td align='center' style='background-color: #FAFC75;'><%=server4.getAccount()%></td>
 			<td align='center' style='background-color: #FAFC75;'><%=server4.getSUB()%></td>
 			<td align='center' style='background-color: #FAFC75;'><%=server4.getDEL()%></td>
 			<td align='center' style='background-color: #FAFC75;'><%=server4.getPercentage()%></td>
 			<td align='center' style='background-color: #FAFC75;'><%=server4.getPending()%></td>
 			<td align='center' style='background-color: #FAFC75;'><%=server4.getPending_per()%></td>
 			
 			</tr>
 			<%
 			System.out.println("Before sub=="+SUB1);
	 		System.out.println("sub value=="+server4.getSUB());
	 		SUB1=SUB1+server4.getSUB();
	 		System.out.println("after add  sub=="+SUB1);
	 		DEL1=DEL1+server4.getDEL();
	 		Pending1=Pending1+server4.getPending();	
		}}
	if(SUB1>0){
		percentage1=(DEL1*10)/(SUB1/10);
	 	Pending_PER1=(Pending1*10)/(SUB1/10);
	 	
	 	%>
	 	<tr>
	 	<td></td>
	 	<td align='center'><%=SUB1 %></td>
	 	<td align='center'><%=DEL1%></td>
	 	<td align='center'><%=percentage1 %></td>
	 	<td align='center'><%=Pending1 %></td>
	 	<td align='center'><%=Pending_PER1 %></td>
	 	</tr>
		<%	
	}
	
	for(Server4 server4:list2) {
		if(server4.getSUB()>0) {
 				%>
 				<tr style='background-color: #3bc33b;'><td align='center'><%=server4.getAccount()%></td>
 				<td align='center'><%=server4.getSUB() %></td>
 				<td align='center'><%=server4.getDEL() %></td>
 				<td align='center'><%=server4.getPercentage() %></td>
 				<td align='center'><%=server4.getPending() %></td>
 				<td align='center'><%=server4.getPending_per()%></td>
 				</tr>
 			<% 
 			System.out.println("Before sub=="+SUB2);
	 		System.out.println("sub value=="+server4.getSUB());
	 		SUB2=SUB2+server4.getSUB();
	 		System.out.println("after add  sub=="+SUB2);
	 		DEL2=DEL2+server4.getDEL();
	 		Pending2=Pending2+server4.getPending();
		}
	}
	if(SUB2>0){
		if(SUB2>10){
			percentage2=(DEL2*10)/(SUB2/10);
			Pending_PER2=(Pending2*10)/(SUB2/10);
		}
	 	
	 	%>
	 	<tr>
	 	<td></td>
	 	<td align='center'><%=SUB2 %></td>
	 	<td align='center'><%=DEL2%></td>
	 	<td align='center'><%=percentage2 %></td>
	 	<td align='center'><%=Pending2 %></td>
	 	<td align='center'><%=Pending_PER2 %></td>
	 	</tr>
	<%}
	
	GRANDSUB=SUB+SUB1+SUB2;
	GRANDDEL=DEL+DEL1+DEL2;
	GRANDPending=Pending+Pending1+Pending2;
	GRANDpercentage=(GRANDDEL*10)/(GRANDSUB/10);
 	GRANDPending_PER=(GRANDPending*10)/(GRANDSUB/10);
 	long lastcount=daoImpl.lastSubCountURL();
 	System.out.println("lastcountt===="+lastcount);
 	%>
 	
 	<tr>
 	<td align='center' style="background-color: orange;">Total</td>
 	<td align='center'><%=GRANDSUB %></td>
 	<td align='center'><%=GRANDDEL%></td>
 	<td align='center'><%=GRANDpercentage %></td>
 	<td align='center'><%=GRANDPending %></td>
 	<td align='center'><%=GRANDPending_PER %></td>
 	</tr>
 	
 	<tr>
 	<td align='center' style='background-color: #8080ff ;'>Last Half Hour </td>
 	<td align='center'><%=lastcount %></td>
 	<td align='center' colspan='4'> </td>
 	</tr>
<% 
	System.out.println("lastcount===="+lastcount);
	System.out.println("subbbb===="+SUB);
	long traffic=GRANDSUB-lastcount;
	System.out.println("traffic===="+traffic);
	%>
	<tr>
	<td align='center' style='background-color:#9999ff;'>&nbsp;</td>
	<td align='center' colspan='5'> </td>
	</tr>
	<tr>       <td align='center' style='background-color:#8080ff;'>Add On Traffic </td>
    	 		               <td align='center'><%=traffic %></td> 
    	 			             <td align='center' colspan='4'> </td>
    	 			   </tr> 
<%
/*daoImpl.insertServer4Data(list);
daoImpl.insertServer4Data(list1);
daoImpl.insertServer4Data(list2); */
List<Server4> totalList=new ArrayList<>();
totalList.addAll(list);
totalList.addAll(list1);
totalList.addAll(list2);
daoImpl.insertServer4DataThroughUrl(totalList);
}catch(Exception e){
	e.printStackTrace();
}
%>
</table>
</body>
</html>