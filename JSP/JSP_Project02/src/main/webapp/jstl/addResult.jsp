<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	int num1= Integer.parseInt(request.getParameter("num1"));
   int num2= Integer.parseInt(request.getParameter("num2"));
   int sum = num1+num2;
%>
</head>
<body>
<h3>addResult.jsp</h3>
덧셈 결과  : <%=num1+num2 %><br/>
결과 : <%=sum %><br/>
합계가 짝수인지 홀수인지 출력<br/>
<%
 if(sum % 2 ==0) {
%>	
   <%=sum %> : 짝수
 <%
 }else{
 %>	 
   <%=sum %> : 홀수
<%	 
 }
%>








</body>
</html>