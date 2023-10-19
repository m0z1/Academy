<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>

<div class="jumbotron jumbotron-fluid">
	<div class="container">
		<h1>${team.teamName }</h1>
	</div>
</div>
<div class="container mt-3">

	<input type="hidden" id="teamid" value="${team.teamId }">
	<div class="form-group">
		<label for="teamname">팀 이름</label> <input type="text"
			class="form-control" id="teamname" value="${team.teamName }"
			name="title" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="category">구분</label> <input type="text"
			class="form-control" id="category" value="${team.teamCategory }"
			name="category" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="level">등급</label> <input type="text"
			class="form-control" id="level" value="${team.teamLevel }"
			name="level" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="num">인원</label> <input type="text" class="form-control"
			id="num" value="${team.teamSize }" name="num" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="memo">Memo</label> <input type="text"
			class="form-control" id="memo" value="${team.teamMemo }" name="memo"
			readonly="readonly">
	</div>

	<sec:authorize access="isAuthenticated()">

		<div class="form-group">
			<a href="/team/team_join/${team.teamId }"><button
					class="btn btn-outline-primary btn-sm" id="btnJoin">팀 가입</button></a> <a
				href="/team/team_out/${team.teamId }"><button
					class="btn btn-outline-danger btn-sm" id="btnout">팀 탈퇴</button></a>
		</div>
		<label for="msg">댓글달기</label>
		<div align="center">
			<textarea class="form-control" rows="5" id="msg"></textarea>
		</div>
		<div>
			<button type="button" class="btn btn-outline-primary btn-sm mt-3"
				id="btnComment">Comment Write</button>
			<hr />
		</div>
		<br />
	</sec:authorize>
	<table class='table table-hover'>
		<thead>
			<tr>
				<th>이용자</th>
				<th>내용</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${clist }" var="c">
				<tr>
					<td>${c.user.name }</td>
					<td>${c.commentComment }</td>
					<td>${c.commentRegdate }</td>
					<c:if test="${principal.user.username == user.username }">
						<td><a href="/team/commentdelete/${c.commentNum }"><button
									type="button" class="btn btn-danger btn-sm">삭제</button></a></td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="container" id="replyResult"></div>
	<br /> <br />



	<script>
		/* $("#btnJoin").click(function(){
			if(!confirm("가입하시겠습니까?")) return;
			$.ajax({
				type : 'post',
				url : '/team/team_join/${team_id}',
				data : 
			})
		}) */
		/* $("#btnJoin").click(function(){
			$.ajax({
				type : 'put',
				url : '/team/team_join',
				contentType : 'application/json;charset=utf-8',
				data : JSON.stringify($("#teamid").val())
			})
			.done(function(resp){
				alert("팀 가입 성공")
				location.href = "/team/team_view"
			})
			.fail(function(e){
				alert("실패")
			})
		}) */

		$("#btnDelete").click(function() {
			if (!confirm('정말 삭제할까요'))
				return;
			$.ajax({
				type : 'DELETE',
				url : '/team/delete/${teamId}'
			}).done(function(resp) {
				alert("삭제성공")
				location.href = '/team/team_list'
			}).fail(function(e) {
				alert("삭제실패")
			})
		})

		$("#btnComment").click(function() {

			if ($("#msg").val() == "") {
				alert("댓글을 적으세요")
				return;
			}
			let data = {
				commentComment : $("#msg").val()
			}
			$.ajax({
				type : 'POST',
				url : '/team/reply/insert/' + $("#teamid").val(),
				contentType : "application/json;charset=utf-8",
				data : JSON.stringify(data)
			}).done(function(resp, status) {
				alert("댓글 추가 성공")
				/* init(); */
			}).fail(function(e) {
				alert("댓글 추가 실패")
			})
		})
		
		$("#btnout").click(function() {
			alert("탈퇴 성공")
		})

		$("#btnJoin").click(function() {
			alert("가입 성공")
		})
	</script>

	<br />
</div>


