package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InforServlet   extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("utf-8");
			String name= req.getParameter("name");
			String id= req.getParameter("id");
			String pwd= req.getParameter("pwd");
			String phone= req.getParameter("phone");
			String address= req.getParameter("address");
			
//			req.setAttribute("name", name);
//			req.setAttribute("id", id);
//			req.setAttribute("pwd", pwd);
//			req.setAttribute("phone", phone);
//			req.setAttribute("address", address);
			
//			Person person = new Person();
//			person.setAddress(address);
//			person.setId(id);
//			person.setName(name);
//			person.setPhone(phone);
//			person.setPwd(pwd);
			
			Person person = new Person(name, id, pwd, phone, address);
			
			req.setAttribute("person", person);
			
			RequestDispatcher rd = req.getRequestDispatcher("personResult.jsp");
			rd.forward(req, resp);
			
			
			
			
	}

}
