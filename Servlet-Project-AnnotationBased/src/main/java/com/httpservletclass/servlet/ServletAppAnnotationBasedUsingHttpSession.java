package com.httpservletclass.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/servlet-application-withhref-using-httpsession")
public class ServletAppAnnotationBasedUsingHttpSession extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		HttpSession session = req.getSession();
		String usermail = (String) session.getAttribute("Email");
		String userpwd= (String) session.getAttribute("Password");
		
		
        resp.setContentType("text/html");
        PrintWriter pwo = resp.getWriter();
        pwo.print("From Http Session, Hi Your email is: " + usermail + "<br>");
        pwo.print("From Http Session, Hi Your password is: " + userpwd + "<br>");
	}
}
