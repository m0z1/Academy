<%@page import="com.jqueryAddress.AddressVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jqueryAddress.JAddressDAOImpl"%>
<%@page import="com.jqueryAddress.JAddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String field = request.getParameter("field");
	String word = request.getParameter("word");
	JAddressDAO dao = JAddressDAOImpl.getInstance();
	ArrayList<AddressVO> sarr =  dao.searchList(field, word);
	out.println(sarr.size());
%>