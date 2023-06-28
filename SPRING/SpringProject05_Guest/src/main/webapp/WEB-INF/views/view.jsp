<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>Guest 상세보기</h2>
	이름 : ${guests.name }<br/>
	내용 :${guests.content } <br/>
	등급 : ${guests.grade }<br/>
	작성일  : ${guests.created }<br/>
	IP : ${guests.ip }<br/>
	<br/><br/>
</body>
</html>




