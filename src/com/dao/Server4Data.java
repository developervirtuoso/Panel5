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



        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
        	String requested_date=java.time.LocalDate.now().toString();
        	//HttpPost request = new HttpPost("http://172.105.57.57/user/deliverySMSReport/?action=manage&search=1&sgcExport=1&fromDate=2020-04-13&toDate=2020-04-13&senderName=TESTSM&mobileNo=&uuId=");
        	HttpGet request=new HttpGet("http://122.160.97.195/vr_mis/analysis_4_resp.jsp?date-pick="+requested_date+"&date-pick2="+requested_date+"");
            HttpResponse response = client.execute(request);

            BufferedReader bufReader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuilder builder = new StringBuilder();

            String line;

            while ((line = bufReader.readLine()) != null) {

                builder.append(line);
                builder.append(System.lineSeparator());
            }

           System.out.println("bbb"+builder);
            Document html = Jsoup.parse(builder.toString());
            Element table = html.select("table").get(0); 
            String txtmsg="";
          //  System.out.println("After table : " + table);
            Elements table1 = html.select("table").select("tbody").select("tr");
            long submitted=0;
            List<Server4> list=new ArrayList<Server4>();
            for(Element tr : table1){
            	Elements td=tr.select("td");
            	boolean check=false;
            	int i=0;
            	txtmsg=txtmsg+"<tr>";
            	//System.out.print(tr.text()+"\t");
            	
            	if(tr.text().indexOf("vfirstTr1")>=0 || tr.text().indexOf("vfirstTr2")>=0 || tr.text().indexOf("vfirstTr3")>=0 || tr.text().indexOf("vfirstTr4")>=0 || tr.text().indexOf("vfirstPR1")>=0 ) {
            	Server4 server4=new Server4();
            	for(Element tddata:td) {
            			
            			if(i==0) {
            				txtmsg=txtmsg+"<td style='background-color: orange;'>"+tddata.text()+"</td>";
            				server4.setAccount(tddata.text());
            			}else if(i==1){
            				txtmsg=txtmsg+"<td>"+tddata.text()+"</td>";
            				server4.setId(Long.parseLong(tddata.text()));
            			}else if(i==2){
            				txtmsg=txtmsg+"<td>"+tddata.text()+"</td>";
            				server4.setSUB(Long.parseLong(tddata.text()));
            			}else if(i==3){
            				txtmsg=txtmsg+"<td>"+tddata.text()+"</td>";
            				server4.setDEL(Long.parseLong(tddata.text()));
            			}else if(i==4){
            				txtmsg=txtmsg+"<td>"+tddata.text()+"</td>";
            				server4.setPercentage(Long.parseLong(tddata.text()));
            			}else if(i==5){
            				txtmsg=txtmsg+"<td>"+tddata.text()+"</td>";
            				server4.setPending(Long.parseLong(tddata.text()));
            			}else if(i==6){
            				txtmsg=txtmsg+"<td>"+tddata.text()+"</td>";
            				server4.setPending(Long.parseLong(tddata.text()));
            			}else if(i==6){
            				txtmsg=txtmsg+"<td>"+tddata.text()+"</td>";
            				server4.setPending(Long.parseLong(tddata.text()));
            			}
            		
            		i++;
            	
            }
            	list.add(server4);
            }
            	txtmsg=txtmsg+"</tr>";
            	/*if(tr.text().indexOf("Total")>=0) {
            		txtmsg=txtmsg+tr;
            		//System.out.println(tr);
            	}*/
            	
            	System.out.println();
            }
            System.out.println(list);
            
            Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
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
    	 	long traffic=SUB-lastcount;
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
