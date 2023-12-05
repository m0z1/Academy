<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="jumbotron jumbotron-fluid">
	<div class="container con">
		<div class="board_title">공지사항</div>
	</div>
</div>
<div class="container">
	<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href = "/board/notice_insert"><button class = "btn btn-outline-primary">글쓰기</button></a>
	</sec:authorize>
	<table class="table table-hover">
		<tbody>
			<c:forEach items="${notice.content}" var="notice" varStatus="st">
				<tr>
					<td>${notice.boardContent }</td>
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