<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="jumbotron jumbotron-fluid">
	<div class="container con">
		<div class="board_title">팀 게시판</div>
	</div>
</div>
<div class="container mt-3">
	<!-- <form action="/team/teamAd_insert" method="post"> -->
	<div class="form-group">
		<input type="hidden" class="form-control" id="category"
			name="category" value="팀 홍보">
	</div>
	<div class="form-group">
		<label for="boardTitle">제목</label> <input type="text"
			class="form-control" id="title" placeholder="제목을 입력해주세요."
			name="title">
	</div>
	<div class="form-group">
		<label for="userid">팀 네임</label> <input type="text"
			class="form-control" id="userid"
			value="<sec:authentication property="principal.user.team.teamName"/>"
			name="userid" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="boardContent">Content:</label> <input type="text"
			class="form-control" id="content" placeholder="내용을 입력해주세요"
			name="content" style="width: 100%; height: 300px;">
	</div>

	<button class="btn btn-outline-primary btn-sm" id="sendBtn">등록하기</button>
	<!-- </form> -->
</div>
<%@ include file="../include/footer.jsp"%>
<script>
	$("#sendBtn").click(function() {
		let data1 = {
			boardTitle : $("#title").val(),
			boardContent : $("#content").val(),
			boardCategory : $("#category").val()
		}
		$.ajax({
			type : 'post',
			url : '/team/teamAd_insert',
			contentType : 'application/json;charset=utf-8',
			data : JSON.stringify(data1)
		}).done(function(resp) {
			alert("등록 되었습니다.")
			location.href = "/board/team";
		}).fail(function(e) {
			alert("등록 실패")
		})
	})
</script>
