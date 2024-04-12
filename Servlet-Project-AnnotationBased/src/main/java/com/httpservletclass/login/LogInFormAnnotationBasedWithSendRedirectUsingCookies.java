package com.httpservletclass.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/loginform-with-sendredirect-cookies")
public class LogInFormAnnotationBasedWithSendRedirectUsingCookies extends HttpServlet
{
	RequestDispatcher rd;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String useremail = req.getParameter("email");
		String password = req.getParameter("password");
		
		// Encode user email and password
		String encodedEmail = Base64.getEncoder().encodeToString(useremail.getBytes());
		String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
		
		//Cookie uEmail = new Cookie("UserEmail", useremail);
		//Cookie uPassword = new Cookie("UserPassword", password);
		Cookie uEmail = new Cookie("UserEmail", encodedEmail);
		Cookie uPassword = new Cookie("UserPassword", encodedPassword);
		resp.addCookie(uEmail);
		resp.addCookie(uPassword);
		
		
		if("sisu@gmail.com".equalsIgnoreCase(useremail) && "sisu".equalsIgnoreCase(password)) 
		 {
			 System.out.println("Valid Credential");
			 resp.sendRedirect("servlet-profile-application-withsr-using-cookie");
		 }
		 else
		 {	 
			 System.out.println("InValid Credential");
		        resp.setContentType("text/html");
		        PrintWriter pwo = resp.getWriter();
		        pwo.print("<h3>InValid Credential</h3>");
		        RequestDispatcher rd = req.getRequestDispatcher("/loginFormUsingCookies.html");
		        rd.include(req, resp);
		 }
		
	}

	

}
