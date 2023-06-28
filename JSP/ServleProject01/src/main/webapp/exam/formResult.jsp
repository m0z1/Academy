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
이름 : ${form.name }  ////   <c:out value="${form.name }" />   <br/>
나이 : ${form.age }<br/>
성별 : ${form.gender }<br/>
취미 : ${form.hobby }<br/>
직업 : ${form.job }<br/>
<hr/>
<c:forEach items="${form.hobby }" var="h">
${h }
</c:forEach>

</body>
</html>