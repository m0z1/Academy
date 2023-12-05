<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="jumbotron jumbotron-fluid">
	<div class="container con">
		<div class="board_title">팀 매치 매칭</div>
		<h3>시합 개최 / 참가 신청</h3>
	</div>
</div>


<div class="container">

	<table class="table table-hover">

		<thead class="">
			<tr>
				<th>시간</th>
				<th>장소</th>
				<th>VS</th>
				<th>구장크기</th>
				<th>구장 바닥</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${matchList.content}" var="match">
			<tr>
					<td>${match.book.bookStart } : 00 ~ ${match.book.bookEnd } : 00</td>
					<td>${match.book.field.fieldAddress}</td>
					<td>${match.fight}</td>
					<td>${match.book.field.fieldArea }</td>
					<td>${match.book.field.fieldForm}</td>
						<sec:authentication property="principal" var="pinfo"/>
	<%-- <sec:authorize access="isAuthenticated()">
		<c:if test="${pinfo.username == board.user.username }"> --%>
					<td><a href="/match/match_view/${match.book.bookNum}"><button type="button" class="btn btn-primary btn-sm">매치신청</button></a></td>
	<%-- 					</c:if>
	</sec:authorize> --%>
			</tr>
				</c:forEach>
		</tbody>

	</table>
			
</div>
<br />
<br />
<br />
<br />
<br />
<br />

<%@ include file="../include/footer.jsp"%>
