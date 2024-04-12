package com.httpservletclass.login;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/loginform-with-httpsession")
public class LogInFormAnnotationBasedWithHrefUsingHttpSession extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String useremail = req.getParameter("email");
		String password = req.getParameter("password");
		
	        resp.setContentType("text/html");
	        PrintWriter pwo = resp.getWriter();
	        pwo.print("<h4>useremail="+useremail+"</h4>");
	        pwo.print("<h4>password="+password+"</h4>");
	        
	        HttpSession session = req.getSession();
	        session.setAttribute("Email",useremail);
	        session.setAttribute("Password",password);
	        pwo.print("<a href='servlet-application-withhref-using-httpsession'>Go to HttpSession Service</a>");
	}
}
