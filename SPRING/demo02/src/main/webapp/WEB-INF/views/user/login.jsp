<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>
<div class="container">
<form action="/loginPro" method="post">
	<div class="form-group">
		<label for="username">ID:</label> <input type="text" class="form-control"
			id="username" placeholder="Enter id" name="username">
	</div>
	<div class="form-group">
		<label for="pass">Password:</label> <input type="password"
			class="form-control" id="pass" placeholder="Enter password"
			name="password">
	</div>
  <button  class="btn btn-primary" >로그인</button>
</form>
</div>
<%@ include file="../include/footer.jsp"%>







