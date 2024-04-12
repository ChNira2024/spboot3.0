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
@WebServlet("/servlet-application-withhref-using-urlrewritting")
public class ServletAppAnnotationBasedWithSendRedirectUsingURLRewritting extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String usermail = req.getParameter("useremail");
		String userpwd= req.getParameter("password");
		 PrintWriter pwo = resp.getWriter();
		 
        resp.setContentType("text/html");
        pwo.print("From URL Rewritting, Hi Your email is: " + usermail + "<br>");
        pwo.print("From URL Rewritting, Hi Your password is: " + userpwd + "<br>");
	}
}
