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
 * Servlet implementation class AccesApiEmail
 */
@WebServlet("/AccesApiEmail")
public class AccesApiEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccesApiEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApiEmail apiEmail=new ApiEmail();
		apiEmail.setId(Long.parseLong(request.getParameter("id")));
		apiEmail.setAccess(Long.parseLong(request.getParameter("access")));
		Smpp_DaoImpl daoImpl=new Smpp_DaoImpl();
		int i=daoImpl.accessApiEmail(apiEmail);
		if(i>0) {
			if(apiEmail.getAccess()==0) {
				response.sendRedirect("apiEmail?message=4");
			}else {
				response.sendRedirect("apiEmail?message=5");
			}
			
		}else {
			response.sendRedirect("apiEmail?message=0");
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
