<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp"%>
<div class="jumbotron jumbotron-fluid">
	<div class="container info">
		<div class="board_title">회원 목록</div>
	</div>
</div>
<br/>
<br/>
<div class="container">
	<table class="table table-hover">
		<thead>
			<tr>
				<th>회원 아이디</th>
				<th>회원 이름</th>
				<th>전화번호</th>
				<th>회원 권한</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${member.content}" var="member" >
				<tr>
					<td><a href = "/member/member_view/${member.userId}">${member.username }</a></td>
					<td>${member.name }</td>
					<td>${member.tel }</td>
					<td>${member.role }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class = "d-flex justify-content-between mt-5 mr-auto">
		<ul class = "pagination">
		<c:if test="${notice.first == false }">
			<li class = "page-item"><a class = "page-link" href = "?page=${notice.number-1 }">이전</a></li>
		</c:if>
		<c:if test="${notice.last == false }">
			<li class = "page-item"><a class = "page-link" href = "?page=${notice.number+1 }">다음</a></li>
		</c:if>
		</ul>
	</div>
</div>
<%@ include file="../include/footer.jsp"%>