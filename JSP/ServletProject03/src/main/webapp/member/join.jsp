<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>
<script src ="../js/member.js" ></script>
<div class="jumbotron jumbotron-fluid">
	<div class="container">
		<h1>JOIN</h1>
	</div>
</div>
<div class="container">
	<form action="join" id="frm" method="post">
		<div class="form-group">
			<label for="name">Name:</label> <input type="text"
				class="form-control" id="name" placeholder="Enter Name" name="name">
		</div>
		<div class="row">
			<div class="col">
				<label for="userid">UserID:</label> <input type="text"
					class="form-control" id="userid" placeholder="Enter UserID"
					name="userid" >
			</div>
			<div class="col align-self-end">
				<button type="button" class="btn btn-primary" id="idCheckBtn">중복확인</button>
			</div>
		</div>
		<div class="form-group">
			<label for="pwd">Password:</label> <input type="password"
				class="form-control" id="pwd" placeholder="Enter password"
				name="pwd">
		</div>
		<div class="form-group">
			<label for="pwd_check">Password_Check:</label> <input type="password"
				class="form-control" id="pwd_check"
				placeholder="Enter password_check" name="pwd_check">
		</div>
		<div class="form-group">
			<label for="email">Email:</label> <input type="email"
				class="form-control" id="email" placeholder="Enter email"
				name="email">
		</div>
		<div class="form-group">
			<label for="phone">Phone:</label> <input type="text"
				class="form-control" id="phone" placeholder="Enter phone"
				name="phone">
		</div>

		<div class="form-group form-check-inline">
			<label class="form-check-label"> <input
				class="form-check-input" type="radio" name="admin" value="0" checked>
				일반회원
			</label>
		</div>
		<div class="form-group form-check-inline">
			<label class="form-check-label"> <input
				class="form-check-input" type="radio" name="admin" value="1">
				관리자
			</label>
		</div>
		<br /> <br />
		<button type="button" class="btn btn-primary" id="sendBtn">Submit</button>
	</form>
</div>
<%@ include file="../include/footer.jsp"%>
