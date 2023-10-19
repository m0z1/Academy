<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="jumbotron jumbotron-fluid">
	<div class="container">
		<h1>매치 신청</h1>
	</div>
</div>

<div class="container">
	<h2>구장정보</h2>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>경기장 이름</th>
				<th>경기장 위치</th>
				<th>크기</th>
				<th>경기장 바닥</th>
				<th>특이사항</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${view.field.fieldName }</td>
				<td>${view.field.fieldAddress}</td>
				<td>${view.field.fieldArea}</td>
				<td>${view.field.fieldForm}</td>
				<td>${view.field.fieldMemo }</td>
			</tr>
		</tbody>

	</table>
</div>
<br/>


		
<c:choose>
<c:when test="${user.username != match.user.username}">
<h2 style="text-align: center;">
	<a href="/match/match_apply/${match.matchNum}"><button
			type="button" class="btn btn-primary  btn-lg" id="btn_submit">신청</button>
	</a>
</h2>
</c:when>
<c:when test="${user.username == match.user.username}">
<h2 style="text-align: center;">
	<a href="/match/match_apply2/${match.matchNum}"><button
			type="button" class="btn btn-primary  btn-lg" id="btn_ok">수락</button>
	</a>
</h2>
</c:when>
</c:choose>


<script>
	$("#btn_submit").click(function(resp) {
		alert("신청완료")
	})
	
	$("#btn_ok").click(function(resp) {
		
		alert("수락완료")
		
	})
</script>


<%@ include file="../include/footer.jsp"%>
