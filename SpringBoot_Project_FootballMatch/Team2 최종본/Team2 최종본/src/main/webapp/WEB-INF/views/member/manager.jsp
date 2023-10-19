<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>

<div class="jumbotron jumbotron-fluid">
	<div class="container">
		<h1>${user.name } 구장 매니저님.</h1>
		<h1>반갑습니다.</h1>
	</div>
</div>
<div class="container mt-3">

	<input type="hidden" id="user_id" value="${user.userId }">
	
	<br>
	<h1>등록 구장 목록</h1>
	<br>
	
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
				<th></th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${fieldList}" var="field">
			<tr>
				<td>${field.fieldSigungu}</td>
				<td>${field.fieldName}</td>
				<td>${field.fieldForm}</td>
				<td>${field.fieldArea}</td>
				<td>${field.fieldOpen}</td>
				<td>${field.fieldClose}</td>
				<td><a href="/field/field_view/${field.fieldNum}">
				<button type="button" class="btn btn-info btn-sm">상세</button></a></td>
				<td><button type="button" id="btnUpdate" class="btn btn-primary btn-sm" onclick="location.href='/field/field_update/${field.fieldNum }'">수정</button>   
				<td><button type="button" id="btnDelete" class="btn btn-secondary btn-sm" onclick="btnDelete(${field.fieldNum})">삭제</button><td>
			</tr>	
		</c:forEach>
		</tbody>
	</table>

	<script>
		function btnDelete(fieldNum){

			if(confirm("정말 삭제하겠습니까?")){
				location.href="/field/field_delete/" + fieldNum;
			} else {
				return false;
			}
		}

		$("#btnUpdate").click(function() {
			
				
			let data = {
				user_id : $("#user_id").val(),
				username : $("#username").val(),
				password : $("#password").val(),
				name : $("#name").val(),
				birth : $("#birth").val(),
				tel : $("#tel").val(),
				admin : $("#admin").val()
			}

				if (confirm("정말 수정할까요?"))
				$.ajax({
					type : 'put',
					url : '/member/member_update',
					contentType : 'application/json;charset=utf-8',
					data : JSON.stringify(data)
				}).done(function(resp) {
					alert("수정 완료")
					location.href = "/member/memberlist"
				}).fail(function(e) {
					alert("수정 실패")
				})
		})
	</script>

	<br />
</div>


