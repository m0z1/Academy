<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="jumbotron jumbotron-fluid">
	<div class="container">
		<h1>팀 매치 목록 페이지</h1>
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
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${matchList.content}" var="match">
				<tr>
					<td>${match.book.bookStart }: 00 ~ ${match.book.bookEnd } : 00</td>
					<td>${match.book.field.fieldAddress}</td>
					<td>${match.fight}</td>
					<td>${match.book.field.fieldArea }</td>
					<td>${match.book.field.fieldForm}</td>
					<td><a href="/match/match_view/${match.book.bookNum}"><button
								type="button" class="btn btn-primary btn-sm">매치신청</button></a></td>
					<td><a href="/match/match_check/${match.book.bookNum}">
					<button type="button"
								class="btn btn-danger btn-sm ">매치정보 확인</button></a></td>
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
