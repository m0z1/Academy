<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="jumbotron jumbotron-fluid">
	<div class="container con">
		<div class="board_title">팀 목록</div>
	</div>
</div>
<div class="container">

	<br/>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>팀 이름</th>
				<th>구분</th>
				<th>수준</th>
				<th>인원</th>
				<!-- <th>팀장</th> -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${teams.content}" var="teams" varStatus="st">
				<tr>
					<td><a href = "/team/team_view/${teams.teamId}">${teams.teamName }</a></td>
					<td>${teams.teamCategory }</td>
					<td>${teams.teamLevel}</td>
					<td>${teams.teamSize }</td>
					<%-- <td>${teams.teammanager}</td> --%>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class = "d-flex justify-content-between mt-5 mr-auto">
		<ul class = "pagination">
		<c:if test="${teams.first == false }">
			<li class = "page-item"><a class = "page-link" href = "?page=${teams.number-1 }">이전</a></li>
		</c:if>
		<c:if test="${teams.last == false }">
			<li class = "page-item"><a class = "page-link" href = "?page=${teams.number+1 }">다음</a></li>
		</c:if>
		</ul>
	</div>
	<form class = "form-inline" action = "/team/team_list" method = "get">
		<select name = "field" id = "field" class = "form-control mr-sm-1">
			<option value = "team_name">팀 이름</option>
			<option value = "team_category">팀 종류</option>
			
		</select>
		<input type = "text" name = "word" placeholder="Search" class = "form-control">
		<button class = "btn btn-secondary">Search</button>
	</form>
</div>


<%@ include file="../include/footer.jsp"%>