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

public class Server4Data {
	public static void main(String[] args) {



        try{
        	
        	 String requested_date=java.time.LocalDate.now().toString();
            Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
            List<Server4> list=daoImpl.getServer4DataWithApi(requested_date,requested_date);
           // System.out.println(list);
        	String txt_msg="";
    		 long SUB=0;
    		long DEL=0;
    		long percentage=0;
    		long Pending=0;
    	 	for(Server4 server4:list) {
    	 		if(server4.getSUB()>0) {
    	 		txt_msg=txt_msg+"<tr><td align='center' style='background-color: orange;'>"+server4.getAccount()+"</td>\r\n" + 
    	 				"  <td align='center'>"+server4.getSUB()+"</td>\r\n" + 
    	 				"  <td align='center'>"+server4.getDEL()+"</td>\r\n" + 
    	 				"  <td align='center'>"+server4.getPercentage()+"</td>\r\n" + 
    	 				"  <td align='center'>"+server4.getPending()+"</td>\r\n" + 
    	 				"  </tr>";
    	 		SUB=SUB+server4.getSUB();
    	 		DEL=DEL+server4.getDEL();
    	 		Pending=Pending+server4.getPending();
    	 			}
    	 		}
    	 	percentage=(DEL*10)/(SUB/10);
    	 	long lastcount=daoImpl.lastSubCount();
    	 	txt_msg=txt_msg+"<tr>\r\n" + 
    	 			"                <td align='center' style='background-color: orange;'>Total </td>\r\n" + 
    	 			"                <td align='center'>"+SUB+" </td>\r\n" + 
    	 			"                <td align='center'>"+DEL+" </td>\r\n" + 
    	 			"                <td align='center'>"+percentage+" </td>\r\n" + 
    	 			"                <td align='center'>"+Pending+" </td>\r\n" + 
    	 			"\r\n" + 
    	 			"            </tr> ";
    	 	txt_msg=txt_msg+"<tr>\r\n" + 
    	 			"                <td align='center' style='background-color: #8080ff ;'>Last Half Hour </td>\r\n" + 
    	 			"                <td align='center'>"+lastcount+" </td>\r\n" + 
    	 			"                <td align='center' colspan='3'> </td>\r\n" + 
    	 			"\r\n" + 
    	 			"            </tr> ";
    	 	System.out.println("lastcount===="+lastcount);
    	 	long traffic=SUB-lastcount;
    	 	System.out.println("traffic===="+traffic);
    	 	txt_msg=txt_msg+"<tr>\r\n" + 
    	 			"                <td align='center' style='background-color:#9999ff;'>&nbsp;</td>\r\n" + 
    	 			"                <td align='center' colspan='4'> </td>\r\n" + 
    	 			"\r\n" + 
    	 			"            </tr> ";
    	 	txt_msg=txt_msg+"<tr>\r\n" + 
    	 			"                <td align='center' style='background-color:#8080ff;'>Add On Traffic </td>\r\n" + 
    	 			"                <td align='center'>"+traffic+" </td>\r\n" + 
    	 			"                <td align='center' colspan='3'> </td>\r\n" + 
    	 			"\r\n" + 
    	 			"            </tr> ";
          daoImpl.sendServer4Mail(list,txt_msg,requested_date);
          daoImpl.insertServer4Data(list);
        }catch (Exception e) {
			e.printStackTrace();
		}
    
       
	}
}
