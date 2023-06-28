<%@page import="com.jqueryAddress.AddressVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jqueryAddress.JAddressDAO"%>
<%@page import="com.jqueryAddress.JAddressDAOImpl"%>
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
<script   src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script>
$(document).ready(function(){
	$("#btnSearch").click(function(){
		$.ajax({
			type:"get",
			url:"searchProcess.jsp",
			data : {
				field : $("#field").val(),
				word : $("#word").val()
			},
			success:function(resp){
				alert(resp)
			},
			error :function(e){
				alert(e + " : error")
			}
		})  //ajax
	}) //btnSearch
})  //document
</script>
<title>Insert title here</title>
<%
JAddressDAO dao = JAddressDAOImpl.getInstance(); 
ArrayList<AddressVO> arr =    dao.list();

%>
</head>
<body>
<div class="container">
  <h2>전체보기</h2>

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
      for(AddressVO vo : arr){
     %> 	  
    	  <tr>
    	  	<td><%=vo.getNum() %></td>
    	  	<td><%=vo.getName() %></td>
    	  	<td><%=vo.getAddr() %></td>
    		<td><%=vo.getTel() %></td>
    	  </tr>
    <%
      }
    %>
      </tbody>
  </table>
  		<select name="field" id="field">
			<option value="name">이름</option>
			<option value="addr">주소</option>
		</select>
		<input type="text" name="word" id="word">
		<input type="button" value="검색" id="btnSearch">
 </div>






</body>
</html>