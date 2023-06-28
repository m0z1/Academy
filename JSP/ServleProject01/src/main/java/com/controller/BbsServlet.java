package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BbsServlet  extends HttpServlet{
  @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");
	String name = req.getParameter("name");
   String subject = req.getParameter("subject");
   String memo = req.getParameter("memo");
   resp.setContentType("text/html;charset=utf-8");
   PrintWriter out = resp.getWriter();
	 out.println("<html>");
	 out.println("<head>");
	 out.println("<body>");
	 out.println("<h1>이름  : "+name+"</h1>");
	 out.println("<h1>subject  : "+subject+"</h1>");
	 out.println("<h1>memo  : "+memo+"</h1>");
	 out.println("</body>");
	 out.println("</head>");
	 out.println("</html>");
   
}
}
