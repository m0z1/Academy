<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>
<div class="container">
	<form action="fileAction" method="post" enctype="multipart/form-data">
		<input type="file" class="form-control" name="uploads" /><br /> <input
			type="file" class="form-control" name="uploads" /><br /> <input
			type="file" class="form-control" name="uploads" /><br />
		<br />
		<button class="btn btn-secondary">FileSubmit</button>
	</form>

</div>
<%@ include file="include/footer.jsp"%>