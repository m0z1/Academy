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
		
		<!--0727 추가  -->
			<div class="form-group">
			<label for="field_address">집 주소</label><br> <input type="text"
				id="postcode" placeholder="우편번호" name="userZipcode" class="d_form"
				readonly="readonly"> <input type="button"
				name="userAddress"  onclick="execDaumPostcode()" value="우편번호 찾기"
				class="d_btn"><br> <input type="text" id="address"
				name="userAddress" placeholder="주소" class="d_form large"
				readonly="readonly"><br> <input type="text"
				id="detailAddress" name="userDetailAddress" placeholder="상세주소"
				class="d_form"> <input type="text" id="extraAddress"
				name="userExtraAddress" placeholder="참고항목" class="d_form">
			<input type="text" id="sigungu" name="userSigungu"
				placeholder="시/군/구" class="d_form">
		</div> 
		<!--0727 추가  -->
		
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
//아이디 중복검사
/* $("#username").on(function(){
	$.ajax({
		type : 'post',
		url : '/user/idcheck',
		data : {username : $("#username").val()},
		success : function(result){
			if(result != 'fail'){
				$('.id_input_re_1').css("display","inline-block");
				$('.id_input_re_2').css("display", "none");				
			} else {
				$('.id_input_re_2').css("display","inline-block");
				$('.id_input_re_1').css("display", "none");				
			}
		}
	})
	

});
 */



	$("#btnJoin1").click(function(){
		if($("#username1").val()==""){
			alert("아이디를 입력해주세요.");
			$("#name").focus();
			return false;
		}
		if($("#password1").val()==""){
			alert("비밀번호를 입력해주세요.");
			$("#password").focus();
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
		if($("#tel").val()==""){
			alert("전화번호를 입력해주세요.");
			$("#tel").focus();
			return false;
		}
		if($("#birth").val()==""){
			alert("생년월일을 입력해주세요.");
			$("#birth").focus();
			return false;
		}
				
		var data = {
				username : $("#username1").val(),
				password : $("#password1").val(),
				name : $("#name").val(),
				tel : $("#tel").val(),
				birth : $("#birth").val(),
				memo : $("#memo").val(),
				/* 7.27 추가 */
				userDetailAddress : $("#detailAddress").val(),
				userExtraAddress : $("#extraAddress").val(),
				userZipcode : $("#postcode").val(),
				userSigungu : $("#sigungu").val()
				/* 7.27 추가 */
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
	/* 7.27 추가 */
	function execDaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {

				var addr = '';
				var extraAddr = ''; 

				if (data.userSelectedType === 'R') { 
					addr = data.roadAddress;
				} else { 
					addr = data.jibunAddress;
				}

				if (data.userSelectedType === 'R') {
					if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
						extraAddr += data.bname;
					}
					if (data.buildingName !== '' && data.apartment === 'Y') {
						extraAddr += (extraAddr !== '' ? ', '
								+ data.buildingName : data.buildingName);
					}
					if (extraAddr !== '') {
						extraAddr = ' (' + extraAddr + ')';
					}
					document.getElementById("extraAddress").value = extraAddr;
					document.getElementById("sigungu").value = data.sigungu;

				} else {
					document.getElementById("extraAddress").value = '';
				}

				document.getElementById("postcode").value = data.zonecode;
				document.getElementById("address").value = addr;
				document.getElementById("detailAddress").focus();
			}
		}).open();
	}
 /* 7.27 추가 */
	
</script>
<%@ include file="../include/footer.jsp"%>
