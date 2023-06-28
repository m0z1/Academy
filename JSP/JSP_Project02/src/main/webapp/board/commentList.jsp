<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.board.dto.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.board.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
int bnum = Integer.parseInt(request.getParameter("bnum"));
//int bnum=25;
BoardDAO dao = BoardDAO.getInstance();
ArrayList<Comment> carr =dao.commentList(bnum);
int count =dao.commentCount(bnum);

//main
JSONObject mainObj = new JSONObject();

//carr == > json (댓글내용)
JSONArray jarr = new JSONArray();
for(Comment c : carr){
	JSONObject obj = new JSONObject();
	obj.put("userid", c.getUserid());
	obj.put("cnum", c.getCnum());
	obj.put("bnum", c.getBnum());
	obj.put("msg", c.getMsg());
	obj.put("regdate", c.getRegdate());
	jarr.add(obj);
}
//개수
JSONObject countObj = new JSONObject();
countObj.put("count",count);

//메인에 추가(데이터와 개수)
mainObj.put("dataObj", jarr);
mainObj.put("countObj", countObj);
out.println(mainObj.toString());
%>









