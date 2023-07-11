<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>
<div class="container">
	<form  id="frm" method="post">
		<div class="form-group">
			<label for="name">Name:</label> <input type="text"
				class="form-control" id="name"  value ="${sMember.name}" name="name">
		</div>
		<div class="row">
			<div class="col">
				<label for="id">UserID:</label> <input type="text"
					class="form-control" id="id" value="${sMember.id}" readonly="readonly"
					name="id" >
			</div>
			<div class="col align-self-end">
			<!-- 	<button type="button" class="btn btn-primary" id="idCheckBtn">중복확인</button> -->
			<span id="idcheck"></span>
			</div>
		</div>
		<div class="form-group">
			<label for="pass">Password:</label> <input type="password"
				class="form-control" id="pass" value="${sMember.pass}"
				name="pass">
		</div>
		<div class="form-group">
			<label for="pass_check">Password_Check:</label> <input type="password"
				class="form-control" id="pass_check"
				placeholder="Enter password_check" name="pass_check">
		</div>
		<div class="form-group">
			<label for="addr">Address:</label> <input type="text"
				class="form-control" id="addr" value="${sMember.addr}"
				name="addr">
		</div>
		<button type="button" class="btn btn-primary" id="btnModify">수정하기</button>
	</form>
</div>
<script>
$("#btnModify").click(function(){
	if($("#pass").val()!=$("#pass_check").val()){
		alert("비번이 맞지 않습니다.");
		$("#pass_check").focus();
		return false;
	}
	let data = {
			id : $("#id").val(),
			name : $("#name").val(),
			pass : $("#pass").val(),
			addr : $("#addr").val()
	}
   $.ajax({
	   type: "put",
	   url: "/member/update",
	   contentType : "application/json;charset=utf-8",
	   data:JSON.stringify(data)
   })
   .done(function(resp){
	   if(resp=="success"){
		   alert("회원변경 성공")
		   location.href="login"
	   }
   })
   .fail(function(e){
	   alert("회원변경실패")
   })
})

</script>
<%@ include file="../include/footer.jsp"%>
