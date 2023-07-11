<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="include/header.jsp"%>
<div class="container">
	<h3>${board.writer }의글보기</h3>
	<div class="form-group">
		<label for="num">글번호:</label> <input type="text" class="form-control"
			id="num" name="num" value="${board.num }" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="title">제목:</label> <input type="text" class="form-control"
			id="title" name="title" value="${board.title }" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="pwd">글쓴이:</label> <input type="text" class="form-control"
			id="writer" name="writer" value="${board.writer }"
			readonly="readonly">
	</div>
	<div class="form-group">
		<label for="content">내용</label>
		<textarea class="form-control" rows="5" id="content" name="content"
			readonly="readonly">${board.content }</textarea>
	</div>
	<c:if test="${sessionScope.sMember.id == board.writer }">
		<div class="form-group text-right">
			<button type="button" class="btn btn-secondary btn-sm" id="btnUpdate">수정</button>
			<button type="button" class="btn btn-danger btn-sm" id="btnDelete">삭제</button>
			<a href="/delete/${board.num }">삭제</a>
		</div>
	</c:if>
	<div class="form-group">
		<label for="msg">msg:</label>
		<textarea class="form-control" rows="3" id="msg" name="msg"></textarea>
	</div>
	<button class="btn btn-success btn-sm" id="commentBtn">Comment
		Write</button>
		<hr/>
	<div id="replyResult"></div>
</div>
<script>
var init = function(){
   $.ajax({
	   type : "get",
	   url : "/reply/commentList/${board.num }"
   }).done(function(resp){
	   let str ="";
	   $.each(resp, function(key,val){
		   str +=val.userid+" "
		   str +=val.content +" "
		   str +=val.regdate+" "
		   if("${sessionScope.sMember.id}"  == val.userid) {
			   str +="<a href='javascript:fdel("+val.cnum+")'>삭제</a>"
		   }
		   str+="<br/>"
		 
	   }) //each
	 
	   $("#replyResult").html(str)
   }).fail(function (){
	   alert("실패")
   }) 
}  //init

function fdel(cnum){
	$.ajax({
		type:'delete',
		url :'/reply/delete/'+cnum,
	})
	.done(function(resp){
		alert(resp + "번 삭제 완료")
		init()
	})
	.fail(function(e){
		alert("삭제 실패 : " + e)
	})
}//fdel

//댓글쓰기
$("#commentBtn").click(function(){
	if(${empty sessionScope.sMember}){
		alert("로그인하세요")
		location.href="/member/login"
		return;
	}
	if($("#msg").val() ==""){
		alert("댓글 입력하세요")
		return;
	}
	let data = {
		 bnum : $("#num").val(),
		 content : $("#msg").val()
	}
	$.ajax({
			type:'post',	
			url: "/reply/commentInsert",
			contentType: "application/json;charset=utf-8",
			data: JSON.stringify(data),
			success:function(resp){
				alert("댓글 추가 성공");
				init();
			},
			error : function(e){
				alert("댓글 추가 실패")
			}
	}) //ajax
})//commentBtn





	//수정폼
	$("#btnUpdate").click(function() {
		if (!confirm('수정할까요?')) //false
			return false;
		location.href = "/update/${board.num}"
	})
	//삭제
	$("#btnDelete").click(function() {
		if (!confirm('정말 삭제할까요?')) //false
			return false;
		$.ajax({
			type : "delete",
			url : "/delete/${board.num}",
			success : function(resp) {
				alert(resp + "번 삭제 성공");
				location.href = "/list"
			},
			error : function(e) {
				alert("삭제 실패 : " + e)
			}
		}) //ajax
	})//btnDelete
	
	init()
</script>
<%@ include file="include/footer.jsp"%>



