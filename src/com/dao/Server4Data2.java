package com.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.beans.Server4;

import api.daoImpl.Smpp_DaoImpl;

public class Server4Data2 {
	public static void main(String[] args) {
		
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
    	 				txt_msg=txt_msg+"<tr><td align='center' style='background-color: #efa5a5;'>"+server4.getAccount()+"</td>\r\n" + 
    	 						" 			<td align='center' style='background-color: #efa5a5;'>"+server4.getSUB()+"</td>\r\n" + 
    	 						" 			<td align='center' style='background-color: #efa5a5;'>"+server4.getDEL()+"</td>\r\n" + 
    	 						" 			<td align='center' style='background-color: #efa5a5;'>"+server4.getPercentage()+"</td>\r\n" + 
    	 						" 			<td align='center' style='background-color: #efa5a5;'>"+server4.getPending()+"</td>\r\n" + 
    	 						" 			<td align='center' style='background-color: #efa5a5;'>"+server4.getPending_per()+"</td>\r\n" + 
    	 						" 			\r\n" + 
    	 						" 			</tr>";
    	 			
    	 				System.out.println("Before sub=="+SUB);
    	 		 		System.out.println("sub value=="+server4.getSUB());
    	 		 		SUB=SUB+server4.getSUB();
    	 		 		System.out.println("after add  sub=="+SUB);
    	 		 		DEL=DEL+server4.getDEL();
    	 		 		Pending=Pending+server4.getPending();
    	 			
    	 			}
    	 	}
    	 		if(SUB>0){
    	 			percentage=(DEL*10)/(SUB/10);
    	 		 	Pending_PER=(Pending*10)/(SUB/10);
    	 		 	txt_msg=txt_msg+"<tr>\r\n" + 
    	 		 			"	 	<td> </td>\r\n" + 
    	 		 			"	 	<td align='center'>"+SUB+"</td>\r\n" + 
    	 		 			"	 	<td align='center'>"+DEL+"</td>\r\n" + 
    	 		 			"	 	<td align='center'>"+percentage+"</td>\r\n" + 
    	 		 			"	 	<td align='center'>"+Pending+"</td>\r\n" + 
    	 		 			"	 	<td align='center'>"+Pending_PER+"</td>\r\n" + 
    	 		 			"	 	</tr>";
    	 			}

    	 		for(Server4 server41:list1) {
    	 			if(server41.getSUB()>0) {
    	 				txt_msg=txt_msg+"<tr><td align='center' style='background-color: #FAFC75;'>"+server41.getAccount()+"</td>\r\n" + 
    	 						" 			<td align='center' style='background-color: #FAFC75;'>"+server41.getSUB()+"</td>\r\n" + 
    	 						" 			<td align='center' style='background-color: #FAFC75;'>"+server41.getDEL()+"</td>\r\n" + 
    	 						" 			<td align='center' style='background-color: #FAFC75;'>"+server41.getPercentage()+"</td>\r\n" + 
    	 						" 			<td align='center' style='background-color: #FAFC75;'>"+server41.getPending()+"</td>\r\n" + 
    	 						" 			<td align='center' style='background-color: #FAFC75;'>"+server41.getPending_per()+"</td>\r\n" + 
    	 						" 			\r\n" + 
    	 						" 			</tr>";
    	 				System.out.println("Before sub=="+SUB1);
    	 		 		System.out.println("sub value=="+server41.getSUB());
    	 		 		SUB1=SUB1+server41.getSUB();
    	 		 		System.out.println("after add  sub=="+SUB1);
    	 		 		DEL1=DEL1+server41.getDEL();
    	 		 		Pending1=Pending1+server41.getPending();	
    	 			}
    	 			
    	 		}
    	 		if(SUB1>0){
    	 			percentage1=(DEL1*10)/(SUB1/10);
    	 		 	Pending_PER1=(Pending1*10)/(SUB1/10);
    	 		txt_msg=txt_msg+"<tr>\r\n" + 
    	 				"	 	<td></td>\r\n" + 
    	 				"	 	<td align='center'>"+SUB1+"</td>\r\n" + 
    	 				"	 	<td align='center'>"+DEL1+"</td>\r\n" + 
    	 				"	 	<td align='center'>"+percentage1+"</td>\r\n" + 
    	 				"	 	<td align='center'>"+Pending1+"</td>\r\n" + 
    	 				"	 	<td align='center'>"+Pending_PER1+"</td>\r\n" + 
    	 				"	 	</tr>"; 	
    	 		}
    	 		for(Server4 server42:list2) {
    	 			if(server42.getSUB()>0) {
    	 				txt_msg=txt_msg+"<tr style='background-color: #3bc33b;'><td align='center'>"+server42.getAccount()+"</td>\r\n" + 
    	 						" 				<td align='center'>"+server42.getSUB()+"</td>\r\n" + 
    	 						" 				<td align='center'>"+server42.getDEL()+"</td>\r\n" + 
    	 						" 				<td align='center'>"+server42.getPercentage()+"</td>\r\n" + 
    	 						" 				<td align='center'>"+server42.getPending()+"</td>\r\n" + 
    	 						" 				<td align='center'>"+server42.getPending_per()+"</td>\r\n" + 
    	 						" 				</tr>";
    	 				
    	 				System.out.println("Before sub=="+SUB2);
    	 		 		System.out.println("sub value=="+server42.getSUB());
    	 		 		SUB2=SUB2+server42.getSUB();
    	 		 		System.out.println("after add  sub=="+SUB2);
    	 		 		DEL2=DEL2+server42.getDEL();
    	 		 		Pending2=Pending2+server42.getPending();
    	 			}
    	 		}
    	 		if(SUB2>0){
    	 			if(SUB2>10){
    	 				percentage2=(DEL2*10)/(SUB2/10);
    	 				Pending_PER2=(Pending2*10)/(SUB2/10);
    	 			}
    	 		txt_msg=txt_msg+"<tr>\r\n" + 
    	 				"	 	<td></td>\r\n" + 
    	 				"	 	<td align='center'>"+SUB2+"</td>\r\n" + 
    	 				"	 	<td align='center'>"+DEL2+"</td>\r\n" + 
    	 				"	 	<td align='center'>"+percentage2+"</td>\r\n" + 
    	 				"	 	<td align='center'>"+Pending2+"</td>\r\n" + 
    	 				"	 	<td align='center'>"+Pending_PER2+"</td>\r\n" + 
    	 				"	 	</tr>";
    	 		}

    	 		GRANDSUB=SUB+SUB1+SUB2;
    	 		GRANDDEL=DEL+DEL1+DEL2;
    	 		GRANDPending=Pending+Pending1+Pending2;
    	 		GRANDpercentage=(GRANDDEL*10)/(GRANDSUB/10);
    	 	 	GRANDPending_PER=(GRANDPending*10)/(GRANDSUB/10);
    	 	 	long lastcount=daoImpl.lastSubCount();
    	 	 	int ii=daoImpl.addLastSubCount(lastcount);
    	 	 	System.out.println("lastcountt===="+lastcount);
    	 	 	
    	 	 	txt_msg=txt_msg+"<tr>\r\n" + 
    	 	 			" 	<td align='center' style=\"background-color: orange;\">Total</td>\r\n" + 
    	 	 			" 	<td align='center'>"+GRANDSUB+"</td>\r\n" + 
    	 	 			" 	<td align='center'>"+GRANDDEL+"</td>\r\n" + 
    	 	 			" 	<td align='center'>"+GRANDpercentage+"</td>\r\n" + 
    	 	 			" 	<td align='center'>"+GRANDPending+"</td>\r\n" + 
    	 	 			" 	<td align='center'>"+GRANDPending_PER+"</td>\r\n" + 
    	 	 			" 	</tr>\r\n" + 
    	 	 			" 	\r\n" + 
    	 	 			" 	<tr>\r\n" + 
    	 	 			" 	<td align='center' style='background-color: #8080ff ;'>Last Half Hour </td>\r\n" + 
    	 	 			" 	<td align='center'>"+lastcount+"</td>\r\n" + 
    	 	 			" 	<td align='center' colspan='4'> </td>\r\n" + 
    	 	 			" 	</tr>";
    	 	 	System.out.println("lastcount===="+lastcount);
    	 		System.out.println("subbbb===="+SUB);
    	 		long traffic=GRANDSUB-lastcount;
    	 		System.out.println("traffic===="+traffic);
    	 		txt_msg=txt_msg+"<tr>\r\n" + 
    	 				"	<td align='center' style='background-color:#9999ff;'>&nbsp;</td>\r\n" + 
    	 				"	<td align='center' colspan='5'> </td>\r\n" + 
    	 				"	</tr>\r\n" + 
    	 				"	<tr>       <td align='center' style='background-color:#8080ff;'>Add On Traffic </td>\r\n" + 
    	 				"    	 		               <td align='center'>"+traffic+"</td> \r\n" + 
    	 				"    	 			             <td align='center' colspan='4'> </td>\r\n" + 
    	 				"    	 			   </tr> ";
    	        daoImpl.sendServer4Mail(list,txt_msg,requested_date);
    	        List<Server4> totalList=new ArrayList<>();
    	        totalList.addAll(list);
    	        totalList.addAll(list1);
    	        totalList.addAll(list2);
    	 		daoImpl.insertServer4Data(totalList);
    	 		//daoImpl.insertServer4Data(list1);
    	 		//daoImpl.insertServer4Data(list2);
    	 		}catch(Exception e){
    	 			e.printStackTrace();
    	 		}
        
		}
}
