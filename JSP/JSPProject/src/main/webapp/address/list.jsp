<%@page import="com.address.Address"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.address.AddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
   String field ="";
   String word="";
   if(request.getParameter("word")!=null){ //검색
	   field = request.getParameter("field");
   	   word = request.getParameter("word");
   }
   AddressDAO dao = AddressDAO.getInstance();
   ArrayList<Address>arr  = dao.list(field, word);  //
   int count = dao.getCount(field, word);  // 
   
 %>
<body>
<div class="container">
<div align="right">
	<a href="insert.jsp">추가하기</a>
</div>
<h3>전체보기(<%=count%>)</h3>
<table class="table table-hover">
	<thead>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>주소</th>
			<th>전화번호</th>
		</tr>
	</thead>
	<tbody>
	<%
	   for(Address ad  : arr){
	%>	   
		<tr>
				<td><%=ad.getNum()%></td>
				<td><a  href="detail.jsp?num=<%=ad.getNum()%>"><%=ad.getName()%></a></td>
				<td><%=ad.getAddr() %></td>
				<td><%=ad.getTel() %></td>
			</tr>
	<%		
	}
	%>
	
		</tbody>
	</table>
	<form action="list.jsp">
	<select name="field">
		 <option value="name">이름</option>
		<option value="addr">주소</option>
	</select>
	<input type="text" name="word">
	<input type="submit" value="검색">
	</form>
	<hr/>
	<%
	for(int i =0; i<arr.size();i++){
	%>
    	<%=arr.get(i).getNum() %><br/>
		<%=arr.get(i).getName() %><br/>
	   <%=arr.get(i).getAddr() %><br/>
		<%=arr.get(i).getTel()%><br/><br/>
	<% 
	}
	%>
	
	
	
	
	
</div>
</body>
</html>




