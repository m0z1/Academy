<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>

<div class="jumbotron jumbotron-fluid">
	<div class="container con">
		<div class="board_title">${user.name } 님</div>
		<h3>반갑습니다</h3>
	</div>
</div>
<br/>
<div class="board_info">
<div class="container">
	<h2>회원 정보</h2>
	<table class="table table-bordered table-hover">
		<tbody>
			<tr>
				<th>아이디</th>
				<td colspan="11">${user.username}</td>
			</tr>
			<tr>
				<th colspan="1">이름</th>
				<td colspan="11">${user.name }</td>
			</tr>
			<tr>
				<th colspan="1">전화번호</th>
				<td colspan="11">${user.tel}</td>
			</tr>
			<tr>
				<th colspan="1">팀명</th>
				<c:choose>
					<c:when test="${!empty user.team.teamName }">
						<td colspan="11">${user.team.teamName}</td>
					</c:when>
					<c:when test="${empty user.team.teamName }">
						<td colspan="11">용병</td>
					</c:when>
				</c:choose>
			</tr>
		</tbody>
	</table>
	<div class="form-group">
		<button class="btn btn-secondary btn-sm" id="btnUpdate">수정</button>
	</div>
	<br /> <br />
</div>

<c:if test="${!empty user.team}">
	<div class="container">
		<h2>예약 정보</h2>

		<c:forEach items="${booklists}" var="b">
			<div class="border-top border-dark">
				<table class="table table-hover">
					<tbody>
						<tr>
							<th>구장 이름</th>
							<td><a href="/field/field_view/${b.field.fieldNum }">${b.field.fieldName }</a></td>
						</tr>
						<tr>
							<th>주소</th>
							<td>${b.field.fieldAddress }</td>
						</tr>
						<tr>
							<th>예약 일</th>
							<td><a href="/book/book_view/${b.bookNum }">${b.bookDate }</a></td>
						</tr>
						<tr>
							<th>예약 시간</th>
							<td>${b.bookStart }:00 ~ ${b.bookEnd } : 00</td>
						</tr>
					</tbody>
				</table>
			</div>
			<a href="/match/match_insert/${b.bookNum }"><button type="button" class="btn btn-outline-success mb-5">매치 개최하기</button></a>
		</c:forEach>


	</div>

	<br>
	<br>
	<div class="container" id=memberDiv>
		<h2>매치 개최 현황</h2>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>시간</th>
					<th>장소</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${matchlists }" var="m">
					<tr>
						<td>${m.book.bookStart }</td>
						<td><a href="/field/field_view/${m.book.field.fieldNum }">${m.book.field.fieldAddress }</a></td>
						<c:choose>
							<c:when test="${m.flag == 0 }">
								<td><button type="button" class="btn btn-secondary">신청
										대기중</button></td>
							</c:when>
							<c:when test="${m.flag == 1 }">
								<td><button type="button" class="btn btn-primary"
										data-num="${m.matchNum }" id="btnAccept">수락 대기중</button></td>
							</c:when>
							<c:when test="${m.flag == 2 }">
								<td><button type="button" class="btn btn-success">수락함</button></td>
							</c:when>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<br>
	<br>
	<div class="container">
		<h2>매치 신청 현황</h2>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>시간</th>
					<th>장소</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${mlists }" var="ml">
					<tr>
						<td>${ml.book.bookStart }:00 ~ ${ml.book.bookEnd } : 00</td>
						<td><a href="/field/field_view/${ml.book.field.fieldNum }">${ml.book.field.fieldAddress }</a></td>
						<c:choose>
							<c:when test="${ml.flag == 1 }">
								<td><button type="button" class="btn btn-primary"
										data-num="${ml.matchNum }">수락 대기중</button></td>
							</c:when>
							<c:when test="${ml.flag == 2 }">
								<td><button type="button" class="btn btn-success">수락됨</button></td>
							</c:when>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</c:if>
</div>


<br />

<%@ include file="../include/footer.jsp"%>

<script>
	$("#btnUpdate").click(function() {
		location.href = "/member/member_update/${user.userId}"
	})

	$(document)
			.ready(
					function() {
						// 버튼을 클릭했을 때 처리할 이벤트를 등록합니다.
						$('#memberDiv')
								.on(
										'click',
										'#btnAccept',
										function() {
											var btn = $(this); // 클릭한 버튼을 변수에 저장

											// 서버로 Ajax 요청을 보냅니다.
											$
													.ajax({
														type : 'get',
														url : '/match/match_apply2/'
																+ btn
																		.data('num'),
														success : function(resp) {
															alert('수락 하였습니다.');

															// 버튼을 "수락됨" 버튼으로 변경합니다.
															btn
																	.replaceWith('<button type="button" class="btn btn-success">수락됨</button>');
														},
														error : function(e) {
															alert('실패');
														}
													});
										});
					});
</script>




