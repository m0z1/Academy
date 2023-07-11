<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="container">
	<h3>Board Insert</h3>
	<form action="insert" method="post">
		<div class="form-group">
			<label for="title">제목 :</label> <input type="text"
				class="form-control" id="title" placeholder="Enter title"
				name="title">
		</div>
		<div class="form-group">
			<label for="writer">작성자:</label> <input type="text"
				class="form-control" id="writer" name="writer" 
				value='<sec:authentication property="principal.user.username"/>'
		  readonly="readonly" />
		</div>
		<div class="form-group">
			<label for="content">내용:</label>
			<textarea class="form-control" rows="5" id="content" name="content"></textarea>
		</div>
		<button type="submit" class="btn btn-primary btn-sm">글쓰기</button>
	</form>
</div>
