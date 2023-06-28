<%@page import="com.board.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="board" class="com.board.dto.Board"></jsp:useBean>
<jsp:setProperty property="*" name="board" />
<%
String ip =request. getRemoteAddr();
board.setIp(ip);
 BoardDAO dao = BoardDAO.getInstance();
dao.boardInsert(board);
response.sendRedirect("list.jsp");
%>