package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.ApiEmail;
import com.beans.VFEmails;

import api.daoImpl.Smpp_DaoImpl;

/**
 * Servlet implementation class AddVFEmail
 */
@WebServlet("/AddVFEmail")
public class AddVFEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddVFEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		VFEmails vfEmail=new VFEmails();
		vfEmail.setEmail(request.getParameter("email"));
		vfEmail.setType(request.getParameter("type"));
		Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
		int i=daoImpl.addVFEmail(vfEmail);
		if(i>0) {
			response.sendRedirect("ValueFirstEmail.jsp?message=1");
		}else {
			response.sendRedirect("ValueFirstEmail.jsp?message=0");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
