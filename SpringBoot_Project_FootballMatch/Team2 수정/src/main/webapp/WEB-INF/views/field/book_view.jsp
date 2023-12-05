<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="jumbotron jumbotron-fluid">
	<div class="container con">
		<div class="board_title">${book.field.fieldName}</div>
		<h3>예약정보</h3>
	</div>
</div>

<div class="container">
	<br>
	<table class="table table-hover">
		<tr>
			<th>구장</th>
			<td colspan="11">${book.field.fieldName}</td>
		</tr>
		<tr>
			<th>예약 팀 이름</th>
			<td colspan="3">${team.teamName}</td>
			<th>대관번호</th>
			<td colspan="3">${book.bookNum}</td>
		</tr>
		<tr>
			<th>예약일</th>
			<td colspan="3">${book.bookDate}</td>
			<th>예약시간</th>
			<td colspan="6">${book.bookStart} : 00 ~ ${book.bookEnd} : 00 ( ${book.field.fieldUnit }시간 )</td>
		</tr>
	</table>
	<hr>
	<a href="/match/match_insert/${book.bookNum}"><button type="button" class="btn btn-primary">매치 등록</button></a>
</div>


<%@ include file="../include/footer.jsp"%>
