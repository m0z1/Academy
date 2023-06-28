<%@page import="com.member.dto.Member"%>
<%@page import="com.member.dao.MemberDAOImpl"%>
<%@page import="com.member.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src ="../js/member.js"></script>
<title>Insert title here</title>
<%
MemberDAO dao = MemberDAOImpl.getInstance();
String sid = (String)session.getAttribute("sUserid");
Member member =  dao.findById(sid);
%>
</head>
<body>
	<div class="container mt-5">
	<div align="right">
	<a href="memberView.jsp"><%=sid %></a>님 반갑습니다. 
	/ <a href="logout.jsp">로그아웃</a>
	/<a href="../board/list.jsp">게시판으로(상대경로)</a>
</div>
		<h2>회원 정보 수정</h2>
		<form action="memberUpdatePro.jsp" id="frm" method="post">
	 		<input type="hidden" name="userid" value="<%=sid %>" /> 
			<div class="form-group">
				<label for="name">Name:</label> 
				<input type="text"
					class="form-control" id="name" value="<%=member.getName() %>" name="name">
			</div>
	
			<div class="form-group">
				<label for="pwd">Password:</label>
				 <input type="password"
					class="form-control" id="pwd" value="<%=member.getPwd() %>"
					name="pwd">
			</div>
			<div class="form-group">
				<label for="pwd_check">Password_Check:</label> 
				<input  	type="password" class="form-control" id="pwd_check"
					placeholder="Enter password_check" name="pwd_check">
			</div>
			<div class="form-group">
				<label for="email">Email:</label> 
				<input type="email"	class="form-control" id="email" value="<%=member.getEmail() %>"
					name="email">
			</div>
			<div class="form-group">
				<label for="phone">Phone:</label> 
				<input type="text" class="form-control" id="phone"  value="<%=member.getPhone() %>"
					name="phone">
			</div>

			<div class="form-group form-check-inline">
				<label class="form-check-label"> 
				<input  class="form-check-input" type="radio" name="admin" value="0"
					> 일반회원
				</label>
			</div>
			<div class="form-group form-check-inline">
				<label class="form-check-label"> 
				<input class="form-check-input" type="radio" name="admin" value="1">
					관리자
				</label>
			</div>
			<script>
			<%-- 
			if(document.getElementsByName('admin')[0].value == <%=member.getAdmin() %>){
				document.getElementsByName('admin')[0].checked= true;
			}else{
				document.getElementsByName('admin')[1].checked= true;
			} 
			--%>
	     <%-- 	
		if(<%=member.getAdmin()%>==0){
				$("input:radio[value=0]").attr("checked",true);
			}else{
				$("input:radio[value=1]").attr("checked",true);
			} 
			--%>
	 $("input:radio[value=<%=member.getAdmin()%>]").attr("checked",true);
			</script>
			<br/> <br/>
			<button type="button" class="btn btn-primary" id="sendBtn">수정하기</button>
			<button type="reset" class="btn btn-secondary">취소하기</button>
			<button type="button" class="btn btn-danger" 
			onclick="location.href='userDeletePro.jsp'">탈퇴하기</button>
		</form>
	</div>
</body>
</html>




