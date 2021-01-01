package com.servlet;

import java.io.IOException;
import java.sql.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.ReportRecord;
import com.beans.Server4VfReport;
import com.beans.VFEmails;

import api.daoImpl.Smpp_DaoImpl;

/**
 * Servlet implementation class SendReport
 */
@WebServlet("/SendReport")
public class SendReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


        try{
        	
        	/* String requested_date=java.time.LocalDate.now().toString();
        	 System.out.println("requested_date==>"+requested_date);*/
        	 
        /*	Calendar cal = Calendar.getInstance();
        	 cal.add(Calendar.DATE, -1);
        	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	 String previous_date=dateFormat.format(cal.getTime()).toString();
        	 System.out.println("previous_date==>"+previous_date);
        	 
            Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
            List<Server4VfReport> list=daoImpl.getServer4DataForVFWithApi(previous_date,previous_date,"no");
           // System.out.println(list);
            long sub_total=0;
            long del_total=0;
            long del_total_per=0;
            long failed_total=0;
            long failed_total_per=0;
            long waiting_total=0;
            long waiting_total_per=0;
            long mtd_total=0;
        	String txt_msg="";
    	
    	 	for(Server4VfReport server4:list) {
    	 		System.out.println("sub===>"+server4.getSUB());
    	 	//	if(server4.getSUB()>0) {
    	 		txt_msg=txt_msg+"<tr><td align='center' style='background-color: orange;'>"+server4.getAccount()+"</td>\r\n" + 
    	 				"  <td align='center'>"+server4.getSUB()+"</td>\r\n" + 
    	 				"  <td align='center'>"+server4.getDEL()+"</td>\r\n" + 
    	 				"  <td align='center'>"+server4.getDel_per()+" %</td>\r\n" + 
    	 				"  <td align='center'>"+server4.getFAILED()+"</td>\r\n" +
    	 				"  <td align='center'>"+server4.getFailed_per()+" %</td>\r\n" +
    	 				"  <td align='center'>"+server4.getWAITING()+"</td>\r\n" +
    	 				"  <td align='center'>"+server4.getWaiting_per()+" %</td>\r\n" +
    	 				"  <td align='center'>"+server4.getMtd()+" </td>\r\n" +

    	 				"  </tr>";
    	
    	 			//}
    	 		sub_total=sub_total+server4.getSUB();
    	 		del_total=del_total+server4.getDEL();
    	 		del_total_per=(del_total*100 / (sub_total));
    	 		failed_total=failed_total+server4.getFAILED();
    	 		failed_total_per=(failed_total*100/(sub_total));
    	 		waiting_total=waiting_total+server4.getWAITING();
    	 		waiting_total_per=(waiting_total*100/(sub_total));
    	 		mtd_total=mtd_total+server4.getMtd();
    	 		
    	 		
    	 		}
    	 	txt_msg=txt_msg+"<tr>\r\n" + 
    	 			"                <td align='center' style='background-color: orange;'>Total </td>\r\n" + 
    	 			"                <td align='center'>"+sub_total+" </td>\r\n" + 
    	 			"                <td align='center'>"+del_total+" </td>\r\n" + 
    	 			"                <td align='center'>"+del_total_per+" </td>\r\n" + 
    	 			"                <td align='center'>"+failed_total+" </td>\r\n" + 
    	 			"                <td align='center'>"+failed_total_per+" </td>\r\n" + 
    	 			"                <td align='center'>"+waiting_total+" </td>\r\n" + 
    	 			"                <td align='center'>"+waiting_total_per+" </td>\r\n" + 
    	 			"                <td align='center'>"+mtd_total+" </td>\r\n" + 

    	 			"\r\n" + 
    	 			"            </tr> "; */
/////////////////////////////new code starts/////////////////////////////////

LocalDate todaydate = LocalDate.now();
System.out.println("current date in yyyy-mm-dd: " +todaydate);
System.out.println("current date day in yyyy-mm-dd: " +todaydate.getDayOfMonth());


Smpp_DaoImpl smpp_DaoImpl=new Smpp_DaoImpl();
if(todaydate.compareTo(todaydate.withDayOfMonth(1))==0) {
System.out.println("in iffffffffffffff");
}else {

for(int i=1;i<todaydate.getDayOfMonth();i++) {
	System.out.println("Dates in yyyy-mm-dd: " +todaydate.withDayOfMonth(i));
    List<Server4VfReport> list=smpp_DaoImpl.getServer4DataForVFReportRecord(todaydate.withDayOfMonth(i).toString(),todaydate.withDayOfMonth(i).toString(),"yes");
    for(Server4VfReport server4:list) {
		boolean check=smpp_DaoImpl.checkEntryByDateAndAccount(server4.getAccount(), todaydate.withDayOfMonth(i).toString());
		if(check==false) {
			
	            
			 System.out.println(check+"===>>"+todaydate.withDayOfMonth(i));
				
	            ReportRecord record=new ReportRecord();
	            record.setAccount(server4.getAccount());
	            record.setSub(server4.getSUB());
	            record.setDel(server4.getDEL());
	            record.setDel_per(server4.getDel_per());
	            record.setFailed(server4.getFAILED());
	            record.setFailed_per(server4.getFailed_per());
	            record.setWaiting(server4.getWAITING());
	            record.setWaiting_per(server4.getWaiting_per());
	            record.setMtd(server4.getMtd());
	            System.out.println("mtd=========================>"+server4.getMtd());
	            record.setReport_date(todaydate.withDayOfMonth(i).toString());
	            
	            
	            
	            int in=smpp_DaoImpl.insertVFRecords(record);
	            if(in>0) {
	            	System.out.println("updated===>"+server4.getAccount()+"==>>"+todaydate.withDayOfMonth(i));
	            }else {
	            	System.out.println("Error");
	            }
	           


		}
    }
     //   record.setAccount(details);
   //     int j=smpp_DaoImpl.insertDailyReportRecord(java.time.LocalDate.now().toString(),"yes");

	}
}
ArrayList<ReportRecord> rr=smpp_DaoImpl.recordForTodayMail(todaydate.minusDays(1).toString());
String txt_msg="";
long sub=0;
long sub_total=0;
long del=0;
long del_total=0;
long del_total_per=0;
long failed=0;
long failed_total=0;
long failed_total_per=0;
long waiting=0;
long waiting_total=0;
long waiting_total_per=0;
long mtd=0;
long mtd_total=0;
String account="";
for(int l=0;l<rr.size();l++) {
txt_msg=txt_msg+"<tr><td align='center' style='background-color: orange;'>"+rr.get(l).getAccount()+"</td>\r\n" + 
			"  <td align='center'>"+rr.get(l).getSub()+"</td>\r\n" + 
			"  <td align='center'>"+rr.get(l).getDel()+"</td>\r\n" + 
			"  <td align='center'>"+rr.get(l).getDel_per()+" %</td>\r\n" + 
			"  <td align='center'>"+rr.get(l).getFailed()+"</td>\r\n" +
			"  <td align='center'>"+rr.get(l).getFailed_per()+" %</td>\r\n" +
			"  <td align='center'>"+rr.get(l).getWaiting()+"</td>\r\n" +
			"  <td align='center'>"+rr.get(l).getWaiting_per()+" %</td>\r\n" +
			"  <td align='center'>"+rr.get(l).getMtd()+" </td>\r\n" +

			"  </tr>";
 sub_total=sub_total+rr.get(l).getSub();
	del_total=del_total+rr.get(l).getDel();
	del_total_per=(del_total*100 / (sub_total));
	failed_total=failed_total+rr.get(l).getFailed();
	failed_total_per=(failed_total*100/(sub_total));
	waiting_total=waiting_total+rr.get(l).getWaiting();
	waiting_total_per=(waiting_total*100/(sub_total));
	mtd_total=mtd_total+rr.get(l).getMtd();
}
txt_msg=txt_msg+"<tr>\r\n" + 
		"                <td align='center' style='background-color: orange;'>Total </td>\r\n" + 
		"                <td align='center'>"+sub_total+" </td>\r\n" + 
		"                <td align='center'>"+del_total+" </td>\r\n" + 
		"                <td align='center'>"+del_total_per+" </td>\r\n" + 
		"                <td align='center'>"+failed_total+" </td>\r\n" + 
		"                <td align='center'>"+failed_total_per+" </td>\r\n" + 
		"                <td align='center'>"+waiting_total+" </td>\r\n" + 
		"                <td align='center'>"+waiting_total_per+" </td>\r\n" + 
		"                <td align='center'>"+mtd_total+" </td>\r\n" + 

		"\r\n" + 
		"            </tr> ";
		smpp_DaoImpl.sendVFReport("Daily Traffic Report",txt_msg,todaydate.minusDays(1).toString());
/////////////////////new code ends///////////////////////////////////////////////////

   //       daoImpl.sendVFMail(list,"Daily Traffic Report",txt_msg,previous_date);
          int j=smpp_DaoImpl.insertDailyReportRecord(java.time.LocalDate.now().toString(),"no");
          if(j>0) {
				response.sendRedirect("ValueFirstEmail.jsp?message=6");

          }else {
				response.sendRedirect("ValueFirstEmail.jsp?message=7");

          }
        }catch (Exception e) {
			e.printStackTrace();
		}
    
    
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
