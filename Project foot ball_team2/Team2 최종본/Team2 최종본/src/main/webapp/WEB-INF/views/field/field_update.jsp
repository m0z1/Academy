<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<div class="jumbotron jumbotron-fluid">
	<div class="container">
		<h1>구장 정보 수정 페이지</h1>
	</div>
</div>

<div class="container">
	<form action="/field/field_update" id="frm" name="frm" enctype="multipart/form-data" method="post">	
		<input type="hidden" id="fieldNum" name="fieldNum" value="${field.fieldNum }">
		<div class="form-group">
			<label for="fieldName">구장 이름:</label> <input type="text"
				class="form-control" value="${field.fieldName }" id="field_name" placeholder="구장 이름" name="fieldName">
		</div>
		
		<label for="field_form">구장 형태:<br></label>
		<div class="input-group mb-3">
			<div class="custom-control custom-radio custom-control-inline">
			    <input type="radio" class="custom-control-input" id="fildeFormRadio1" name="fieldForm" value="천연 잔디">
			    <label class="custom-control-label" for="fildeFormRadio1"> 천연 잔디</label>
			</div>
		  	<div class="custom-control custom-radio custom-control-inline">
			    <input type="radio" class="custom-control-input" id="fildeFormRadio2" name="fieldForm" value="인조 잔디">
			    <label class="custom-control-label" for="fildeFormRadio2"> 인조 잔디</label>
		 	</div>
		 	<div class="custom-control custom-radio custom-control-inline">
			    <input type="radio" class="custom-control-input" id="fildeFormRadio3" name="fieldForm" value="흙">
			    <label class="custom-control-label" for="fildeFormRadio3"> 흙</label>
		 	</div>
		 	<div class="custom-control custom-radio custom-control-inline">
			    <input type="radio" class="custom-control-input" id="fildeFormRadio4" name="fieldForm" value="풋살">
			    <label class="custom-control-label" for="fildeFormRadio4"> 풋살</label>
		 	</div>
	 	</div>
	 	<input type="hidden" id="fieldForm2" name="fieldForm2" value="${field.fieldForm }">
	 	
	 	<label>구장 크기:</label> 
	 	<div class="input-group mb-3">
			<input type="text"
				class="form-control" id="field_area_x" name="field_area_x" placeholder="가로 (m)">
			<input type="text"
				class="form-control" id="field_area_y" name="field_area_y" placeholder="세로 (m)">
		</div>
		<input type="hidden" id="fieldArea" class="fieldArea" name="fieldArea" value="${field.fieldArea }">
		
		<label>구장 운영시간:</label> 
		<div class="input-group mb-3">
			<input type="text" name="fieldOpen" value="${field.fieldOpen }" placeholder="시간선택"  class="time" required size="8" maxlength="5" autocomplete='off'>
			<input type="text" name="fieldClose" value="${field.fieldClose }" placeholder="시간선택"  class="time" required size="8" maxlength="5" autocomplete='off'>	
		</div>
		
		<div class="form-group">
			<label for="admin">구장 대관 단위시간 : </label> 
			<select class="form-control" id="fieldUnit" name="fieldUnit" required="required">
				<optgroup label="단위시간">
					<!-- <option value="0.5">30분</option> -->
					<option value="1">1시간</option>
					<option value="2">2시간</option>
					<option value="3">3시간</option>
				</optgroup>
			</select>
		</div>
		
	<!-- 	<div class="form-group">
			<label for="field_unit">구장 대관 단위시간:</label> <input type="text"
				class="form-control" id="field_unit" placeholder="구장 대관 단위시간" name="fieldUnit">
		</div> -->
		
		<div class="form-group">
			<label for="field_address">구장 주소:</label><br>
			<input type="text" id="postcode" placeholder="우편번호" name="fieldZipcode" class="d_form" value="${field.fieldZipcode }" readonly="readonly">
			<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기" class="d_btn"><br>
			<input type="text" id="address" name="fieldAddress" placeholder="주소" class="d_form large" value="${field.fieldAddress }" readonly="readonly"><br>
			<input type="text" id="detailAddress" name="fieldDetailAddress" value="${field.fieldDetailAddress}" placeholder="상세주소" class="d_form">
			<input type="text" id="extraAddress" name="fieldExtraAddress" value="${field.fieldExtraAddress }" placeholder="참고항목" class="d_form">
			<input type="text" id="sigungu" name="fieldSigungu" value="${field.fieldSigungu }" placeholder="시/군/구" class="d_form">
		</div>
		
		<p>구장 사진 : </p>
    	<c:if test="${empty list}">
	    	<c:forEach  var="num" begin="1" end="5">
	    		<div class="form-group">
               	<div class="custom-file img-div">
                    <input type="file" class="custom-file-input" name="ImgFile">
                    <label class="custom-file-label">구장이미지${num}</label>
                </div>
                </div>
	    	</c:forEach>
    	</c:if>
		
		<input type="hidden" id="fieldMemo2" class="fieldMemo2" name="fieldMemo2" value="${field.fieldMemo }">
		<div class="form-group">
			<label for="field_memo">메모:</label> <textarea rows="10" cols="20"
				class="form-control" id="fieldMemo" placeholder="구장 소개 및 공지사항"
				name="fieldMemo"></textarea>
		</div><br>
		<hr>

		<br /> <br />
		<!-- <button type="submit">등록</button> -->
		<button type="button" class="btn btn-primary" id="btnFieldInsert">구장정보 수정</button>
	</form>
	<input type="hidden" id="errorMessage" value="${errorMessage }">
</div>
<script>
	// 시간 선택기
	$(".time").timepicker({
		step: 60,            //시간간격 : 5분
		timeFormat: "H:mm",
		startTime: '06:00',//시간:분 으로표시
	});
	
	// 초기화
	$(document).ready(function(){
		
		let fieldForm = $("#fieldForm2").val();
		$('input:radio[name="fieldForm"]:input[value="' + fieldForm + '"]').prop("checked", true);

		var areaStr = $("#fieldArea").val().split("x");
		$('input[type="text"][name="field_area_x"]').val(areaStr[0].trim());
		$('input[type="text"][name="field_area_y"]').val(areaStr[1].trim());
		
		$("#fieldMemo").append($("#fieldMemo2").val());
		
		var errorMessage = null; 
			
		errorMessage = $("#errorMessage").val();
		if(errorMessage != null && errorMessage != ""){
            alert(errorMessage);
        }
        bindDomEvent();

    });

	// 파일 등록
    function bindDomEvent(){
        $(".custom-file-input").on("change", function() {
            var fileName = $(this).val().split("\\").pop();  //이미지 파일명
            var fileExt = fileName.substring(fileName.lastIndexOf(".")+1); // 확장자 추출
            fileExt = fileExt.toLowerCase(); //소문자 변환

            if(fileExt != "jpg" && fileExt != "jpeg" && fileExt != "gif" && fileExt != "png" && fileExt != "bmp" && fileExt != "webp"){
                alert("이미지 파일만 등록이 가능합니다.");
                return;
            }

            $(this).siblings(".custom-file-label").html(fileName);
        });
    }
	
	
	//유효성 검사 및 데이터 필터링
	$("#btnFieldInsert").click(function(){
		if($("#field_name").val()==""){
			alert("구장 이름 입력해주세요.");
			$("#field_name").focus();
			return false;
		}
		if(!$("input[type=radio][name=fieldForm]:checked").is(':checked')){
			alert("구장 형태를 선택해주세요.");
			return false;
		}
		if($("#field_area_x").val()==""){
			alert("구장 가로 길이를 입력해주세요.");
			$("#field_area_x").focus();
			return false;
		}
		if($("#field_area_y").val()==""){
			alert("구장 세로 길이를 입력해주세요.");
			$("#field_area_y").focus();
			return false;
		}
		if ($("#field_open").val() == "") {
			alert("구장 개장시간을 입력해주세요.");
			$("#field_open").focus();
			return false;
		}
		if ($("#field_close").val() == "") {
			alert("구장 폐장시간을 입력해주세요.");
			$("#field_close").focus();
			return false;
		}
		if (!$("#field_close").val() > $("#field_open").val()) {
			alert("구장 폐장시간은 구장 개장시간보다 이후여야 합니다.");
			$("#field_close").focus();
			return false;
		}
		if($("#field_unit").val()==""){
			alert("구장 대관 단위시간을 입력해주세요.");
			$("#field_unit").focus();
			return false;
		}
		if($("#address").val()==""){
			alert("구장 주소를 입력해주세요.");
			$("#field_address").focus();
			return false;
		}

		
		
		$("#field_area").attr("value",$("#field_area_x").val() + " m X " + $("#field_area_y").val() + "m");
		$("#field_address").attr("value",$("#address").val() + " " + $("#detailAddress").val());
		
		if(confirm("정말 수정하시겠습니까?")){
			document.getElementById("frm").submit();	
		} else {
			return false;
		}
		
	})
	
	
	//주소입력
	function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("extraAddress").value = extraAddr;
                    document.getElementById("sigungu").value = data.sigungu;
                
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById("postcode").value = data.zonecode;
                document.getElementById("address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailAddress").focus();
            }
        }).open();
    }
</script>


<%@ include file="../include/footer.jsp"%>
