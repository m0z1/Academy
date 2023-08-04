<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="jumbotron jumbotron-fluid">
	<div class="container">
		<h1>게스트 게시판</h1>
	</div>
</div>
<div class="container mt-3">
	<div class="d-flex flex-row justify-content-between">
		<sec:authorize access="isAuthenticated()">
			<div>
				<a href="/board/guestAd_insert"><button
						class="btn btn-outline-success btn-sm">
						<b>글쓰기</b>
					</button></a>
			</div>
			<!-- <div class="d-flex flex-row">
			<div class="text-muted mr-3"><b>아직 팀이 없으신가요?</b></div>
			<a href="/team/team_insert">
				<button type="button" class="btn btn-outline-info btn-sm mr-3"><b>팀 만들기</b></button></a>
			<a href="/team/team_list">
				<button type="button" class="btn btn-outline-info btn-sm"><b>팀 가입하기</b></button></a>
		</div> -->
		</sec:authorize>
	</div>
</div>


<div class="container mt-3" style="text-align: right;">

	<div align="right">

		<table class="table table-hover">
			<thead>
				<tr>
					<th>카테고리</th>
					<th>활동지역</th>
					<th>닉네임</th>
					<th>제목</th>
					<th>Info.</th>
				</tr>
			</thead>

			<tbody>

				<c:forEach items="${ad.content}" var="ad">
					<tr>
						<td>${ad.boardCategory}</td>
						<td>${ad.user.userSigungu }</td>
						<td>${ad.user.username}</td>
						<td>${ad.boardTitle }</td>
						<td><a href="/board/guestCheck/${ad.boardNum }"><button
									type="submit" class="btn btn-outline-primary"
									onclick="zipfinder()">보기</button></a></td>
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

		<form class="form-inline" action="/board/guest" method="get">
			<select name="field" id="field" class="form-control mr-sm-1">
				<option value="userId">아이디</option>
				<option value="title">제목</option>
			</select> <input type="text" name="word" placeholder="Search"
				class="form-control mr-sm-1">
			<button class="btn btn-secondary">Search</button>
		</form>

	</div>
</div>
<br>
<br>
<br>
<!-- <script>
	function zipfinder() {
		window.open("", "", "width=1200 height=900");
	}
</script> -->



<%@ include file="../include/footer.jsp"%>
