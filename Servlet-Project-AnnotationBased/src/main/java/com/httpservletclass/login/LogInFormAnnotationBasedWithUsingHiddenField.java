package com.httpservletclass.login;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/loginform-with-hiddenform")
public class LogInFormAnnotationBasedWithUsingHiddenField extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String useremail = req.getParameter("email");
		String password = req.getParameter("password");
		
	        resp.setContentType("text/html");
	        PrintWriter pwo = resp.getWriter();
	        pwo.print("<form action='servlet-application-using-hiddenfield'><input type='text' name='Email' value='"+useremail+"'><br><input type='text' name='Password' value='"+password+"'><br><button type='submit'>Go to Visual Service</button></form>");
	        pwo.print("<form action='servlet-application-using-hiddenfield'><input type='hidden' name='Email' value='"+useremail+"'><br><input type='hidden' name='Password' value='"+password+"'><br><button type='submit'>Go to Hidden Service</button></form>");
	}

	

}
