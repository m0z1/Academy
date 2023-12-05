<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>

<div class="jumbotron jumbotron-fluid">
	<div class="container con">
		<div class="board_title">${member.name }</div>
		<h3>회원정보수정</h3>
	</div>
</div>
<div class="container mt-3">

	<input type="hidden" id="userId" value="${member.userId }">
	<input type="hidden" id="password" value="${member.password }">
	<div class="form-group">
		<label for="username">회원 ID : </label> <input type="text"
			class="form-control" id="username" value="${member.username }"
			name="username">
	</div>
	<div class="form-group">
		<label for="name">회원 이름 : </label> <input type="text"
			class="form-control" id="name" value="${member.name }" name="name">
	</div>
	<div class="form-group">
		<label for="tel">전화번호 :</label> <input type="tel"
			class="form-control" id="tel" value="${member.tel }" name="tel">
	</div>
	<div class="form-group">
		<label for="email">Email :</label> <input type="text"
			class="form-control" id="email" value="${member.email }" name="email"
			readonly="readonly">
	</div>

	<br /> <br />
	<div class="form-group">
		<button class="btn btn-secondary btn-sm" id="btnUpdate">수정</button>
		<button class="btn btn-danger btn-sm" id="btnDelete">탈퇴</button>
	</div>




	<script>
		$("#btnDelete").click(function() {
			if (!confirm('정말 탈퇴할까요'))
				return;
			$.ajax({
				type : 'DELETE',
				url : '/member/member_delete/{user_id}'
			}).done(function(resp) {
				alert("탈퇴되었습니다.")
				location.href = '/'
			}).fail(function(e) {
				alert("탈퇴실패")
			})
		})

		$("#btnUpdate").click(function() {
			
				
			let data = {
				userId : $("#userId").val(),
				username : $("#username").val(),
				password : $("#password").val(),
				name : $("#name").val(),
				tel : $("#tel").val(),
				email : $("#email").val(),
			}

				if (confirm("정말 수정할까요?"))
				$.ajax({
					type : 'put',
					url : '/member/member_update',
					contentType : 'application/json;charset=utf-8',
					data : JSON.stringify(data)
				}).done(function(resp) {
					alert("수정 완료")
					location.href = "/member/user_info/" + $("#email").value()
				}).fail(function(e) {
					alert("수정 실패")
				})
		})
	</script>

	<br />
</div>


