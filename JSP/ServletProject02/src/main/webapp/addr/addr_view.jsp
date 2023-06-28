<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>상세보기</h3>
<a href="list.addr">전체보기</a>
<form action="update.addr" method="post">
<input type="hidden" name="num" value="${address.num }" />
<table border="1">
		
		<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="${address.name }"></td>
		</tr>
		<tr>
				<td>우편번호</td>
				<td><input type="text" name="zipcode"  id="zipcode"  size=5  value="${address.zipcode }">
						<input type="button" value="검색" onclick="zipfinder()">
				</td>
		</tr>
		<tr>
				<td>주소</td>
				<td><input type="text" name="addr" id="addr"  size="50" value="${address.addr }"></td>
		</tr>
		<tr>
				<td>전화번호</td>
				<td><input type="text" name="tel" value="${address.tel }"></td>
		</tr>
		<tr>
		<td colspan="2">
				<input type="submit" value="수정">
				<input type="reset" value="취소">
				<button type="button" onclick="location.href='delete.addr?num=${address.num}'">삭제</button> 
				<button type="button" onclick="location.href='list.addr'">전체보기</button>
		</td>
		</tr>
</table>

</form>
</body>
</html>