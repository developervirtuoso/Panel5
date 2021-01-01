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
 * Servlet implementation class DeleteVFEmail
 */
@WebServlet("/DeleteVFEmail")
public class DeleteVFEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteVFEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		VFEmails vfEmails=new VFEmails();
		vfEmails.setId(Long.parseLong(request.getParameter("id")));
		Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
		int i=daoImpl.deleteVFEmail(vfEmails);
		if(i>0) {
			response.sendRedirect("ValueFirstEmail.jsp?message=3");
		}else {
			response.sendRedirect("ValueFirstEmail?message=0");
		}	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
