<%@page import="com.jqueryAddress.JAddressDAO"%>
<%@page import="com.jqueryAddress.JAddressDAOImpl"%>
<%@page import="com.jqueryAddress.AddressVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
request.setCharacterEncoding("utf-8");    
 AddressVO avo = new AddressVO();
 avo.setAddr(request.getParameter("addr"));
 avo.setName(request.getParameter("name"));
 avo.setTel(request.getParameter("tel"));
 avo.setZipcode(request.getParameter("zipcode"));
 JAddressDAO  dao = JAddressDAOImpl.getInstance();
 dao.insert(avo);
 response.sendRedirect("list.jsp");
  %>
