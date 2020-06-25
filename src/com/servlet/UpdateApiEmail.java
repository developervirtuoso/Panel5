package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.ApiEmail;

import api.daoImpl.Smpp_DaoImpl;

/**
 * Servlet implementation class UpdateApiEmail
 */
@WebServlet("/UpdateApiEmail")
public class UpdateApiEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateApiEmail() {
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
		ApiEmail apiEmail=new ApiEmail();
		apiEmail.setId(Long.parseLong(request.getParameter("id")));
		apiEmail.setEmail(request.getParameter("email"));
		apiEmail.setAccess(Long.parseLong(request.getParameter("access")));
		Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
		int i=daoImpl.updateApiEmail(apiEmail);
		if(i>0) {
			response.sendRedirect("apiEmail?message=2");
		}else {
			response.sendRedirect("apiEmail?message=0");
		}
	}

}
