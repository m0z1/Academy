<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<br>
<br>
<br>
<div class="container">
	<div class="card">
		<div class="card-header">
			<p>
				<b>FootballTeam1</b>
			</p>
		</div>
		<div class="card-body">
			<h5 class="card-title">Home</h5>
			<p class="card-text">${match.matchHome1}</p>
			<!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
		</div>
	</div>
</div>
<br>
<br>
<br>
<div class="container">
	<div class="card">
		<div class="card-header">
			<p>
				<b>FootballTeam2</b>
			</p>
		</div>
		<div class="card-body">
			<h5 class="card-title">AWAY1</h5>
			<p class="card-text">${match.matchAway1}</p>
			<!--    <a href="#" class="btn btn-primary">Go somewhere</a> -->
		</div>
	</div>
</div>
<br>
<br>
<br>
<div class="container">
	<div class="card">
		<div class="card-header">
			<p>
				<b>FootballTeam3</b>
			</p>
		</div>
		<div class="card-body">
			<h5 class="card-title">AWAY2</h5>
			<p class="card-text">${match.matchAway2}</p>
			<!--  <a href="#" class="btn btn-primary">Go somewhere</a> -->
		</div>
	</div>
	<br/>
	<div class="container" style="text-align: center;">
		<a href="/match/match_list"><button type="button"
				class="btn btn-success btn-lg">확인</button></a>
	</div>
</div>
























<%@ include file="../include/footer.jsp"%>