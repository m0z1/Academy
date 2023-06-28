<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function check(){
	if(document.getElementById("name").value==""){
		alert("이름 입력")
		return;
	}
	
	//isNaN(A)  ==> A가 문자면  true 아니면  false
	if(document.getElementById("kor").value==""||isNaN(document.getElementById("kor").value)){
		alert("숫자로 국어점수 입력")
		document.getElementById("kor").value="";
		return;
	}
	if(document.getElementById("eng").value==""||isNaN(document.getElementById("eng").value)){
		alert("숫자로 영어점수 입력")
		return;
	}
	if(document.getElementById("math").value==""||isNaN(document.getElementById("math").value)){
		alert("숫자로 수학점수 입력")
		return;
	}
	document.getElementById("frm").submit()
}
</script>
</head>
<body>
<form action="scoreResult.jsp"  id="frm">
이름: <input type="text" name ="name"  id="name"><br/>
국어: <input type="text" name ="kor" id="kor"><br/>
영어:<input type="text" name ="eng" id='eng'><br/>
수학:<input type="text" name ="math" id="math"><br/>
<input type="button" value="button전송" onclick="check()"/>
</form>
</body>
</html>



