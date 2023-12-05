<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<div class="jumbotron jumbotron-fluid">
	<div class="container">
		<h1>회원가입</h1>
	</div>
</div>
<div class="container">
		<div class="form-group">
			<label for="username1">ID:</label> <input type="text" class="form-control" id="username1" placeholder="Enter ID" name="username1" autocomplete='off'>
		</div>
		<!-- <span class="id_input_re_1">사용 가능한 아이디입니다.</span>
		<span class="id_input_re_2">아이디가 이미 존재합니다.</span> -->
		<div class="form-group">
			<label for="password1">Password:</label> <input type="password" class="form-control" id="password1" placeholder="Enter password" name="password1">
		</div>
		<div class="form-group">
			<label for="pass_check">Password_Check:</label> <input type="password" class="form-control" id="pass_check" placeholder="Enter password_check" name="pass_check">
		</div>
		<div class="form-group">
			<label for="name">Name:</label> <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
		</div>
		<div class="form-group">
			<label for="email">Email:</label> <input type="text" class="form-control" oninput="oninputEmail(this)" id = "email" placeholder="Enter Email" name="email">
		</div>
		
		<div class="form-group">
			<label for="tel">Phone:</label> <input type="text" class="form-control" oninput="oninputPhone(this)" id = "tel" placeholder="Enter phone" name="tel">
		</div>
		<div class="form-group">
  			<label for="memo">Memo:</label>
 			<textarea class="form-control" rows="5" id="memo"></textarea>
		</div>
		

		<br /> <br />
		<button type="button" class="btn btn-primary" id="btnJoin1">회원가입</button>
</div>
<script>
function oninputPhone(target) {
    target.value = target.value
        .replace(/[^0-9]/g, '')
        .replace(/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g, "$1-$2-$3");
}


	$("#btnJoin1").click(function(){
		if($("#username1").val()==""){
			alert("아이디를 입력해주세요.");
			$("#username").focus();
			return false;
		}
		if($("#password1").val()==""){
			alert("비밀번호를 입력해주세요.");
			$("#password1").focus();
			return false;
		}
		if($("#password1").val()!=$("#pass_check").val()){
			alert("비밀번호 확인이 실패하였습니다. 다시 입력해주세요.")
			$("#pass_check").focus();
			return false;
		}
		if($("#name").val()==""){
			alert("이름을 입력해주세요.");
			$("#name").focus();
			return false;
		}
		if($("#email").val()==""){
			alert("Email을 입력해주세요.");
			$("#email").focus();
			return false;
		}
		if($("#tel").val()==""){
			alert("전화번호를 입력해주세요.");
			$("#tel").focus();
			return false;
		}
				
		var data = {
				username : $("#username1").val(),
				password : $("#password1").val(),
				name : $("#name").val(),
				tel : $("#tel").val(),
				email : $("#email").val(),
				memo : $("#memo").val(),
		}
		
		$.ajax({
			type:"post",
			url: "/member/join",
			contentType:"application/json;charset=utf-8",
			data : JSON.stringify(data)
		})
		.done(function(resp){
			if(resp=="success"){
				alert("회원가입되었습니다.");
				location.href="/member/login"
			} else if(resp=="fail"){
				alert("이미 존재하는 아이디입니다.");
			}
		})
		.fail(function(e){
			alert("실패")
		})
	})

	
</script>
<%@ include file="../include/footer.jsp"%>
