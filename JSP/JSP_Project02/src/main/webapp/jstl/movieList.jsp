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
<%
	String[] movieList = {"영화1","영화2","영화3","영화4","영화5"};
    pageContext.setAttribute("movies", movieList);
 %>
 <table>
 <tr>
 	<th>index</th>
 	<th>count</th>
 	<th>title</th>
  </tr>
  <c:forEach items="${movies }" var="movie" varStatus="st">
  <tr>
	  	<td>${st.index }</td>
	   <td>${st.count }</td>
	   <td>${movie }</td>
	  </tr>
  </c:forEach>
  </table>
</body>
</html>






