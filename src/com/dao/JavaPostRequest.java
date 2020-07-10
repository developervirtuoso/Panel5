package com.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 *
 * @author Murari Dev
 */
public class JavaPostRequest {
	
	public static void main(String[] args) throws IOException {
		


        HttpPost post = new HttpPost("http://172.105.57.57/admin/push_smpp_connects/?action=ajaxRead");
        HttpClient client = HttpClientBuilder.create().build();
      
        StringEntity entity = new StringEntity("draw=1&columns%5B0%5D%5Bdata%5D=sel&columns%5B0%5D%5Bname%5D=&columns%5B0%5D%5Bsearchable%5D=false&columns%5B0%5D%5Borderable%5D=false&columns%5B0%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B0%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B1%5D%5Bdata%5D=id&columns%5B1%5D%5Bname%5D=&columns%5B1%5D%5Bsearchable%5D=true&columns%5B1%5D%5Borderable%5D=true&columns%5B1%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B1%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B2%5D%5Bdata%5D=name&columns%5B2%5D%5Bname%5D=&columns%5B2%5D%5Bsearchable%5D=true&columns%5B2%5D%5Borderable%5D=true&columns%5B2%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B2%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B3%5D%5Bdata%5D=host&columns%5B3%5D%5Bname%5D=&columns%5B3%5D%5Bsearchable%5D=true&columns%5B3%5D%5Borderable%5D=true&columns%5B3%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B3%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B4%5D%5Bdata%5D=user&columns%5B4%5D%5Bname%5D=&columns%5B4%5D%5Bsearchable%5D=true&columns%5B4%5D%5Borderable%5D=true&columns%5B4%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B4%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B5%5D%5Bdata%5D=tps&columns%5B5%5D%5Bname%5D=&columns%5B5%5D%5Bsearchable%5D=true&columns%5B5%5D%5Borderable%5D=true&columns%5B5%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B5%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B6%5D%5Bdata%5D=type&columns%5B6%5D%5Bname%5D=&columns%5B6%5D%5Bsearchable%5D=true&columns%5B6%5D%5Borderable%5D=true&columns%5B6%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B6%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B7%5D%5Bdata%5D=internalQ&columns%5B7%5D%5Bname%5D=&columns%5B7%5D%5Bsearchable%5D=true&columns%5B7%5D%5Borderable%5D=true&columns%5B7%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B7%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B8%5D%5Bdata%5D=kannelQ&columns%5B8%5D%5Bname%5D=&columns%5B8%5D%5Bsearchable%5D=true&columns%5B8%5D%5Borderable%5D=true&columns%5B8%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B8%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B9%5D%5Bdata%5D=sessions&columns%5B9%5D%5Bname%5D=&columns%5B9%5D%5Bsearchable%5D=false&columns%5B9%5D%5Borderable%5D=false&columns%5B9%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B9%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B10%5D%5Bdata%5D=price&columns%5B10%5D%5Bname%5D=&columns%5B10%5D%5Bsearchable%5D=false&columns%5B10%5D%5Borderable%5D=false&columns%5B10%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B10%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B11%5D%5Bdata%5D=sesMan&columns%5B11%5D%5Bname%5D=&columns%5B11%5D%5Bsearchable%5D=true&columns%5B11%5D%5Borderable%5D=true&columns%5B11%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B11%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B12%5D%5Bdata%5D=&columns%5B12%5D%5Bname%5D=&columns%5B12%5D%5Bsearchable%5D=false&columns%5B12%5D%5Borderable%5D=false&columns%5B12%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B12%5D%5Bsearch%5D%5Bregex%5D=false&order%5B0%5D%5Bcolumn%5D=1&order%5B0%5D%5Bdir%5D=desc&start=0&length=25&search%5Bvalue%5D=&search%5Bregex%5D=false");

        post.setHeader("Host", "172.105.57.57");
        post.setHeader("Connection", "keep-alive");
    	//post.setHeader("Content-Length", "2884");
    	post.setHeader("Cache-Control", "max-age=0");
    	post.setHeader("Origin", "http://172.105.57.57");
    	post.setHeader("Upgrade-Insecure-Requests", "1");
    	post.setHeader("Content-Type", "application/x-www-form-urlencoded");
    	post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36");
    	post.setHeader("Accept", "application/json, text/javascript");
    	post.setHeader("Referer", "http://172.105.57.57/admin/push_smpp_connects/?action=manage");
    	post.setHeader("Accept-Encoding", "gzip, deflate");
    	post.setHeader("Accept-Language", "en-US,en;q=0.9");
    	post.setHeader("Cookie", "PHPSESSID=a2sn1qn2v84hv1620q3133aa0e; SERVERNAME=s1");
    	post.setHeader("X-Requested-With","XMLHttpRequest");
        post.setEntity(entity);
        HttpResponse response = client.execute(post);

        int responseCode = response.getStatusLine().getStatusCode();

//        System.out.println("\nSending 'POST' request to URL : " + url);
    //    System.out.println("Post parameters : " + postParams);
        System.out.println("Response Code : " + response.getEntity().getContentType());
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
           // System.out.println(line);
        }
        System.out.println("44444"+result.toString());
        String data=result.toString();
       
         
    

      /*  try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

        	HttpPost request = new HttpPost("http://172.105.57.57/admin/push_smpp_connects/?action=ajaxRead");
        	String urlParameters = "draw=1&columns%5B0%5D%5Bdata%5D=sel&columns%5B0%5D%5Bname%5D=&columns%5B0%5D%5Bsearchable%5D=false&columns%5B0%5D%5Borderable%5D=false&columns%5B0%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B0%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B1%5D%5Bdata%5D=id&columns%5B1%5D%5Bname%5D=&columns%5B1%5D%5Bsearchable%5D=true&columns%5B1%5D%5Borderable%5D=true&columns%5B1%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B1%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B2%5D%5Bdata%5D=name&columns%5B2%5D%5Bname%5D=&columns%5B2%5D%5Bsearchable%5D=true&columns%5B2%5D%5Borderable%5D=true&columns%5B2%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B2%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B3%5D%5Bdata%5D=host&columns%5B3%5D%5Bname%5D=&columns%5B3%5D%5Bsearchable%5D=true&columns%5B3%5D%5Borderable%5D=true&columns%5B3%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B3%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B4%5D%5Bdata%5D=user&columns%5B4%5D%5Bname%5D=&columns%5B4%5D%5Bsearchable%5D=true&columns%5B4%5D%5Borderable%5D=true&columns%5B4%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B4%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B5%5D%5Bdata%5D=tps&columns%5B5%5D%5Bname%5D=&columns%5B5%5D%5Bsearchable%5D=true&columns%5B5%5D%5Borderable%5D=true&columns%5B5%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B5%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B6%5D%5Bdata%5D=type&columns%5B6%5D%5Bname%5D=&columns%5B6%5D%5Bsearchable%5D=true&columns%5B6%5D%5Borderable%5D=true&columns%5B6%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B6%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B7%5D%5Bdata%5D=internalQ&columns%5B7%5D%5Bname%5D=&columns%5B7%5D%5Bsearchable%5D=true&columns%5B7%5D%5Borderable%5D=true&columns%5B7%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B7%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B8%5D%5Bdata%5D=kannelQ&columns%5B8%5D%5Bname%5D=&columns%5B8%5D%5Bsearchable%5D=true&columns%5B8%5D%5Borderable%5D=true&columns%5B8%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B8%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B9%5D%5Bdata%5D=sessions&columns%5B9%5D%5Bname%5D=&columns%5B9%5D%5Bsearchable%5D=false&columns%5B9%5D%5Borderable%5D=false&columns%5B9%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B9%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B10%5D%5Bdata%5D=price&columns%5B10%5D%5Bname%5D=&columns%5B10%5D%5Bsearchable%5D=false&columns%5B10%5D%5Borderable%5D=false&columns%5B10%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B10%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B11%5D%5Bdata%5D=sesMan&columns%5B11%5D%5Bname%5D=&columns%5B11%5D%5Bsearchable%5D=true&columns%5B11%5D%5Borderable%5D=true&columns%5B11%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B11%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B12%5D%5Bdata%5D=&columns%5B12%5D%5Bname%5D=&columns%5B12%5D%5Bsearchable%5D=false&columns%5B12%5D%5Borderable%5D=false&columns%5B12%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B12%5D%5Bsearch%5D%5Bregex%5D=false&order%5B0%5D%5Bcolumn%5D=1&order%5B0%5D%5Bdir%5D=desc&start=0&length=25&search%5Bvalue%5D=&search%5Bregex%5D=false";
        	byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        	
			/*
			 * request.setHeader("User-Agent", "Java client");
			 * request.setHeader("User-Agent", "Java client");
			 */
            //con.setRequestProperty("//POST /user/userReports/?action=deliverySummary HTTP/1.1","");
        	/*request.setHeader("Host", "172.105.57.57");
        	request.setHeader("Connection", "keep-alive");
        	request.setHeader("Content-Length", "2884");
        	request.setHeader("Cache-Control", "max-age=0");
        	request.setHeader("Origin", "http://172.105.57.57");
        	request.setHeader("Upgrade-Insecure-Requests", "1");
        	request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        	request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36");
        	request.setHeader("Accept", "application/json, text/javascript");
        /*	request.setHeader("Referer", "http://172.105.57.57/admin/push_smpp_connects/?action=manage");
        	request.setHeader("Accept-Encoding", "gzip, deflate");
        	request.setHeader("Accept-Language", "en-US,en;q=0.9");
        	request.setHeader("Cookie", "PHPSESSID=a2sn1qn2v84hv1620q3133aa0e; SERVERNAME=s1");
        	request.setHeader("X-Requested-With","XMLHttpRequest");
           
        	
            request.setEntity(new StringEntity(urlParameters));
            
            HttpResponse response = client.execute(request);

  /*          BufferedReader bufReader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuilder builder = new StringBuilder();

            String line;

            while ((line = bufReader.readLine()) != null) {

                builder.append(line);
                builder.append(System.lineSeparator());
            }

//          / System.out.println("bbb"+builder);
           
           
        }catch (Exception e) {
			e.printStackTrace();
		}*/
    }
	public String pushSmppConnectApiData(String cookiename) {
		String data="{}";
		try {
			
		
		 HttpPost post = new HttpPost("http://clientsc.sms24hours.com/admin/push_smpp_connects/?action=ajaxRead");
	        HttpClient client = HttpClientBuilder.create().build();
	      
	        StringEntity entity = new StringEntity("draw=1&columns%5B0%5D%5Bdata%5D=sel&columns%5B0%5D%5Bname%5D=&columns%5B0%5D%5Bsearchable%5D=false&columns%5B0%5D%5Borderable%5D=false&columns%5B0%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B0%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B1%5D%5Bdata%5D=id&columns%5B1%5D%5Bname%5D=&columns%5B1%5D%5Bsearchable%5D=true&columns%5B1%5D%5Borderable%5D=true&columns%5B1%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B1%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B2%5D%5Bdata%5D=name&columns%5B2%5D%5Bname%5D=&columns%5B2%5D%5Bsearchable%5D=true&columns%5B2%5D%5Borderable%5D=true&columns%5B2%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B2%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B3%5D%5Bdata%5D=host&columns%5B3%5D%5Bname%5D=&columns%5B3%5D%5Bsearchable%5D=true&columns%5B3%5D%5Borderable%5D=true&columns%5B3%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B3%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B4%5D%5Bdata%5D=user&columns%5B4%5D%5Bname%5D=&columns%5B4%5D%5Bsearchable%5D=true&columns%5B4%5D%5Borderable%5D=true&columns%5B4%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B4%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B5%5D%5Bdata%5D=tps&columns%5B5%5D%5Bname%5D=&columns%5B5%5D%5Bsearchable%5D=true&columns%5B5%5D%5Borderable%5D=true&columns%5B5%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B5%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B6%5D%5Bdata%5D=type&columns%5B6%5D%5Bname%5D=&columns%5B6%5D%5Bsearchable%5D=true&columns%5B6%5D%5Borderable%5D=true&columns%5B6%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B6%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B7%5D%5Bdata%5D=internalQ&columns%5B7%5D%5Bname%5D=&columns%5B7%5D%5Bsearchable%5D=true&columns%5B7%5D%5Borderable%5D=true&columns%5B7%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B7%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B8%5D%5Bdata%5D=kannelQ&columns%5B8%5D%5Bname%5D=&columns%5B8%5D%5Bsearchable%5D=true&columns%5B8%5D%5Borderable%5D=true&columns%5B8%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B8%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B9%5D%5Bdata%5D=sessions&columns%5B9%5D%5Bname%5D=&columns%5B9%5D%5Bsearchable%5D=false&columns%5B9%5D%5Borderable%5D=false&columns%5B9%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B9%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B10%5D%5Bdata%5D=price&columns%5B10%5D%5Bname%5D=&columns%5B10%5D%5Bsearchable%5D=false&columns%5B10%5D%5Borderable%5D=false&columns%5B10%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B10%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B11%5D%5Bdata%5D=sesMan&columns%5B11%5D%5Bname%5D=&columns%5B11%5D%5Bsearchable%5D=true&columns%5B11%5D%5Borderable%5D=true&columns%5B11%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B11%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B12%5D%5Bdata%5D=&columns%5B12%5D%5Bname%5D=&columns%5B12%5D%5Bsearchable%5D=false&columns%5B12%5D%5Borderable%5D=false&columns%5B12%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B12%5D%5Bsearch%5D%5Bregex%5D=false&order%5B0%5D%5Bcolumn%5D=1&order%5B0%5D%5Bdir%5D=desc&start=0&length=25&search%5Bvalue%5D=&search%5Bregex%5D=false");

	        post.setHeader("Host", "clientsc.sms24hours.com");
	        post.setHeader("Connection", "keep-alive");
	    	//post.setHeader("Content-Length", "2884");
	    	post.setHeader("Cache-Control", "max-age=0");
	    	post.setHeader("Origin", "http://172.105.57.57");
	    	post.setHeader("Upgrade-Insecure-Requests", "1");
	    	post.setHeader("Content-Type", "application/x-www-form-urlencoded");
	    	post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36");
	    	post.setHeader("Accept", "application/json, text/javascript");
	    	post.setHeader("Referer", "http://172.105.57.57/admin/push_smpp_connects/?action=manage");
	    	post.setHeader("Accept-Encoding", "gzip, deflate");
	    	post.setHeader("Accept-Language", "en-US,en;q=0.9");
	    	post.setHeader("Cookie", cookiename);
	    	post.setHeader("X-Requested-With","XMLHttpRequest");
	        post.setEntity(entity);
	        HttpResponse response = client.execute(post);

	        int responseCode = response.getStatusLine().getStatusCode();

//	        System.out.println("\nSending 'POST' request to URL : " + url);
	    //    System.out.println("Post parameters : " + postParams);
	        System.out.println("Response Code : " + response.getEntity().getContentType());
	        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
	        BufferedReader rd = new BufferedReader(
	                new InputStreamReader(response.getEntity().getContent()));

	        StringBuffer result = new StringBuffer();
	        String line = "";
	        while ((line = rd.readLine()) != null) {
	            result.append(line);
	           // System.out.println(line);
	        }
	        System.out.println("44444"+result.toString());

	        data=result.toString();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
		public String vnsApiData(String username,String fromdate,String todate,String cookiename) {
			String data="";
			 try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

		        	HttpPost request = new HttpPost("http://172.105.57.57/user/userReports/?action=deliverySummary");
		        	String urlParameters = "username="+username+"&fromDate="+fromdate+"&toDate="+todate+"&groupBy=summary&search=1";
		        	byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
		        	
					/*
					 * request.setHeader("User-Agent", "Java client");
					 * request.setHeader("User-Agent", "Java client");
					 */
		            //con.setRequestProperty("//POST /user/userReports/?action=deliverySummary HTTP/1.1","");
		        	request.setHeader("Host", "172.105.57.57");
		        	request.setHeader("Connection", "keep-alive");
		        	//request.setHeader("Content-Length", "74");
		        	request.setHeader("Cache-Control", "max-age=0");
		        	request.setHeader("Origin", "http://172.105.57.57");
		        	request.setHeader("Upgrade-Insecure-Requests", "1");
		        	request.setHeader("Content-Type", "application/x-www-form-urlencoded");
		        	request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36");
		        	request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
		        	request.setHeader("Referer", "http://172.105.57.57/user/userReports/?action=deliverySummary");
		        	request.setHeader("Accept-Encoding", "gzip, deflate");
		        	request.setHeader("Accept-Language", "en-US,en;q=0.9");
		        	request.setHeader("Cookie", cookiename);
		           
		        	
		            request.setEntity(new StringEntity(urlParameters));

		            HttpResponse response = client.execute(request);

		            BufferedReader bufReader = new BufferedReader(new InputStreamReader(
		                    response.getEntity().getContent()));

		            StringBuilder builder = new StringBuilder();

		            String line;

		            while ((line = bufReader.readLine()) != null) {

		                builder.append(line);
		                builder.append(System.lineSeparator());
		            }

		     //      System.out.println(builder);
		            /*Document html = Jsoup.parse(builder.toString());
		            String title = html.title();
		            String h1 = html.body().getElementsByTag("h1").text();
		     
		            //System.out.println("Input HTML String to JSoup :" + HTMLSTring);
		            System.out.println("After parsing, Title : " + title);
		            System.out.println("Afte parsing, Heading : " + h1);
		            Element table = html.select("table").get(0); 
		            System.out.println("After table : " + table);*/
		           data=builder.toString();
		        }catch (Exception e) {
					e.printStackTrace();
				}
			return data;
			}
}

