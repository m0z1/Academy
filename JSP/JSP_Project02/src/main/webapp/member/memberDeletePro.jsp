<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.member.dto.Member"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.member.dao.MemberDAOImpl"%>
<%@page import="com.member.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String userid = request.getParameter("userid");
MemberDAO dao = MemberDAOImpl.getInstance();
dao.memberDelete(userid);//
ArrayList<Member> arr = dao.memberList();
int count =dao.getCount();

//자바 ==>json
//메인(루트)
JSONObject mainObj = new JSONObject();

//arr
JSONArray jarr = new JSONArray();
for(Member m : arr){
	String mode = m.getAdmin() == 0 ?"일반회원" : "관리자";
	JSONObject obj = new JSONObject();
	obj.put("name", m.getName());
	obj.put("userid", m.getUserid());
	obj.put("pwd", m.getPwd());
	obj.put("phone", m.getPhone());
	obj.put("email", m.getEmail());
	obj.put("admin", mode);
	jarr.add(obj);
}
//cont
JSONObject countObj = new JSONObject();
countObj.put("count",count);

//메인에 추가
mainObj.put("jarrObj", jarr); // jarr 를 메인에 추가
mainObj.put("countObj", countObj);  //countObj 를 메인에 추가
out.print(mainObj.toString());
%>


