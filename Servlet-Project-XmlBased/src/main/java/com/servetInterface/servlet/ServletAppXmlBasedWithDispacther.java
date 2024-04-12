package com.servetInterface.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class ServletAppXmlBasedWithDispacther implements Servlet
{

	@Override
	public void destroy() 
	{
		System.out.println("ServletApplicationAnnotationBased.destroy()");
	}

	@Override
	public ServletConfig getServletConfig()
	{
		System.out.println("ServletApplicationAnnotationBased.getServletConfig()");
		return null;
	}

	@Override
	public String getServletInfo() 
	{
		System.out.println("ServletApplicationAnnotationBased.getServletInfo()");
		return null;
	}

	@Override
	public void init(ServletConfig config) throws ServletException 
	{
		System.out.println("ServletApplicationAnnotationBased.init()");
	}

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException 
	{
		System.out.println("ServletApplicationAnnotationBased.service()");
		String username = req.getParameter("name");
		//String password = req.getParameter("password");
		PrintWriter pwo = resp.getWriter();
		pwo.print("From RD,Hi "+username + "<br>");
		pwo.print("<a href=\"loginForRD.html\">Go to Login For RD</a>");
	}

}
