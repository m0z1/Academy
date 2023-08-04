<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="jumbotron jumbotron-fluid">
	<div class="container">
		<h1>팀 홍보 페이지</h1>
	</div>
</div>
<div class="container mt-3">
	<div class="d-flex flex-row justify-content-between">
	<sec:authorize access="isAuthenticated()">
		<div>
			<a href="/team/teamAd_insert"><button
					class="btn btn-outline-success btn-sm"><b>글쓰기</b></button></a>
		</div>
		<div class="d-flex flex-row">
			<div class="text-muted mr-3"><b>아직 팀이 없으신가요?</b></div>
			<a href="/team/team_insert">
				<button type="button" class="btn btn-outline-info btn-sm mr-3"><b>팀 만들기</b></button></a>
			<a href="/team/team_list">
				<button type="button" class="btn btn-outline-info btn-sm"><b>팀 가입하기</b></button></a>
		</div>
	</sec:authorize>
	</div>
</div>
<sec:authorize access="isAuthenticated()">

</sec:authorize>

<div class="container mt-3">
	<div align="right">
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">카테고리</th>
					<th scope="col">팀</th>
					<th scope="col">제목</th>
					<th scope="col">글쓴이(Id)</th>
					<th scope="col">About Team</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ad.content }" var="ad">
					<tr>
						<td>${ad.boardCategory }</td>
						<td>${ad.user.team.teamName }</td>
						<td>${ad.boardTitle }</td>
						<td>${ad.user.name }(${ad.user.username })</td>
						<td><a href="/team/team_view/${ad.user.team.teamId }"><button
									type="button" class="btn btn-outline-info btn-sm">알아보기</button></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="d-flex justify-content-between mt-5 mr-auto">
			<ul class="pagination">
				<c:if test="${ad.first == false }">
					<li class="page-item"><a class="page-link"
						href="?page=${ad.number-1 }">이전</a></li>
				</c:if>
				<c:if test="${ad.last == false }">
					<li class="page-item"><a class="page-link"
						href="?page=${ad.number+1 }">다음</a></li>
				</c:if>
			</ul>
		</div>
		<form class="form-inline" action="/board/team" method="get">
			<select name="field" id="field" class="form-control mr-sm-1">
				<option value="team_name">팀</option>
				<option value="title">제목</option>
			</select> <input type="text" name="word" placeholder="Search"
				class="form-control">
			<button class="btn btn-secondary">Search</button>
		</form>

	</div>

</div>
<br>
<br>
<br>
<%@ include file="../include/footer.jsp"%>

