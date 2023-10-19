<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="jumbotron jumbotron-fluid">
	<div class="container">
		<h1>팀 매치 등록 페이지</h1>
	</div>
</div>

<div class="container">

	<form action="/match/match_insert/${book1.bookNum }" method="post">
		<h1 style="text-align: center;">(매치등록)</h1>
		<table class="table table-hover">
			<tbody>
				<tr>
					<td>구장 이름</td>
					<td><input type="text" id="fieldName" name="fieldName"
						value="${field.fieldName }" readonly="readonly"></td>
				</tr>
				<tr>
					<td>구장 형태</td>
					<td><input type="text" id="fieldForm" name="fieldForm"
						value="${field.fieldForm}" readonly="readonly" /></td>
				</tr>

				<tr>
					<td>구장 크기</td>
					<td><input type="text" id="fieldArea" name="fieldArea"
						value="${field.fieldArea }" readonly="readonly" /></td>
				</tr>
				<tr>
					<td>구장주소</td>
					<td>${field.fieldAddress }</td>
				</tr>
				<tr>
					<td>OPEN</td>
					<td><input type="text" id="fieldOpen" name="fieldOpen"
						value="${field.fieldOpen }" readonly="readonly" /></td>
				</tr>
				<tr>
					<td>CLOSE</td>
					<td><input type="text" id="fieldClose" name="fieldClose"
						value="${field.fieldClose }" readonly="readonly"/></td>
				</tr>
			</tbody>
		</table>
		<div class="form-group">
			<label for="admin">Fight</label> <select class="form-control"
				id="fight" name="fight" required="required">
				<optgroup label="fight">
					<option value="5vs5">5vs5</option>
					<option value="6vs6">6vs6</option>
					<option value="8vs8">8vs8</option>
					<option value="11vs11">11vs11</option>
				</optgroup>
			</select>
		</div>
		<h2>예약시간</h2>
		<table class="table table-hover">

			<thead>
				<tr>
					<th>예약시작 시간</th>
					<th>예약종료 시간</th>
				</tr>
			</thead>

			<tbody>
				<tr>
					<td>${book1.bookStart } : 00</td>
					<td>${book1.bookEnd } : 00</td>
				</tr>
			</tbody>

		</table>

		<button type="submit" class="btn btn-primary btn-sm">등록</button>
	</form>
</div>

<%@ include file="../include/footer.jsp"%>
