<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
%>
<body>
id :  <%=request.getParameter("id") %><br/>
id :  <%=id %><br/>
pwd : <%=pwd %>
</body>
</html>



