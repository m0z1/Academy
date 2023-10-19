<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
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
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<link rel="shortcut icon" href="#">
<title>Insert title here</title>
</head>
<body>
	<div class="container mt-3">
		<!-- <form action="/board/notice_insert" method="post"> -->
			<div class="form-group">
				<label for="boardTitle">title:</label> <input type="text"
					class="form-control" id="title" placeholder="Enter title"
					name="title">
			</div>
			<div class="form-group">
				<label for="boardContent">Content:</label> <input type="text"
					class="form-control" id="content" placeholder="Enter content"
					name="content" style="width: 100%; height: 300px;">
			</div>
			<div class="form-group">
				<label for="boardCategory">Category:</label> <input type="text"
					class="form-control" id="category" value = "공지사항"
					name="category">
			</div>
			<br /> <br />
			<button class="btn btn-primary btn-sm" id="sendBtn">글쓰기</button>
		<!-- </form> -->
	</div>
	<script>
	$("#sendBtn").click(function(){
		let data1 = {
				boardTitle : $("#title").val(),
				boardContent : $("#content").val(),
				boardCategory : $("#category").val()
		}
		$.ajax({
			type : 'post',
			url : '/board/notice_insert',
			contentType : 'application/json;charset=utf-8',
			data : JSON.stringify(data1)
		})
		.done(function(resp){
			alert("등록 되었습니다.")
			location.href = "/board/notice";
		})
		.fail(function(e){
			alert("등록 실패")
		})
	})
	
	</script>
</body>
</html>
