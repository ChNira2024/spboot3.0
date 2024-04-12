package com.httpservletclass.login;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/login-with-sendredirect-cookies")
public class LogInAppAnnotationBasedWithSendRedirectWithCookies extends HttpServlet
{
	RequestDispatcher rd;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String username = req.getParameter("name");
		String password = req.getParameter("password");
		
		Cookie uName = new Cookie("UserName", username);
		Cookie uPassword = new Cookie("UserPassword", password);
		resp.addCookie(uName);
		resp.addCookie(uPassword);
		
		PrintWriter pwo = resp.getWriter();
		
		if("susanta".equalsIgnoreCase(username) && "susanta".equalsIgnoreCase(password)) 
		 {
			 System.out.println("Valid Credential");
			 resp.sendRedirect("servlet-application-withsr-cookie");
		 }
		 else
		 {	 
			 System.out.println("InValid Credential");
			 pwo.print("<h3>InValid Credential</h3>");
			 rd = req.getRequestDispatcher("/loginForCookies.html");
			 rd.include(req, resp);
		 }
		
	}

	

}
