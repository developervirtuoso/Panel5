package com.dao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Servlet implementation class ApiServelets
 */
@WebServlet("/ApiServelets")
public class ApiServelets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApiServelets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String apitype=request.getParameter("apitype");
        PrintWriter out = response.getWriter();

		if(apitype.equalsIgnoreCase("imiTr1_data")) {
			String userid=request.getParameter("userid");
			String password=request.getParameter("password");
			String fromdate=request.getParameter("fromdate");
			String todate=request.getParameter("todate");
			String jsonData="";
			try {
					HttpResponse<JsonNode> response1 = Unirest.get("http://172.105.50.198:5612/SMSApi/report/smsSummary?userid="+userid+"&password="+password+"&fromdate="+fromdate+"&todate="+todate+"&groupby=summary&output=json")
							.asJson();
					jsonData=response1.getBody().toString();
				
				
			} catch (UnirestException e) {
				e.printStackTrace();
			}
			out.println(jsonData);

		}else if(apitype.equalsIgnoreCase("imiTr2_data")) {
			String userid=request.getParameter("userid");
			String password=request.getParameter("password");
			String fromdate=request.getParameter("fromdate");
			String todate=request.getParameter("todate");
			String jsonData="";
			try {
					HttpResponse<JsonNode> response1 = Unirest.get("http://172.105.50.198:5612/SMSApi/report/smsSummary?userid="+userid+"&password="+password+"&fromdate="+fromdate+"&todate="+todate+"&groupby=summary&output=json")
							.asJson();
					jsonData=response1.getBody().toString();
					
				
				
			} catch (UnirestException e) {
				e.printStackTrace();
			}
			out.println(jsonData);
		}else if(apitype.equalsIgnoreCase("imiPr1_data")) {
			System.out.println(apitype);
			String userid=request.getParameter("userid");
			String password=request.getParameter("password");
			String fromdate=request.getParameter("fromdate");
			String todate=request.getParameter("todate");
			System.out.println("userid===="+userid+"==password==="+password+"==fromdate==="+fromdate+"==todate=="+todate);
			String jsonData="";
			try {
					HttpResponse<JsonNode> response1 = Unirest.get("http://172.105.50.198:5612/SMSApi/report/smsSummary?userid="+userid+"&password="+password+"&fromdate="+fromdate+"&todate="+todate+"&groupby=summary&output=json")
							.asJson();
					jsonData=response1.getBody().toString();
				
				
			} catch (UnirestException e) {
				e.printStackTrace();
			}
			out.println(jsonData);

		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
