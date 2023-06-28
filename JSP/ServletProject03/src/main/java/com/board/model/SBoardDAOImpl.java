package com.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConnection;

public class SBoardDAOImpl  implements SBoardDAO{
  private static  SBoardDAO instance = new  SBoardDAOImpl();
  public static SBoardDAO getInstance() {
	  return instance;
  }
	@Override
	public void boardInsert(BoardDTO board) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con =DBConnection.getConnection();
			String sql = "insert into simpleboard "
					+ " values(simpleboard_seq.nextval,?,?,?,0,?, sysdate)";
			ps =con.prepareStatement(sql);
			ps.setString(1, board.getUserid());
			ps.setString(2, board.getSubject());
			ps.setString(3, board.getEmail());
			ps.setString(4, board.getContent());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<BoardDTO> boardList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs= null;
		ArrayList<BoardDTO> arr= new ArrayList<BoardDTO>();
		
		try {
			con = DBConnection.getConnection();
		    st = con.createStatement();
		    rs = st.executeQuery("select * from simpleboard");
		    while(rs.next()) {
		    	BoardDTO board= new BoardDTO();
		    	board.setContent(rs.getString("content"));
		    	board.setEmail(rs.getString("email"));
		    	board.setNum(rs.getInt("num"));
		    	board.setReadcount(rs.getInt("readcount"));
		    	board.setRegdate(rs.getString("regdate"));
		    	board.setSubject(rs.getString("subject"));
		    	board.setUserid(rs.getString("userid"));
		    	arr.add(board);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return arr;
	}

	@Override
	public ArrayList<BoardDTO> boardList(String field, String word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardDTO findByNum(int num) {
		String sql = "select * from simpleboard where num="+num;
		BoardDTO board = null;
		try(Connection con = DBConnection.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)	){
			if(rs.next()) {
				board = new BoardDTO();
				board.setContent(rs.getString("content"));
				board.setEmail(rs.getString("email"));
				board.setNum(rs.getInt("num"));
				board.setReadcount(rs.getInt("readcount"));
				board.setSubject(rs.getString("subject"));
				board.setRegdate(rs.getString("regdate"));
				board.setUserid(rs.getString("userid"));
			}
		} catch (SQLException e) {
					e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
	}

	@Override
	public void boardUpdate(BoardDTO board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void boardDelete(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int boardCount(String field, String word) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void commentInsert(CommentDTO comment) {
		String sql = "insert into comboard values(comboard_seq.nextval, ?,?,sysdate,?)";
		try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, comment.getUserid());
			ps.setString(2, comment.getMsg());
			ps.setInt(3, comment.getBnum());
		   ps.executeUpdate();
		} catch (SQLException e) {
					e.printStackTrace();
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
	@Override
	public ArrayList<CommentDTO> commentList(int bnum) {
		ArrayList<CommentDTO> carr = new ArrayList<>();
		String sql = "select * from comboard where bnum = "+ bnum;
		
		try(Connection con = DBConnection.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)){
			 while(rs.next()) {
				 CommentDTO c = new CommentDTO();
				 c.setBnum(rs.getInt("bnum"));
				 c.setCnum(rs.getInt("cnum"));
				 c.setMsg(rs.getString("msg"));
				 c.setRegdate(rs.getString("regdate"));
				 c.setUserid(rs.getString("userid"));
				 carr.add(c);
			 }
		} catch (SQLException e) {
					e.printStackTrace();
		} catch (Exception e) {
				e.printStackTrace();
		}
		return carr;
	}
	@Override
	public int commentCount(int bnum) {
		String sql = "select count(*) from comboard where bnum = "+bnum;
		int count = 0;
		try(Connection con = DBConnection.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)	){
			if(rs.next()) {
				count =rs.getInt(1);
			}
		} catch (SQLException e) {
					e.printStackTrace();
		} catch (Exception e) {
					e.printStackTrace();
		}
		return count;
	}

}





