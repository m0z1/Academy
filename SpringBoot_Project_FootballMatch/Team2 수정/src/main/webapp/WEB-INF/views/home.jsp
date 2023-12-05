<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>
<div class="jumbotron jumbotron-fluid">
	<div class="container home">
		<div class="home_title">공차자</div>
		<h3>경기장 대관 / 매치 중개 서비스</h3>
	</div>
</div>

<div class="container mt-5">

	<sec:authorize access="hasRole('ROLE_MANAGER')">
		<div>
			<a href="/field/field_insert"><button type="button"
					class="btn btn-outline-primary">구장 등록</button></a>
		</div>
		<br>
	</sec:authorize>
	
	<%-- <div class="d-flex justify-content-between">
		<c:forEach var="i" begin="1" end="14">
			<div class="d-flex items-align-center">
				<div>${dArr[i] }월</div>
				<div>${dArr2[i] }일</div>
				<div>${dArr3[i] }</div>
			</div>
		</c:forEach>	
	</div> --%>
	
	<table class="table talbe-hover">
		<thead>
			<tr>
				<th>지역</th>
				<th>구장이름</th>
				<th>구장형태</th>
				<th>구장크기</th>
				<th>개장시간</th>
				<th>종료시간</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list.content}" var="field">
				<tr>
					<td>${field.fieldSigungu}</td>
					<td>${field.fieldName}</td>
					<td>${field.fieldForm}</td>
					<td>${field.fieldArea}</td>
					<td>${field.fieldOpen}</td>
					<td>${field.fieldClose}</td>
					<td><a href="/field/field_view/${field.fieldNum}">
							<button type="button" class="btn btn-outline-info">대관 정보</button>
					</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="d-flex justify-content-between">

		<ul class="pagination">
			<c:if test="${list.first==false }">
				<li class="page-item"><a class="page-link"
					href="?page=${boards.number-1}">이전</a></li>
			</c:if>
			<c:if test="${list.last==false }">
				<li class="page-item"><a class="page-link"
					href="?page=${list.number+1}">다음</a></li>
			</c:if>
		</ul>
	</div>

	<div class="d-flex justify-content-between">
		<form action="/" name="search" method="get" class="form-inline" >
			<select name="field" class="form-control mr-sm-1">
				<option value="fieldName">구장이름
				<option value="fieldSigungu">지역
			</select> <input type="text" size=16 name="word" class="form-control mr-sm-1" placeholder="Search">
			<button type="submit" class="btn btn-secondary">Search</button>
		</form>
	</div>

</div>

<%@ include file="include/footer.jsp"%>
