package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dao.JavaPostRequest;

import api.daoImpl.Smpp_DaoImpl;

/**
 * Servlet implementation class ApiServlet
 */
@WebServlet("/ApiServlet")
public class ApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void processor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 response.setContentType("text/html");
         PrintWriter out = response.getWriter();
		String apitype=request.getParameter("apitype");
		if(apitype.equalsIgnoreCase("panel5data")) {
			JavaPostRequest javaPostRequest=new JavaPostRequest();
			String username=request.getParameter("username");
			if(username!=null){
				 if(username.equalsIgnoreCase("null")) {
					 username="0";
			 		 }
			  }else{
				  username="0";
				  
			  }
			try {
				
			
			int Requested=0;
			int Delivered=0;
			
			String fromdate=request.getParameter("fromdate");
			String todate=request.getParameter("todate");
			Smpp_DaoImpl smpp_dao = new Smpp_DaoImpl();
			JSONObject jsonObject=new JSONObject();
			JSONObject apijson=new JSONObject();
			smpp_dao.getCookie(jsonObject);
			String cookiename=jsonObject.getString("name");
			String data=javaPostRequest.vnsApiData(username, fromdate, todate,cookiename);
    		if(data.equalsIgnoreCase("")){
    			
    		}else{
    		 Document html = Jsoup.parse(data);
           
             Element table = html.select("table").get(0); 
             Element tbody = table.select("tbody").get(0); 
             Elements rows = tbody.select("tr");
             for (int i = 0; i < rows.size(); i++) { //first row is the col names so skip it.
            	    Element row = rows.get(i);
            	    Elements cols = row.select("td");
    				 Requested=Integer.parseInt(cols.get(1).text().replace(",", ""));
    				 Delivered=Integer.parseInt(cols.get(2).text().replace(",", ""));
    				 
            	}
            
            
    		}
    		apijson.put("Requested", Requested);
    		apijson.put("Delivered", Delivered);
    		out.print(apijson);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processor(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processor(request, response);
	}

}
