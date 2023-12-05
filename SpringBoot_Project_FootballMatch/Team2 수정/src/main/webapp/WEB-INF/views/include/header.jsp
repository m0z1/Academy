<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.util.Date" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<!-- <script type="text/javascript" src="/js/header.js"></script> -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">


<script type="text/javascript" 
	src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=hxbhuy0gcl"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>축구장 예약 서비스</title>

</head>
<body>
	<script
		src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.1/index.global.min.js'></script>

	<nav id="header_nav"
		class="navbar navbar-expand-sm sticky-top bg-white ">
		<div class="container">
			<!-- Brand/logo -->
			<a class="navbar-brand text-info" href="/"><b>HOME</b></a>

			<!-- Links -->
			<ul class="navbar-nav mr-auto" role="tablist">
				<li class="nav-item"><a class="nav-link text-secondary"
					href="/"><b>구장예약</b></a></li>
				<li class="nav-item"><a class="nav-link text-secondary"
					href="/match/match_list"><b>팀 매치</b></a></li>
				<li class="nav-item"><a class="nav-link text-secondary"
					href="/board/team"><b>팀 게시판</b></a></li>
				<li class="nav-item"><a class="nav-link text-secondary"
					href="/board/guest"><b>게스트 모집</b></a></li>

			</ul>
			<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal" var="pinfo" />
			</sec:authorize>

			<!-- 로그인/회원가입 -->
			<ul class="navbar-nav">

				<sec:authorize access="isAnonymous()">
					<div class="dropdown">
						<button type="button" class="btn btn-light text-secondary" id="dropdownMenuClickableInside"
							data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="false" >
							<b>로그인</b>
						</button>
						<div class="dropdown-menu dropdown-menu-end p-5"
							style="width: 300px;" aria-labelledby="dropdownMenuClickableInside">
							<div class="d-flex align-items-center flex-column">
								<h1 class="text-primary">로그인</h1>
							</div>
							<div class="dropdown-divider"></div>
							<form action="/member/login" method="post">
								<div class="form-group">
									<input type="text" class="form-control" name="username"
										 id="username" placeholder="아이디" autocomplete='off'>
								</div>
								<div class="form-group">
									<input type="password" class="form-control" name="password"
										  id="password" placeholder="비밀번호" autocomplete='off'>
										
								</div>
								<div class="form-group">
									<div class="form-check">
										<input type="checkbox" class="form-check-input"
											id="dropdownCheck"> <label class="form-check-label"
											for="dropdownCheck"> Remember me </label>
									</div>
								</div>
								<div class="dropdown-divider"></div>
									<button type="submit"
										class="btn btn-outline-primary btn-sm btn-block" id="btnLogin">로그인</button>
									<div class="dropdown-divider"></div>
									<a href="/oauth2/authorization/google">
										<img src="/localImages/loginGoogle.png" class="oauth2LoginBtn"></a>
									<div class="dropdown-divider"></div>
									<a href="/oauth2/authorization/naver">
										<img src="/localImages/loginNaver.png" class="oauth2LoginBtn"></a>
									<div class="dropdown-divider"></div>
								<button type="button"
									class="btn btn-outline-info btn-sm btn-block" id="btnJoin"
									onclick="location.href='/member/join'">회원가입</button>
							</form>
						</div>
					</div>
				</sec:authorize>
				
				<!-- 로그아웃 -->
				<sec:authorize access="isAuthenticated()">
					<span class="navbar-text text-muted"><b>${sessionScope.user.nickName }</b></span>
					<li class="nav-item"><a class="nav-link text-muted"
						href="/logout"><b>로그아웃</b></a></li>
					<li class="nav-item"></li>
				</sec:authorize>

			</ul>


			<!-- 메뉴 -->
			<div class="dropdown">
				<button type="button" class="btn btn-light text-secondary"
					data-toggle="dropdown">
					<b>메뉴 </b><i class="fa fa-bars"></i>
				</button>
				<div class="dropdown-menu dropdown-menu-right">
					<a class="dropdown-item text-secondary" href="/board/notice"><b>공지사항</b></a>
					<div class="dropdown-divider"></div>
					<!-- 구장 매니저 메뉴 -->
					<sec:authorize access="hasRole('ROLE_MANAGER')">
						<a class="dropdown-item text-secondary" href="/member/manager">구장관리</a>
					</sec:authorize>
					<!-- 회원 메뉴 -->
					<sec:authorize access="isAuthenticated()">
						<a class="dropdown-item text-secondary"
							href="/member/user_info/">마이페이지</a>
					</sec:authorize>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item text-secondary" 
						href="/board/about">사이트 소개</a>
					<!-- 관리자 메뉴 -->
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<div class="dropdown-divider"></div>
						<a class="dropdown-item text-secondary" href="/member/memberlist">회원관리</a>
					</sec:authorize>
				</div>
			</div>
		</div>
	</nav>
	<script>
		$("#btnLogin").click(function(){
			if($("#username").val()==""){
				alert("아이디를 입력해주세요.");
				$("#username").focus();
				return false;
			}
			if($("#password").val()==""){
				alert("비밀번호를 입력해주세요.");
				$("#password").focus();
				return false;
			}
		})
	
	
		$("#password").on('keypress', function(e) {
			if (e.keyCode == 13) {
				if($("#username").val()==""){
					alert("아이디를 입력해주세요.");
					$("#username").focus();
					return false;
				}
				if($("#password").val()==""){
					alert("비밀번호를 입력해주세요.");
					$("#password").focus();
					return false;
				}
				$("#btnLogin").click();
			}
		})
	</script>