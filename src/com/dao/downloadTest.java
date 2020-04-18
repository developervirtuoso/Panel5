package com.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Servlet implementation class downloadTest
 */
@WebServlet("/downloadTest")
public class downloadTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public downloadTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

        	HttpPost req = new HttpPost("http://172.105.57.57/user/deliverySMSReport/?action=manage&search=1&sgcExport=1&fromDate=2020-04-13&toDate=2020-04-13&senderName=TESTSM&mobileNo=&uuId=");
        	String urlParameters = "username=25&fromDate=2020-04-10&toDate=2020-04-10&groupBy=summary&search=1";
        	byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        	
			/*
			 * request.setHeader("User-Agent", "Java client");
			 * request.setHeader("User-Agent", "Java client");
			 */
            //con.setRequestProperty("//POST /user/userReports/?action=deliverySummary HTTP/1.1","");
        	req.setHeader("Host", "172.105.57.57");
        	req.setHeader("Connection", "keep-alive");
        	//request.setHeader("Content-Length", "74");
        	//request.setHeader("Cache-Control", "max-age=0");
        	//request.setHeader("Origin", "http://172.105.57.57");
        	req.setHeader("Upgrade-Insecure-Requests", "1");
        	//request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        	req.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36");
        	req.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        	//request.setHeader("Referer", "http://172.105.57.57/user/userReports/?action=deliverySummary");
        	req.setHeader("Accept-Encoding", "gzip, deflate");
        	req.setHeader("Accept-Language", "en-US,en;q=0.9");
        	req.setHeader("Cookie", "PHPSESSID=oobqtvp5nr576aeiimll8c2nfu; SERVERNAME=s1");
           
        	
        	req.setEntity(new StringEntity(urlParameters));

            HttpResponse resp = client.execute(req);

            BufferedReader bufReader = new BufferedReader(new InputStreamReader(
            		resp.getEntity().getContent()));

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
