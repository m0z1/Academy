<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="jumbotron jumbotron-fluid">
	<div class="container">
		<h1>팀 등록</h1>
	</div>
</div>

<div class="container">
	<!-- <form action = "/team/team_insert" method = "post"> -->

	<div class="form-group">
		<label for="name">팀 이름 :</label> <input type="text"
			class="form-control" id="name" placeholder="Enter 팀이름">
	</div>
	<div class="form-group">
		<label for="category">팀 종류 :</label><br /> <select
			class="form-control" name = "category" id="category">
			<option value = "풋살">풋살</option>
			<option value = "축구">축구</option>
		</select>
	</div>
	<div class="form-group">
		<label for="level">팀 수준 :</label><br /> <select class="form-control"
			name = "level" id="level">
			<option value = "아마추어">아마추어</option>
			<option value = "세미프로">세미프로</option>
			<option value = "프로">프로</option>
		</select>
	</div>
	<div class="form-group">
		<label for="memo">Memo :</label>
		<textarea class="form-control" rows="5" id="memo" name = "memo"></textarea>
	</div>

	<br /> <br />
	<button class="btn btn-primary" id="btnTeam">팀 등록</button>
	<!-- </form> -->
</div>


<script>
	

	$("#btnTeam").click(function() {
		
		let data = {
		teamName : $("#name").val(),
		teamCategory : $("#category").val(),
		teamLevel : $("#level").val(),
		teamMemo : $("#memo").val()
		
	}
		
		$.ajax({
			type : "post",
			url : "/team/team_insert",
			contentType : "application/json;charset=utf-8",
			data : JSON.stringify(data)
		}).done(function(resp) {
			alert("팀이 등록 되었습니다.")
			location.href = '/team/team_list'
		}).fail(function(e) {
			alert("팀 등록에 실패하였습니다.")
		})
	})
</script>