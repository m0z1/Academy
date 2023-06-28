<%@page import="com.address.AddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="ad" class="com.address.Address"></jsp:useBean>
<jsp:setProperty property="*" name="ad" />
<% 
 AddressDAO dao = AddressDAO.getInstance();
dao.update(ad);  //5개 값 전달 
response.sendRedirect("list.jsp");

%>







