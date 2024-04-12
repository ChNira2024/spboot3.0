package com.servletInterface.login;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login-with-sendredirect")
public class LogInAppAnnotationBasedWithSendRedirect implements Servlet
{

	ServletConfig config;
	RequestDispatcher rd;
	
	@Override
	public void destroy() 
	{
		System.out.println("LoginApplicationAnnotationBased.destroy()");
	}

	@Override
	public ServletConfig getServletConfig() 
	{
		System.out.println("LoginApplicationAnnotationBased.getServletConfig()");
		return config;
	}

	@Override
	public String getServletInfo() 
	{
		System.out.println("LoginApplicationAnnotationBased.getServletInfo()");
		return "This is getServletInfo method";
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException 
	{
		System.out.println("LoginApplicationAnnotationBased.init()");
	}

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException 
	{
		System.out.println("LoginApplicationAnnotationBased.service()");
		String username = req.getParameter("name");
		String password = req.getParameter("password");
		PrintWriter pwo = resp.getWriter();
		 
		 if("niranjana".equalsIgnoreCase(username) && "niranjana".equalsIgnoreCase(password)) 
		 {
			 System.out.println("Valid Credential");
			 HttpServletResponse httpResponse = (HttpServletResponse) resp;
		     httpResponse.sendRedirect("servlet-application-withsr");
		 }
		 else
		 {
			 /*System.out.println("InValid Credential");
			 pwo.print("<h3>InValid Credential</h3>");
			 HttpServletResponse httpResponse = (HttpServletResponse) resp;
		     httpResponse.sendRedirect("loginForSR.html");
		     pwo.flush(); */
			 
			 System.out.println("InValid Credential");
			 pwo.print("<h3>InValid Credential</h3>");
			 rd = req.getRequestDispatcher("/loginForSR.html");
			 rd.include(req, resp);
		 }
		
	}

}
