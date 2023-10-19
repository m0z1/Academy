<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<div class="jumbotron jumbotron-fluid">
	<div class="container">
		<h1>팀 홍보 페이지</h1>
	</div>
</div>
<div class="container mt-3">
	<sec:authorize access="isAuthenticated()">
		<a href="/team/teamAd_insert"><button>글쓰기</button></a>
	</sec:authorize>
	<div align="right">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>팀</th>
					<th>제목</th>
					<th>글쓴이(Id)</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ad.content }" var="ad">
					<tr>
						<td><a href = "/team/team_view/${ad.user.team.teamId }">${ad.user.team.teamName }</a></td>
						<td>${ad.boardTitle }</td>
						<td>${ad.user.name }(${ad.user.username })
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


