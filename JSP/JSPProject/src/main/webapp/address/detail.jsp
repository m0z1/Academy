<%@page import="com.address.Address"%>
<%@page import="com.address.AddressDAO"%>
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
<%
//AddressDAO dao =new AddressDAO();
AddressDAO dao = AddressDAO.getInstance();
int num =Integer.parseInt(request.getParameter("num"));
Address ad = dao.detail(num);
%>
<script>
function del(){
	if(confirm("삭제할까요?")){
		location.href="deletePro.jsp?num=<%=num%>";
	}
}

function zipfinder(){
	window.open("zipCheck.jsp","","width=700 height=400")
}
</script>
<form action="updatePro.jsp" method="post">
<table>
 	<tr>
		<th>번호</th>
		<td><input type="text" name="num" value="<%=ad.getNum() %>" readonly="readonly"></td>
	</tr>
	<tr>
	<th>이름</th>
		<td><input type="text" name="name" value="<%=ad.getName() %>"></td>
	</tr>
		<tr>
		<th>우편번호</th>
		<td><input type="text" name="zipcode" id="zipcode"  value="<%=ad.getZipcode() %>" 
		         readonly="readonly" size="7">
		<input type="button" value="검색"  onclick="zipfinder()"/>
		</td>
	</tr>
		<tr>
		<th>주소</th>
		<td><input type="text" name="addr" id="addr"  value="<%=ad.getAddr()%>" size="50"></td>
	</tr>
		<tr>
		<th>전화번호</th>
		<td><input type="text" name="tel" value="<%=ad.getTel() %>"></td>
	</tr>
	<tr>
		<td colspan="2">
		<input type="submit" value="수정">
		<input type="reset" value="수정취소">
		<input type="button" value="삭제" onclick="del()">
		<input type="button" value="전체보기" 
		      onclick="location.href='list.jsp'">
		
		</td>
	</tr>

</table>

</form>
</body>
</html>






