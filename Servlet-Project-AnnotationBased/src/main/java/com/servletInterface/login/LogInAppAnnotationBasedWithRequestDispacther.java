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

@WebServlet("/login-with-requestdispatcher")
public class LogInAppAnnotationBasedWithRequestDispacther implements Servlet
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
		 
		 if("sisu".equalsIgnoreCase(username) && "sisu".equalsIgnoreCase(password)) 
		 {
			 System.out.println("Valid Credential");
			 rd = req.getRequestDispatcher("/servlet-application-withrd");
			 rd.forward(req, resp);
		 }
		 else
		 {
			 System.out.println("InValid Credential");
			 pwo.print("<h3>InValid Credential</h3>");
			 rd = req.getRequestDispatcher("/loginForRD.html");
			 rd.include(req, resp);
		 }
		
	}

}
