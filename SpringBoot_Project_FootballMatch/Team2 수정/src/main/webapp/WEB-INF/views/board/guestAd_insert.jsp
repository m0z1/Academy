<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="jumbotron jumbotron-fluid">
	<div class="container con">
		<div class="board_title">게스트 게시판</div>
		<h3> 게스트 지원 / 게스트 모집 </h3>
	</div>
</div>
<div class="container mt-3">
	<div class="form-group">
		<label for="boardCategory">모집 / 등록</label> <select
			class="form-control" id="category" name="category"
			required="required">
			<option value="용병 등록">게스트 지원</option>
			<option value="용병 모집">게스트 모집</option>
		</select>
	</div>
	<div class="form-group">
		<label for="boardTitle">제목</label> <input type="text"
			class="form-control" id="title" placeholder="Enter title"
			name="title">
	</div>
	<div class="form-group">
		<label for="userid">닉네임</label> <input type="text"
			class="form-control" id="userid"
			value="<sec:authentication property="principal.user.username"/>"
			name="userid" readonly="readonly">
	</div>
	<div class="form-group mt-3">
		<label for="boardContent"></label> <input type="text"
			class="form-control" id="content" placeholder="Enter content"
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
			url : '/board/guestAd_insert',
			contentType : 'application/json;charset=utf-8',
			data : JSON.stringify(data1)
		}).done(function(resp) {
			alert("등록 되었습니다.")
			location.href = "/board/guest";
		}).fail(function(e) {
			alert("등록 실패")
		})
	})
</script>
