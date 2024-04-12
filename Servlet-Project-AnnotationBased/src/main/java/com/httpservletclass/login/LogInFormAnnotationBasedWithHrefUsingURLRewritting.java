package com.httpservletclass.login;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/loginform-with-href-urlrewritting")
public class LogInFormAnnotationBasedWithHrefUsingURLRewritting extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String useremail = req.getParameter("email");
		String password = req.getParameter("password");
		
	        resp.setContentType("text/html");
	        PrintWriter pwo = resp.getWriter();
	        pwo.print("<a href='servlet-application-withhref-using-urlrewritting?useremail="+useremail+"&password=" +password +"'>Go to Service</a>");
	}

	

}
