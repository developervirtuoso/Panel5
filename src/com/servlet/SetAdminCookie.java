package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.daoImpl.Smpp_DaoImpl;

/**
 * Servlet implementation class SetAdminCookie
 */
@WebServlet("/SetAdminCookie")
public class SetAdminCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetAdminCookie() {
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
		int i=daoImpl.insertAdminCookie(cookie);
		if(i>0) {
			response.sendRedirect("pushSmppConnects.jsp?message=1");
		}else {
			response.sendRedirect("pushSmppConnects.jsp?message=0");
		}
	}

}
