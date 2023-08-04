<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<div class="jumbotron jumbotron-fluid">
	<div class="container">
		<h1>구장 상세 페이지</h1>
	</div>
</div>
<div class="container">

	<br>
	<div class="slider">
		<c:forEach items="${imgList }" var="img" varStatus="st">
			<c:if test="${st.first}">
				<input type="radio" name="slide" id="slide1" checked>
			</c:if>
			<c:if test="${st.count>1}">
				<input type="radio" name="slide" id="slide${st.count}">
			</c:if>
		</c:forEach>

		<ul id="imgholder" class="imgs">
			<c:forEach items="${imgList }" var="img">
				<li><img src="${img.imgUrl }" class="img-fluid"></li>
			</c:forEach>
		</ul>

		<div class="bullets">
			<c:forEach items="${imgList }" var="img" varStatus="st">
				<label for="slide${st.count}">&nbsp;</label>
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
			<td colspan="9">${field.fieldAddress}
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
	<input type="hidden" id="fieldNum" value="${field.fieldNum}">
	<input type="hidden" id="fieldUnit" value="${field.fieldUnit}">
	<div class="form-group">
		<a href="/"><button type="button"
				class="btn btn-secondary btn-info" id="writeBtn">구장정보 전체보기</button></a>
	</div>


	<hr>

	<div class="container d-flex flex-row">
		<c:forEach var="i" begin="1" end="24">
			<c:choose>
				<c:when test="${fn:split(field.fieldOpen,':')[0] > i-1}">
					<div class="flex-fill bg-secondary border border-white p-2 "></div>
				</c:when>
				<c:when test="${fn:split(field.fieldClose,':')[0] + 1 > i and fn:split(field.fieldOpen,':')[0]-1 < i}">
					<div class="flex-fill bg-primary border border-white p-2 "></div>
				</c:when>
				<c:otherwise>
					<div class="flex-fill bg-secondary border border-white p-2 "></div>
				</c:otherwise>				
			</c:choose>
		</c:forEach>
	</div>
	<div class="container d-flex justify-content-between">
		<c:forEach var="i" begin="0" end="4">
			<div class="text-secondary">${i*6 }시</div>
		</c:forEach>
	</div>

	<input type="hidden" id="fieldOpen" value="${field.fieldOpen }">
	<input type="hidden" id="fieldClose" value="${field.fieldClose }">


	<!-- 예약 -->
	<div class="d-inline-flex border border-5 rounded p-4 mt-5" >
	<form action="/book/book_insert/${field.fieldNum }" id="frm" name="frm"
		method="post" class="pt-0 mt-0">
		
		<div class="text">	
			<h5><label>예약희망 일시 : </label></h5>
		</div>
		<div class="d-flex justify-content-start">
		
		<div class="input-group-date mr-3">
			<input type="date" class="form-control" id="bookDate" name="bookDate" value="">
		</div>
		
		

		<div id="bookTime">
		<div class="form-group mr-3">

			<select class="form-select" aria-label="Default select example" id="bookStart" name="bookStart">
				<option selected>예약가능시간</option>
				<c:forEach var="i" begin="${fn:split(field.fieldOpen,':')[0]}" end="${fn:split(field.fieldClose,':')[0]-field.fieldUnit}" step="${field.fieldUnit }">
					<c:set var="loop_flag" value="true" />
					<c:if test="${not empty bookList }">
						<c:forEach items="${bookList }" var="book">
							<%-- <c:if test="${fn:split(book.bookDate,'-')[1] == fn:split(selectDate,'-')[1]}">
								<c:if test="${fn:split(book.bookDate,'-')[2] == fn:split(selectDate,'-')[1]}"> --%>
							
							<c:if test="${fn:split(book.bookDate,'-')[1] == dArr[0]}">
								<c:if test="${fn:split(book.bookDate,'-')[2] == dArr2[0]}">
									<c:if test="${book.bookStart == i}">
										<c:set var="loop_flag" value="false" />
									</c:if>
								</c:if>
							</c:if>
						</c:forEach>	
					</c:if>
					<c:if test="${loop_flag }">
							<option value="${i }">${i } : 00 ~ ${i+field.fieldUnit } : 00</option>
					</c:if>
				</c:forEach>
			</select>
		
		</div>
		</div>
		<br>
			<button type="button" id="btnBook" class="btn btn-outline-primary btn-sm mb-3">예약하기</button>
			
		</div>
	</form>
	</div>

</div>
<script>
	
	$(document).ready(function(){
		var today = new Date();
		today = today.toISOString().slice(0, 10);
		document.getElementById("bookDate").value=today;
	})
	
	$("#btnBook").click(function(){
		if($("#bookStart").val()=="예약가능시간"){
			alert("예약시간을 선택해주세요.");
		} else {
		/* 	alert("1") */
			$("#frm").submit();
		}
		
	})
	
	$
	
	
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
