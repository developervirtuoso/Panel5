package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.daoImpl.Smpp_DaoImpl;

/**
 * Servlet implementation class SetCookie
 */
@WebServlet("/SetCookie")
public class SetCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetCookie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cookie=request.getParameter("cookie");
		Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
		int i=daoImpl.insertCookie(cookie);
		if(i>0) {
			response.sendRedirect("index.jsp?message=1");
		}else {
			response.sendRedirect("index.jsp?message=0");
		}
	}

}
