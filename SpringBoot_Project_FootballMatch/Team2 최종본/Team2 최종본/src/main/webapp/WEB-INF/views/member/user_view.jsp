<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>

<div class="jumbotron jumbotron-fluid">
	<div class="container">
		<h1>회원 : ${user.name }</h1>
	</div>
</div>
<table class="table table-hover">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>팀</th>
			</tr>
		</thead>
		<tbody>
				<tr>
					<td>${user.username}</td>
					<td>${user.name }</td>
					<td>${user.tel}</td>
					<td>${user.team.teamName}</td>
				</tr>
		</tbody>
	</table>
	<br /> <br />
	<div class="form-group">
		<button class="btn btn-secondary btn-sm" id="btnUpdate">수정</button>
		<button class="btn btn-danger btn-sm" id="btnDelete">회원 탈퇴</button>
	</div>
<br />



	<script>
		$("#btnDelete").click(function() {
			if (!confirm('정말 삭제할까요'))
				return;
			$.ajax({
				type : 'DELETE',
				url : '/member/member_delete/{user_id}'
			}).done(function(resp) {
				alert("삭제성공")
				location.href = '/member/memberlist'
			}).fail(function(e) {
				alert("삭제실패")
			})
		})

		$("#btnUpdate").click(function() {
			
				
			let data = {
				user_id : $("#user_id").val(),
				username : $("#username").val(),
				password : $("#password").val(),
				name : $("#name").val(),
				birth : $("#birth").val(),
				tel : $("#tel").val(),
				admin : $("#admin").val()
			}

				if (confirm("정말 수정할까요?"))
				$.ajax({
					type : 'put',
					url : '/member/member_update',
					contentType : 'application/json;charset=utf-8',
					data : JSON.stringify(data)
				}).done(function(resp) {
					alert("수정 완료")
					location.href = "/member/memberlist"
				}).fail(function(e) {
					alert("수정 실패")
				})
		})
	</script>

	


