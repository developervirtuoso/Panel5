package com.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.beans.ReportRecord;
import com.beans.Server4VfReport;

import api.daoImpl.Smpp_DaoImpl;

public class DatesTest {

	public static void main(String args[]) {

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

		}
		
		//System.out.println("Months first date in yyyy-mm-dd: " +todaydate.withDayOfMonth(1));
	}

