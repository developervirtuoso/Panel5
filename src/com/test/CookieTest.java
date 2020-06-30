package com.test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
public class CookieTest {
	public static void main(String args[]) throws Exception {
		String cook="";
		 HttpClient client1 = new HttpClient();
		 client1.getParams().setParameter("sgc_username", "gupshupmain");
		 client1.getParams().setParameter("sgc_password", "hF3VJqtz");
		 client1.getParams().setParameter("secKey", "6afc14f02f4c26befe16f0335119e0c6");
		 client1.getParams().setParameter("sgcSubmitLoginForm", "1");

		    GetMethod method = new GetMethod("http://172.105.57.57/login/");
		    //PostMethod method=new PostMethod("http://172.105.57.57/login/");
		    try{
		    	client1.executeMethod(method);
		      Cookie[] cookies = client1.getState().getCookies();
		      for (int i = 0; i < cookies.length; i++) {
		        Cookie cookie = cookies[i];
		        System.out.println(cookie.toString());
		        if(i==0) {
		        	cook=cookie.toString();
		        }else {
		        	cook=cook+"; "+cookie.toString();
		        }
		        System.err.println(
		          "Cookie: " + cookie.getName() +
		          ", Value: " + cookie.getValue() +
		          ", IsPersistent?: " + cookie.isPersistent() +
		          ", Expiry Date: " + cookie.getExpiryDate() +
		          ", Comment: " + cookie.getComment());

		        cookie.setValue("My own value");
		        //PHPSESSID=o9q6umcqoetqsobsjaiqohma1i; SERVERNAME=s1
		        // PHPSESSID=jupng8e6229g6n7e7op3cvfeuv; SERVERNAME=s1
		      }
		      client1.executeMethod(method);
		    } catch(Exception e) {
		      System.err.println(e);
		    } finally {
		      method.releaseConnection();
		    }
		    System.out.println(cook);
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

        	//HttpPost request = new HttpPost("http://172.105.57.57/user/deliverySMSReport/?action=manage&search=1&sgcExport=1&fromDate=2020-04-13&toDate=2020-04-13&senderName=TESTSM&mobileNo=&uuId=");
        	HttpGet request=new HttpGet("http://172.105.57.57/login/");
        	request.setHeader("Host", "172.105.57.57");
        	request.setHeader("Connection", "keep-alive");
        	request.setHeader("Cache-Control", "max-age=0");
        	request.setHeader("Upgrade-Insecure-Requests", "1");
        	request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36");
        	request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        	request.setHeader("Accept-Encoding", "gzip, deflate");
        	request.setHeader("Accept-Language", "en-US,en;q=0.9");
        	request.setHeader("Cookie", cook);
           
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
           /* Document html = Jsoup.parse(builder.toString());
            String title = html.title();
            String h1 = html.body().getElementsByTag("h1").text();
     
            //System.out.println("Input HTML String to JSoup :" + HTMLSTring);
            System.out.println("After parsing, Title : " + title);
            System.out.println("Afte parsing, Heading : " + h1);
            Element table = html.select("table").get(0); 
            System.out.println("After table : " + table);*/
           
        }catch (Exception e) {
			e.printStackTrace();
		}
    
	
		
		
	   
	  }
	
}
