<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<div class="jumbotron jumbotron-fluid">
	<div class="container con">
		<div class="board_title">${field.fieldName}</div>
		<h3>구장정보 / 예약</h3>
	</div>
</div>

<div class="container">

	<br>
	<div class="imgView" >
		<div class="selectedImg" style="border: 1px solid #c8c8c8;">
			
			<div class="bigImg">
				<img src="${imgList[0].imgUrl }" id="bigImg">
			</div>
		</div>
		<div class=" d-flex justify-content-center">
			<c:forEach items="${imgList }" var="img" varStatus="st">
				<div class="thumbNail">
					<img src="${img.imgUrl }" class="thumbImg" id="thumbImg${st.count }" onclick="setImgThumbnails('thumbImg${st.count }')">
				</div>
			
			</c:forEach>
		</div>
	</div>
	<table class="table table-hover mt-5">
		<tr>
			<th>구장 이름</th>
			<td colspan="7">${field.fieldName}</td>
			<th>구장 형태</th>
			<td colspan="1">${field.fieldForm}</td>
			<th>구장 크기</th>
			<td colspan="1">${field.fieldArea}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td colspan="9" id="address">${field.fieldAddress}
				${field.fieldDetailAddress} ${field.fieldExtraAddress}</td>
			<th>구장 전화번호</th>
			<td colspan="1">${field.fieldTel}</td>
		</tr>
		<tr>
			<th>개장 시간</th>
			<td colspan="5">${field.fieldOpen}</td>
			<th>폐장 시간</th>
			<td colspan="5">${field.fieldClose}</td>
		</tr>

		<tr>
			<th>알립니다</th>
			<td colspan="11">${field.fieldMemo}</td>
		</tr>
	</table>
	<hr>
	
	<div id="map" style="width:100%; height:300px;"></div>
	<hr>
	
	<input type="hidden" id="fieldNum" value="${field.fieldNum}">
	<input type="hidden" id="fieldUnit" value="${field.fieldUnit}">
	<input type="hidden" id="team" value="${sessionScope.team}">
	
	<!-- 예약 -->
	<div class=" border border-5 rounded p-4" >
	<form action="/book/book_insert/${field.fieldNum }" id="frm" name="frm"
		method="post" class="pt-0 mt-0">
		<div class="d-flex align-items-center">
			<div class="ml-3">
				<b>예약희망 일시</b>  
			</div>
		<div class="ml-3 mr-3">
			<input type="date" class="form-control" id="bookDate" name="bookDate" value="">
		</div>
		
		

		<div id="bookTime">
			<select class="form-select" aria-label="Default select example" id="bookStart" name="bookStart">
				
			</select>
		</div>
		<div class="d-flex flex-column align-items-center">
			<button type="button" id="btnBook" class="btn btn-outline-primary btn-sm ml-3">예약하기</button>
		</div>
		</div>
	</form>
	
	<!-- 예약현황 -->
	<div class="container d-flex flex-row mt-3"  id="bookLayer">
		
	</div>
	<div class="container d-flex justify-content-between pr-0">
		<c:forEach var="i" begin="0" end="4">
			<div class="text-secondary">${i*6 }시</div>
		</c:forEach>
	</div>
	
	<div class="d-flex justify-content-end align-items-center">
		<div class="text-bg-info rounded-1 mr-1" style="width:40px; height:20px"></div> 예약가능
		<div class="rounded-1 ml-2 mr-1" style="background-color:#F06464; width:40px; height:20px"></div> 예약불가
		<div class="text-bg-secondary rounded-1 p-2 ml-2 mr-1" style="width:40px; height:20px"></div> 폐장시간
	</div>
	
	

	<input type="hidden" id="fieldOpen" value="${field.fieldOpen }">
	<input type="hidden" id="fieldClose" value="${field.fieldClose }">
	
	</div>
	<br/>
	
	<div class="form-group">
		<a href="/"><button type="button"
				class="btn btn-secondary btn-info" id="writeBtn">다른구장보기</button></a>
	</div>

</div>
<script>
	var today = new Date();
	var offset = 1000 * 60 * 60 * 9;
	var today_kr = new Date(today.getTime() + offset)
	today_kr = today_kr.toISOString().slice(0, 10);
	var fieldUnit = $("#fieldUnit").val();
	var fieldOpen = $("#fieldOpen").val();
	var fieldClose = $("#fieldClose").val();
	fieldOpen = fieldOpen.split(":")[0];
	fieldClose = fieldClose.split(":")[0];
	var bookList = [];
	
	$(document).ready(function(){
		document.getElementById("bookDate").value=today_kr; 
		getBookList($("#bookDate").val());
		setBookList($("#bookDate").val(),bookList);
		setImgThumbnails("thumbImg1");
	})
	
	
	
	function setImgThumbnails(imgId){
		var selected = document.getElementById(imgId);
		var src = selected.getAttribute("src");
		var thumb = document.getElementsByClassName("thumbImg");
		document.getElementById("bigImg").setAttribute("src", src);
		for(const item of thumb)
			item.setAttribute("style", "border: 5px solid #aaaaaa;")
		
		selected.setAttribute("style", "border: 5px solid #13C7A3;")
	}
	
	const bookDate = document.getElementById("bookDate");
	bookDate.oninput = (e) => {
		console.log("change");
		console.log(today_kr)
		if($("#bookDate").val()<today_kr){
			alert("오늘 이전 일자는 선택 할 수 없습니다.")
			document.getElementById("bookDate").value=today_kr;
			getBookList($("#bookDate").val());
		} else {
			getBookList($("#bookDate").val());
		}
	}
	
	
	
	function getBookList(bookDate){
		
		
		
		
		$.ajax({
				type: "GET",
				url: "http://127.0.0.1:8988/book/list/${field.fieldNum}/" + bookDate,
				dataType:"json",
				success: function (response) {
					
					console.log(response)
					$("#bookLayer").empty();
					
					for(var i = 0; i<24;){
						if(i < fieldOpen){
							$("#bookLayer").append('<div class="flex-fill bg-secondary border border-white p-2 "></div>');
						} else if(i < fieldClose) {
							var flag = true;
							for(const book of response){
								if(i == book){
									$("#bookLayer").append('<div class="flex-fill border border-white p-2 " style="background-color:#F06464">');
									flag = false;
								}
							}
							if(flag)
								$("#bookLayer").append('<div class="flex-fill bg-info border border-white p-2 ">');
						} else {
							$("#bookLayer").append('<div class="flex-fill bg-secondary border border-white p-2 "></div>');
						}
						 i = i+Number(fieldUnit);
					}
					if(response != "")
						bookList = response;
					setBookList($("#bookDate").val(),bookList);
				}
				
		});
		
	}
	
	
	function setBookList(Date, bookList){
		$("#bookStart").empty();
		$("#bookStart").append('<option selected>예약가능시간</option>');
		for(var i = Number(fieldOpen); i < Number(fieldClose);){
			var flag = true;
			if(bookList != ""){
				for(const book of bookList){
					if(i == book){
						flag = false;
					}
				}
			}
			if(flag){
				$("#bookStart").append('<option value="'+i+'">' + i + ' : 00 ~ ' + Number(Number(i)+Number(fieldUnit)) + ' : 00</option>');
			}
			i = i + Number(fieldUnit);
		}
	}
	
	
	$("#btnBook").click(function(){
		if($("#bookStart").val()=="예약가능시간"){
			alert("예약시간을 선택해주세요.");
		} else {
			if("${sessionScope.user}"==""){
				alert("로그인해주세요.")
				location.href = "/member/login"
			}
			else if($("#team").val()==""){
				alert("팀 가입을 먼저 해주세요.")
				location.href = "/board/team"
			}
			else
				$("#frm").submit();
		}
		
	})
	
	var map = new naver.maps.Map("map", {
	    center: new naver.maps.LatLng(37.3595316, 127.1052133),
	    zoom: 15,
    	mapTypeControl: true
	});

	var infoWindow = new naver.maps.InfoWindow({
	    anchorSkew: true
	});
	
	function initGeocoder() {
		var address = $("#address").text()
		console.log(address)
	    searchAddressToCoordinate(address);
	}

	function searchAddressToCoordinate(address) {
		
		$.ajax({
			  type: "GET",
			  url: "http://127.0.0.1:8988/setMap/" + address,
			  dataType:"json",
			  success: function (response) {
				  
				  var htmlAddresses = [],
		              item = response.addresses[0],
		              point = new naver.maps.Point(item.x, item.y);
				  
				  if (item.roadAddress) {
			            htmlAddresses.push('[도로명 주소] ' + item.roadAddress);
			        }

			        if (item.jibunAddress) {
			            htmlAddresses.push('[지번 주소] ' + item.jibunAddress);
			        }

			        if (item.englishAddress) {
			            htmlAddresses.push('[영문명 주소] ' + item.englishAddress);
			        }
				  
				  infoWindow.setContent([
			            '<div style="padding:10px;min-width:200px;line-height:150%;">',
			            address +'<br />'+
			     
			            '</div>'
			        ].join('\n'));
				  
				  map.setCenter(point);
			      infoWindow.open(map, point);
			      
			      console.log("Mapping Success");
			    }
			  
			});
		
		
		
	}
	
	naver.maps.onJSContentLoaded = initGeocoder;
	
	
	/* $("#bookDate").change(function(){
 		let data1 = {
			bookDate : $("#bookDate").val()
		}
		$.ajax({
			type : 'get',
			url : '/book/book_date/' + $("#bookDate").val(),
			contentType : 'application/json;charset=utf-8',
			data : JSON.stringify(data1)
		})
		.done(function(resp){
			alert("변경 되었습니다.")
			//location.href="/field/field_view/"+$("#fieldNum").val();
			$("#bookTime").load(window.location.href + " #bookTime");
		})
		.fail(function(e){
			alert("변경 실패")
		}) */
		
	
</script>

<%@ include file="../include/footer.jsp"%>
