package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.board.model.CommentDTO;
import com.board.model.SBoardDAO;
import com.board.model.SBoardDAOImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class CommentListController
 */
@WebServlet("/board/commentlist")
public class CommentListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   request.setCharacterEncoding("utf-8");
	   int bnum = Integer.parseInt(request.getParameter("bnum"));
	   SBoardDAO dao = SBoardDAOImpl.getInstance();
	   ArrayList<CommentDTO> carr  = dao.commentList(bnum);
	   int count = dao.commentCount(bnum);
	   
	   //gson.jar
	   Gson gson = new Gson();
	   Map<String, Object>  hm = new  HashMap<>();
	   hm.put("jarr", carr);
	   hm.put("count", count);
	   String jsonStr = gson.toJson(hm);
	   //화면에 출력
	    response.setContentType("application/json;charset=utf-8");
	    PrintWriter out = response.getWriter();
	    out.println(jsonStr);
	   
	   
//	   //json  출력  ==> json-simple.jar
//	   //mainObj(루트)
//	   JSONObject mainObj = new JSONObject();
//	   
//	   //개수
//	   JSONObject countObj = new JSONObject();
//	   countObj.put("count", count);
//	   
//	   //carr ==>json
//	    JSONArray jarr = new JSONArray();
//	    for (CommentDTO c : carr) {
//	    	JSONObject obj = new JSONObject();
//	    	obj.put("userid", c.getUserid());
//	    	obj.put("bnum", c.getBnum());
//	    	obj.put("cnum", c.getCnum());
//	    	obj.put("regdate", c.getRegdate());
//	    	obj.put("msg", c.getMsg());
//	    	jarr.add(obj);
//	    }
//	    mainObj.put("countObj", countObj);
//	    mainObj.put("jarrObj", jarr);
//	    //화면에 출력
//	    response.setContentType("application/json;charset=utf-8");
//	    PrintWriter out = response.getWriter();
//	    out.println(mainObj.toJSONString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
