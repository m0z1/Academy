<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%>
<div class="container">
	<h3>${board.writer }의글보기</h3>
	<div class="form-group">
		<label for="num">글번호:</label> <input type="text" class="form-control"
			id="num" name="num" value="${board.num }" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="title">제목:</label> <input type="text" class="form-control"
			id="title" name="title" value="${board.title }" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="pwd">글쓴이:</label> <input type="text" class="form-control"
			id="writer" name="writer" value="${board.writer }"
			readonly="readonly">
	</div>
	<div class="form-group">
		<label for="content">내용</label>
		<textarea class="form-control" rows="5" id="content" name="content"
			readonly="readonly">${board.content }</textarea>
	</div>
	<sec:authorize access="isAuthenticated()">
		<c:if test="${pinfo.username==board.writer }">
			<div class="form-group text-right">
				<button type="button" class="btn btn-secondary btn-sm"
					id="btnUpdate">수정</button>
				<button type="button" class="btn btn-danger btn-sm" id="btnDelete">삭제</button>
				<a href="/delete/${board.num }">삭제</a>
			</div>
		</c:if>
	</sec:authorize>
	<div class="form-group">
		<label for="msg">msg:</label>
		<textarea class="form-control" rows="3" id="msg" name="msg"></textarea>
	</div>
	<button class="btn btn-success btn-sm" id="commentBtn">Comment
		Write</button>
	<hr />
	<div id="replyResult"></div>
</div>

<%@ include file="../include/footer.jsp"%>



