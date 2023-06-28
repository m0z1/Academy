<%@page import="com.member.dao.MemberDAOImpl"%>
<%@page import="com.member.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
//String sid = (String)session.getAttribute("sUserid ");
%>
<jsp:useBean id="member" class="com.member.dto.Member"></jsp:useBean>
<jsp:setProperty property="*" name="member" />
<%
//member.setUserid(sid);
 MemberDAO dao = MemberDAOImpl.getInstance();
 dao.memberUpdate(member);
 session.invalidate();
 response.sendRedirect("loginForm.jsp");

%>



