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
<title>Insert title here</title>
<script   src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script>
$(function(){
	$("#loginBtn").click(function(){
		if($("#userid").val()==""){
			alert("아이디를 입력하세요")
			$("#userid"). focus();
			return false;
		}  //userid
		if($("#pwd").val()==""){
			alert("비밀번호를 입력하세요")
			$("#pwd"). focus();
			return false;
		} //pwd
		$.ajax({
			type:"post",
			url:"loginPro.jsp",
			data : {
				userid : $("#userid").val(),
				pwd : $("#pwd").val()
			},
			success:function(resp){
				//일반회원이면 memberView.jsp
				//관리자이면  memberList.jsp
				if(resp.trim()==0){
					alert("일반회원 로그인 성공")
					//location.href="memberView.jsp";
					$(location).attr("href" ,"memberView.jsp");
				}else if(resp.trim()==1){
					alert("관리자 로그인 성공")
					$(location).attr("href" ,"memberList.jsp");
				}else if(resp.trim()==-1){
					alert("회원이 아닙니다. 회원가입하세요")
				}else if(resp.trim()==2){
					alert("비밀번호가 틀립니다. 비밀번호를 확인하세요");
				}
			},
			error :function(e){
				alert(e + ": error");
			}
		}) //ajax
	})  //loginBtn
}) //document



</script>
</head>
<body>
	<div class="container mt-5">
		<div align="right">
		<a href="memberForm.jsp">회원가입</a>
		</div>
		<h2>로그인</h2>
		<form action="" id="frm" method="post">
			<div class="form-group">
				<label for="userid">UserID:</label> <input type="text"
					class="form-control" 
					id="userid" placeholder="Enter UserID" name="userid">
			</div>
			<div class="form-group">
				<label for="pwd">UserID:</label> <input type="password"
					class="form-control" 
					id="pwd" placeholder="Enter Password" name="pwd">
			</div>
			<button type="button" 
			class="btn btn-primary" id="loginBtn">로그인</button>
		</form>
	</div>
</body>
</html>





