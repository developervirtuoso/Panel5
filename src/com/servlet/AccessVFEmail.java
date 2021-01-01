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
 * Servlet implementation class AccessVFEmail
 */
@WebServlet("/AccessVFEmail")
public class AccessVFEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccessVFEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		VFEmails vfEmails=new VFEmails();
		vfEmails.setId(Long.parseLong(request.getParameter("id")));
		vfEmails.setAccess(Long.parseLong(request.getParameter("access")));
		Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
		int i=daoImpl.accessVFEmail(vfEmails);
		if(i>0) {
			if(vfEmails.getAccess()==0) {
				response.sendRedirect("ValueFirstEmail.jsp?message=4");
			}else {
				response.sendRedirect("ValueFirstEmail.jsp?message=5");
			}
			
		}else {
			response.sendRedirect("ValueFirstEmail.jsp?message=0");
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
