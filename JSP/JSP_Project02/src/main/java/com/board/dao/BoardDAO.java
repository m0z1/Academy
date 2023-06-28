package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.board.dto.Board;
import com.board.dto.Comment;

public class BoardDAO {
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	private Connection getConnection()  throws Exception { 
		Context initCtx = new InitialContext();  // java:comp/env/jdbc/jsp
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}
	//추가
	public void boardInsert(Board board) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//부모글 
		int num = board.getNum();
		int ref = board.getRef();
		int re_step = board.getRe_step();
		int re_level = board.getRe_level();
		
		int number = 0;
		try {
			con =getConnection();
			ps = con.prepareStatement("select max(num) from board");
			rs = ps.executeQuery();
			//새글일 때 ref  결정
			if(rs.next()) {  // 테이블에 데이터가 존재
				number = rs.getInt(1)+1;  // max 에 1을 더해 ==> ref 로 사용
			}else {  // 테이블에 데이터가 존재하지 않음 ==> 첫 게시글
				number =1;
			}

			if(num!=0) { //댓글
				String sql = "update board set re_step= re_step+1 where ref=? and re_step> ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, ref);
				ps.setInt(2, re_step);
				ps.executeUpdate();  // 기존 데이터의 re_step 변경
				
				re_step = re_step+1;  //부모  re_step에 1 증가
				re_level = re_level + 1; //부모  re_level에 1 증가
		        
			}else {  //새글
				ref = number;
				re_step = 0;
				re_level = 0;
			}
			String sql = "insert into board(num,writer, subject, email, "
					+ " content, ip, passwd,ref, re_step,re_level,reg_date) "
					+ " values(board_seq.nextval,?,?,?,?,?,?,?,?,?,sysdate)";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getWriter());
			ps.setString(2, board.getSubject());
			ps.setString(3, board.getEmail());
			ps.setString(4, board.getContent());
			ps.setString(5, board.getIp());
			ps.setString(6, board.getPasswd());
			ps.setInt(7, ref);
			ps.setInt(8, re_step);
			ps.setInt(9, re_level);
			ps.executeUpdate();
		} catch (Exception e) {
					e.printStackTrace();
		}finally {
			closeConnection(con, ps, ps, null);
		}
		
		
	}
	//전체보기
	public ArrayList<Board> boardList(String field, String word, int startRow, int endRow){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Board> arr = new ArrayList<>();
		
		try {
			con =getConnection();
			//String sql = "select * from board order by ref desc, re_step asc";
			StringBuilder sb = new StringBuilder();
			if(word.equals("")) { // 검색아님
				sb.append("select * from (");
				sb.append("select rownum rn, aa.* from(");
				sb.append("select * from board order by ref desc, re_step asc)aa");
				sb.append(" where rownum <=?)");
				sb.append(" where rn>=?");
			}else { //검색
				sb.append("select * from (");
				sb.append("select rownum rn, aa.* from(");
				sb.append("select * from board where "+field+" like '%"+word+"%'");
				sb.append(" order by ref desc, re_step asc)aa");
				sb.append(" where rownum <=?)");
				sb.append(" where rn>=?");
			}
		
			ps = con.prepareStatement(sb.toString());
			ps.setInt(1, endRow);
			ps.setInt(2, startRow);
			rs = ps.executeQuery();
			while(rs.next()) {
				Board b = new Board();
				b.setNum(rs.getInt("num"));
				b.setSubject(rs.getString("subject"));
				b.setWriter(rs.getString("writer"));
				b.setReg_date(rs.getString("reg_date"));
				b.setReadcount(rs.getInt("readcount"));
				arr.add(b);
				
			}
		} catch (Exception e) {
					e.printStackTrace();
		}finally {
			closeConnection(con, ps, null, rs);
		}
		return arr;
	}
	
	//개수
	public int getCount(String field, String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		String sql="";
		
		try {
			con =getConnection();
			if(word.equals("")) { //검색아님
					sql = "select count(*) from board";
			}else { //검색
					sql = "select count(*) from board where "+field + " like '%"+word+"%'";
			}
			
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return count;
	}
	//상세보기
	public Board findByNum(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Board b = null;
		String sql = "";
		try {
			con = getConnection();
			st = con.createStatement();
			sql ="update board set readcount=readcount+1 where num="+num;
			st.executeUpdate(sql);
			sql = "select * from board where num="+num;
			rs = st.executeQuery(sql);
			if(rs.next()) {
				b = new Board();
				b.setNum(rs.getInt("num"));
				b.setSubject(rs.getString("subject"));
				b.setWriter(rs.getString("writer"));
				b.setReg_date(rs.getString("reg_date"));
				b.setReadcount(rs.getInt("readcount"));
				b.setContent(rs.getString("content"));
				b.setEmail(rs.getString("email"));
				b.setIp(rs.getString("ip"));
				b.setRef(rs.getInt("ref"));
				b.setRe_step(rs.getInt("re_step"));
				b.setRe_level(rs.getInt("re_level"));
				b.setPasswd(rs.getString("passwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return b;
	}
	//수정하기
	// 비번이 맞을 때만 수정
	public int boardUpdate(Board board) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="";
		int flag = 0;
		try {
			con =getConnection();
			sql = "select passwd from board where num = "+board.getNum();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) { 	//비번이 맞는 지 조사
				if(rs.getString("passwd").equals(board.getPasswd())) {
					//비번이 맞으면 업데이트
					sql ="update board set subject =?, email=?, content =?, ip=?, "
							+ "reg_date=sysdate where num=?";
				    ps = con.prepareStatement(sql);
				    ps.setString(1, board.getSubject());
				    ps.setString(2, board.getEmail());
				    ps.setString(3, board.getContent());
				    ps.setString(4, board.getIp());
				   ps.setInt(5, board.getNum());
				  flag=ps.executeUpdate();
				}
			}
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			closeConnection(con, ps, null, rs);
		}
  	return flag;
	}
	//삭제
	public int boardDelete(int num) {
		Connection con = null;
		Statement st = null;
		int flag=0;
		try {
			con = getConnection();
			String sql = "delete from board where num="+num;
			st = con.createStatement();
			flag =st.executeUpdate(sql);
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			closeConnection(con, null, st, null);
		}
		return flag;
	}
	////////////comment
	//comment Insert
	public void commentInsert(Comment comment) {
		Connection con =null;
		PreparedStatement ps = null;
		
		try {
			con =getConnection();
			String sql = "insert into commentboard  "
					+ " values(commentboard_seq.nextval,?,?,sysdate,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, comment.getMsg());
			ps.setString(2, comment.getUserid());
			ps.setInt(3, comment.getBnum());
			ps.executeUpdate();
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			closeConnection(con, ps, ps, null);
		}
		
	}
	//comment list
	public ArrayList<Comment> commentList(int bnum){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Comment> carr = new ArrayList<>();
		
		try {
			con = getConnection();
			String sql = "select * from commentboard where bnum="+bnum;
			st= con.createStatement();
			rs = st.executeQuery(sql);
		   while(rs.next()) {
			   Comment c = new Comment();
			   c.setBnum(rs.getInt("bnum"));
			   c.setCnum(rs.getInt("cnum"));
			   c.setMsg(rs.getString("msg"));
			   c.setRegdate(rs.getString("regdate"));
			   c.setUserid(rs.getString("userid"));
			   carr.add(c);
		   }
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return carr;
	}
	//comment  개수
	public int commentCount(int bnum) {
	    Connection  con = null;
	    Statement st = null;
	    ResultSet rs = null;
	    int count =0;
	    
	    try {
			con = getConnection();
			String sql = "select count(*) from commentboard where bnum="+bnum;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				count= rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return count;
	}
	
	
	////
	private void closeConnection(Connection con, PreparedStatement ps,
					 Statement st, ResultSet rs) {
					try {
						 if(con!=null)  con.close();
						 if(ps!=null)  ps.close();
						 if(st!=null)  st.close();
						 if(rs!=null)  rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			 }
	

}
