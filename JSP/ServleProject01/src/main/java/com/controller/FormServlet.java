package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormServlet   extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setCharacterEncoding("utf-8");
       String name = req.getParameter("name");
       int age = Integer.parseInt(req.getParameter("age"));
       String gender = req.getParameter("gender");
       String job = req.getParameter("job");
       String[] hobby = req.getParameterValues("hobby");
       
//       Form f = new Form();
//       f.setAge(age);
//       f.setGender(gender);
//       f.setHobby(hobby);
//       f.setJob(job);
//       f.setName(name);
       
       Form f = new Form(name, age, gender, job, hobby);
       
       req.setAttribute("form", f );
//       RequestDispatcher rd = req.getRequestDispatcher("formResult.jsp");
//       rd.forward(req, resp);
       req.getRequestDispatcher("formResult.jsp").forward(req, resp); 
       
	}

}
