package com.httpservletclass.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/servlet-profile-application-withsr-using-cookie")
public class ServletProfileAppAnnotationBasedWithSendRedirectUsingCookies extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		 PrintWriter pwo = resp.getWriter();
		 
		 Cookie[] cookies = req.getCookies();
		 if (cookies == null) 
		 {
		        resp.sendRedirect("loginFormUsingCookies.html");
		 } 
		 else 
		 {
		        String userEmail = null;
		        String userPassword = null;
		        for (Cookie cookie : cookies) 
		        {
		            if ("UserEmail".equals(cookie.getName())) 
		            {
		            	 String encodedEmail = cookie.getValue();
		                 userEmail = new String(Base64.getDecoder().decode(encodedEmail));
		            } 
		            else if ("UserPassword".equals(cookie.getName())) 
		            {
		            	 String encodedPassword = cookie.getValue();
		                 userPassword = new String(Base64.getDecoder().decode(encodedPassword));
		            }
		       }

		        if (userEmail != null && userPassword != null) 
		        {
		            resp.setContentType("text/html");
		            pwo.print("From Cookies, Hi Your email is: " + userEmail + "<br>");
		            pwo.print("From Cookies, Hi Your password is: " + userPassword + "<br>");
		            pwo.print("<a href='logout-for-destroy-cookies'>Logout</a>");
		        } 
		        else 
		        {
		            resp.sendRedirect("loginFormUsingCookies.html");
		        }
		   }
	}
}
