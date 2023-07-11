<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<title>Insert title here</title>

</head>
<body>
	<nav class="navbar navbar-expand-sm bg-success navbar-dark mb-3">

		<div class="container">
			<!-- Brand/logo -->
			<a class="navbar-brand" href="/">HOME(Board)</a>

			<!-- Links -->
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="/insert">BoardInsert</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="/list">BoardList</a>
				</li>

			</ul>

			<ul class="navbar-nav">
				<c:choose>
					<c:when test="${empty sessionScope.sMember }">
						<li class="nav-item"><a class="nav-link" href="/login">로그인</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="/join">회원가입</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link" href="/logout">로그아웃(${sessionScope.sMember.name})</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="#">회원변경</a>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>