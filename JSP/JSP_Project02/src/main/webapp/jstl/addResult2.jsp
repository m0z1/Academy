<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>addResult2.jsp</h3>
첫번째수 : ${param.num1 }<br/>
두번째수 : ${param.num2 }<br/>
<hr/>
결과 : ${param.num1 +   param.num2} <br/>
<c:set  var="hap" value="${param.num1 +   param.num2}" />
hap : ${hap } <br/>
hap : <c:out value="${hap }" /><br/>
합계가 짝수인지 홀수인지 출력<br/>
<c:if test="${hap % 2 ==0 }">
${hap } :짝수
</c:if>
<c:if test="${hap % 2 !=0 }">
${hap }  :홀수
</c:if>
<hr/>
<c:choose>
	<c:when test="${hap % 2 ==0}">
			짝수
	</c:when>
	<c:when test="${hap % 3==0}">
	    3의배수
	</c:when>
	<c:otherwise>
		짝수도 3의배수도 아님
	</c:otherwise>
</c:choose>

</body>
</html>









