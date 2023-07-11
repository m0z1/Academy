<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>
<div class="container">
	<form action="fileInsert" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="title">title:</label> <input type="text"
				class="form-control" id="title" placeholder="Enter title"
				name="title">

		</div>
		<div class="form-group">
			<label for="writer">writer:</label> <input type="text"
				class="form-control" id="writer"  placeholder="Enter writer"
				name="writer">
		</div>

		<div class="form-group">
			<label for="content">content:</label>
			<textarea class="form-control" rows="5" id="content" name="content"></textarea>
		</div>
		
		<div class="form-group">
			<label for="upload">File:</label> <input type="file"
				class="form-control" id="upload"  
				placeholder="Enter File"
				name="upload" >
		</div>
		<button type="submit" class="btn btn-primary btn-sm">File Submit</button>
	</form>
</div>

<%@ include file="include/footer.jsp"%>
