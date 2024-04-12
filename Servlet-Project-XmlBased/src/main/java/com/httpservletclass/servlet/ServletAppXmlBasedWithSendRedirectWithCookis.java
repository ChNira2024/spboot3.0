package com.httpservletclass.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletAppXmlBasedWithSendRedirectWithCookis extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		//String username = req.getParameter("name");
		 PrintWriter pwo = resp.getWriter();
		 Cookie[] cookies = req.getCookies();
		 
		 resp.setContentType("text/hmtl");
		 pwo.print("From Cookies,Hi Your name is: "+cookies[0].getValue()+ "<br>");
		 pwo.print("From Cookies,Hi Your password is: "+cookies[1].getValue()+ "<br>");
		 pwo.print("<a href=\"loginForCookies.html\">Go to Login For Cookies</a>");
		
	}


}
