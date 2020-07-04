package com.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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

import api.daoImpl.Smpp_DaoImpl;

public class Server4Data {
	public static void main(String[] args) {



        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

        	//HttpPost request = new HttpPost("http://172.105.57.57/user/deliverySMSReport/?action=manage&search=1&sgcExport=1&fromDate=2020-04-13&toDate=2020-04-13&senderName=TESTSM&mobileNo=&uuId=");
        	HttpGet request=new HttpGet("http://122.160.97.195/vr_mis/analysis_4_resp.jsp?date-pick=2020-07-04&date-pick2=2020-07-04");
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
            for(Element tr : table1){
            	Elements td=tr.select("td");
            	boolean check=false;
            	int i=0;
            	txtmsg=txtmsg+"<tr>";
            	for(Element tddata:td) {
            		if(tddata.text().equalsIgnoreCase("vfirstTr1") || tddata.text().equalsIgnoreCase("vfirstTr2") || tddata.text().equalsIgnoreCase("vfirstTr3")||tddata.text().equalsIgnoreCase(" ")) {
            			check=true;
            		}
            		System.out.print(tddata+"\t");
            		if(check==true) {
            			
            			if(i==0) {
            				txtmsg=txtmsg+"<td style='background-color: orange;'>"+tddata.text()+"</td>";
            			}else {
            				txtmsg=txtmsg+"<td>"+tddata.text()+"</td>";
            			}
            		}
            		
            		i++;
            	}
            	txtmsg=txtmsg+"</tr>";
            	check=false;
            	System.out.println();
            }
            System.out.println(txtmsg);
            Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
        //    daoImpl.sendServer4Mail(txtmsg);
            
        }catch (Exception e) {
			e.printStackTrace();
		}
    
       
	}
}
