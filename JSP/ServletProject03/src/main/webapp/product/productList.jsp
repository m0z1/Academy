<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<div class="jumbotron jumbotron-fluid">
	<div class="container">
		<h1>상품리스트</h1>
	</div>
</div>
<div class="container">
 <div class="row">
	  <c:forEach items="${parr}" var="item">
	  <div class="card col-4" style="width:400px">
	    <img class="card-img-top" src="../upload/${item.filename }" alt="Card image" 
	     style="width:100px;height:100px">
	    <div class="card-body">
	      <h4 class="card-title">${item.pname }</h4>
	      <p class="card-text">${item.description }</p>
	      <a href="pdetail?productId=${ item.productId}" class="btn btn-primary">상세보기</a>
	    </div>
	  </div>
	</c:forEach>
</div>
 </div>
<%@ include file="../include/footer.jsp"%>
