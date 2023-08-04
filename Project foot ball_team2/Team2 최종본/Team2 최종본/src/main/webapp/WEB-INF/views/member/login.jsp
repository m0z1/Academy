<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<meta name="viewport"
	content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">

<div class="container d-flex align-items-center flex-column p-10">
	<br> <br> <br> <br> <br>
	<div
		class="d-flex align-items-center flex-column shadow rounded border border-3 p-5"
		style="width: 300px;">
		<div class="justify-content-center">
			<h1 class="text-primary">로그인</h1>
		</div>
		<div class="dropdown-divider"></div>
		<form action="/member/login" method="post">
			<div class="form-group mb-3">
				<input type="text" class="form-control" id="username1"
					placeholder="아이디" name="username">
			</div>
			<div class="form-group mb-3">
				<input type="password" class="form-control" id="password1"
					placeholder="비밀번호" name="password">
			</div>
			<div class="form-group">
				<div class="form-check">
					<input type="checkbox" class="form-check-input" id="dropdownCheck1">
					<label class="form-check-label" for="dropdownCheck1">
						Remember me </label>
				</div>
			</div>
			<button type="submit"
				class="btn btn-outline-primary btn-sm btn-block" id="btnLogin1">로그인</button>
			<div class="dropdown-divider"></div>
			<button type="button" class="btn btn-outline-info btn-sm btn-block"
				id="btnLogin" onclick="location.href='/member/join'">회원가입</button>
		</form>
	</div>
	<br> <br> <br> <br>
</div>
<script>
	$("#password").on('keypress', function(e) {
		if (e.keyCode == 13) {
			$("#btnLogin1").click();
		}
	})
</script>
<%@ include file="../include/footer.jsp"%>