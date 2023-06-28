<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script   src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script>
$(function(){
	loadData("","");
	$("#send").click(function(){
		var postString = {
				"name" : $("#name").val(),
				"content" : $("#content").val(),
			    "grade" : $("input:radio[name='grade']:checked").val()
		}
		$.ajax({
			type:"post",
			url : "insert",
			contentType: "application/json;charset=utf-8",
			data : JSON.stringify(postString),
			success : function(resp){
				alert(resp);
				loadData("","");
			},
			beforeSend : showRequest,
			error : function(e){
				alert("실패 : " + e)
			}
			
		}) //ajax
	}) //send
	
})// document
//상세보기
 function fview(num){
	$.getJSON("view", 
			           {num : num}, 
			           function(resp){
			        	   var  str =""
			        	   str += "번호  : " + resp.num +"<br/>"
			        	   str += "이름  : " + resp.name +"<br/>"
			        	   str += "내용  : " + resp.content +"<br/>"
			        	   str += "평가  : " + resp.grade +"<br/>"
			        	   str += "작성일  : " + resp.created +"<br/>"
			        	$("#viewDiv").html(str)
	}) // getJSON
}//fview 

//삭제
function fdelete(num){
	$.ajax({
		type: "DELETE",
		url : "delete/"+num,    //delete?num=6 (get방식)
		success : function(resp){
			alert(resp + "삭제 성공")
			loadData("","")
		},
		error:function(e){
			alert("삭제실패 : " + e)
		}
	})//ajax
}  //fdelete

/* function fdelete(num){
	$.ajax({
		type: "post",
		url : "delete",
		data : {num : num},
		success : function(resp){
			alert(resp + "삭제 성공")
			loadData("","")
		},
		error:function(e){
			alert("삭제실패 : " + e)
		}
	})//ajax
}  //fdelete */
function loadData(field, word){
	  $.getJSON(
			 "list",
			  {"field" : field, "word" : word},
			  function(resp){
				  var str =""
				  $.each(resp.arr, function(key,val){
					 str +=val.num +"  "
					 str +="<a href='javascript:fview("+val.num+")'>"+val.name +"</a>   "
					 str +=val.content +"  "
					 str += val.grade +"  "
					 str +="<a href='javascript:fdelete("+val.num+")'>삭제</a><br/>"
				  })//each
				  $("#cnt").text("개수 : " + resp.count)
				 $("#result").html(str);
			  }
	  ) //getJSON
}  //loadData


function showRequest(){
	if($("#name").val()==""){
		alert("글쓴이를 입력하세요")
		$("#name").focus();
		return false;
	}
	if(!$("#content").val()){
		alert("내용을 입력하세요")
		$("#content").focus();
		return false;
	}
	if($("input:radio[name=grade]:checked").length==0){
		alert("평가하세요")
		return false;
	}
	return true;
	
	
}
</script>
</head>
<body>
<form action="insert" method="post" >
<table>
	<tr>
				<td >글쓴이</td>
				<td><input type="text" id="name" name="name"></td>
			</tr>
			<tr>
				<td >내 용</td>
				<td><input type="text" size="80" id="content" name="content">
				</td>
			</tr>
			<tr>
				<td >평가</td>
				<td>
				<input  type="radio" name="grade" value="excellent" checked>아주잘함(excellent)
				<input  type="radio" name="grade" value="good"> 잘함(good) 
				<input 	type="radio" name="grade" value="normal"> 보통(normal) 
				<input 	type="radio" name="grade" value="fail"> 노력(fail)</td>
			</tr>
			<tr>
				<td colspan="2">
				 <input type="button" value="입력" id="send">
				</td>
			</tr>
</table>
</form>
<hr/>
<div id="cnt"></div>
<div id="result"></div>
<hr/>
<div id="viewDiv"></div>

</body>
</html>






