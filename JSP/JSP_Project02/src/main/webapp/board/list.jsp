<%@page import="java.util.ArrayList"%>
<%@page import="com.board.dao.BoardDAO"%>
<%@page import="com.board.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List</title>
<%
	BoardDAO dao = BoardDAO.getInstance();
	String pageNum = request.getParameter("pageNum");
	if(pageNum ==null){
		pageNum ="1";
	}
	String field ="";
	String word="";
	if(request.getParameter("word")!=null){
		field = request.getParameter("field");
		word = request.getParameter("word");
	}
	int pageSize = 5;
	int currentPage = Integer.parseInt(pageNum);  //현재페이지
	int startRow = (currentPage-1)*pageSize+1;  // 1 6 11
	int endRow = currentPage*pageSize;               //5  10  15
	ArrayList<Board> arr =dao.boardList(field, word, startRow,endRow);
	int count = dao.getCount(field, word);

%>

</head>
<body>
<div align="right">
	<a href="writeForm.jsp">글쓰기</a>
</div>
<h2>게시글 목록(<%=count %>)</h2>
<table border=1>
	<thead>
	  <tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>	
 </thead>
 <tbody>
 <%
 	for(Board board:  arr){
 %>		
 		<tr>
	 		<td><%=board.getNum() %></td>
	 		<td><a href="boardView.jsp?num=<%=board.getNum() %>"><%=board.getSubject() %></a></td>
	 		<td><%=board.getWriter() %></td>
	 		<td><%=board.getReg_date() %></td>
	 		<td><%=board.getReadcount()%></td>
  		</tr>
<% 		
 	}
  %>
  </tbody>
</table>
<br/><br/>
 <form action="list.jsp" name="search" method="get">
   	<select name="field" >
    		<option value="subject"> 제 목
    		<option value="writer"> 작성자
    </select>
  	 <input type="text" size=16 name="word">
   	<input type="submit"  value="찾기">
</form>
<div>
<%
if(count > 0){//게시글 38개 , 한 화면 5개 ==>  38/5 == 7 + 1(3개처리)  ==>8
    int pageCount = count/pageSize + (count%pageSize == 0 ? 0 : 1) ;
    int pageBlock= 3;
    int startPage = (int)((currentPage-1)/pageBlock) * pageBlock +1;  // 현재 7 : ((7-1)/3)*3+1 ==>7
    int endPage =  startPage+pageBlock-1;  //    9(허수 : 잘못된 값)==> 8페이지까지 존재
    if(endPage  >pageCount){
    	endPage = pageCount;   //endPage = 8
    }
    //이전
    if(startPage >  pageBlock){
   %> 	
    <a href="list.jsp?pageNum=<%=startPage-pageBlock%>&field=<%=field%>&word=<%=word%>">[이전]</a>	
   	<% 
    }
    for(int i = startPage; i<=endPage;i++){//페이지번호
    	if(i == currentPage){ //현재페이지
    %>
      [<%=i %>]		
   	<% 
   	}else{
   	%>	
    	<a href="list.jsp?pageNum=<%=i %>&field=<%=field%>&word=<%=word%>"><%=i %> </a>		
  <%
    	}
  %>
      
  <%
    }
	if(endPage  < pageCount){  //다음
 %>		
		<a href="list.jsp?pageNum=<%=startPage+pageBlock%>&field=<%=field%>&word=<%=word%>">[다음]</a>
<%		
	}
}
%>




</div>


</body>
</html>










