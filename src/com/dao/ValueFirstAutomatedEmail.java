package com.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.beans.Server4;
import com.beans.Server4VfReport;

import api.daoImpl.Smpp_DaoImpl;

public class ValueFirstAutomatedEmail {
	public static void main(String[] args) {



        try{
        	
        	/* String requested_date=java.time.LocalDate.now().toString();
        	 System.out.println("requested_date==>"+requested_date);*/
        	 
        	Calendar cal = Calendar.getInstance();
        	 cal.add(Calendar.DATE, -1);
        	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	 String previous_date=dateFormat.format(cal.getTime()).toString();
        	 System.out.println("previous_date==>"+previous_date);
        	 
            Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
            List<Server4VfReport> list=daoImpl.getServer4DataForVFWithApi(previous_date,previous_date,"yes");
           // System.out.println(list);
        	String txt_msg="";
    	
    	 	for(Server4VfReport server4:list) {
    	 		if(server4.getSUB()>0) {
    	 		txt_msg=txt_msg+"<tr><td align='center' style='background-color: orange;'>"+server4.getAccount()+"</td>\r\n" + 
    	 				"  <td align='center'>"+server4.getSUB()+"</td>\r\n" + 
    	 				"  <td align='center'>"+server4.getDEL()+"</td>\r\n" + 
    	 				"  <td align='center'>"+server4.getDel_per()+" %</td>\r\n" + 
    	 				"  <td align='center'>"+server4.getFAILED()+"</td>\r\n" +
    	 				"  <td align='center'>"+server4.getFailed_per()+" %</td>\r\n" +
    	 				"  <td align='center'>"+server4.getWAITING()+"</td>\r\n" +
    	 				"  <td align='center'>"+server4.getWaiting_per()+" %</td>\r\n" +
    	 				"  </tr>";
    	
    	 			}
    	 		}
    	 
    	
    	 
          daoImpl.sendVFMail(list,"Daily",txt_msg,previous_date);
          int j=daoImpl.insertDailyReportRecord(java.time.LocalDate.now().toString(),"no");
        }catch (Exception e) {
			e.printStackTrace();
		}
    
       
	}
}
