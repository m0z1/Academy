package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConnection;

public class SMemberDAOImpl  implements SMemberDAO{
	private static SMemberDAO instance = new SMemberDAOImpl();
	public static SMemberDAO getInstance() {
		return instance;
	}
	@Override
	public void memberJoin(SMemberDTO member) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBConnection.getConnection();
			String sql = "insert into memberdb values(?,?,?,?,?,?)";
			ps =con.prepareStatement(sql);
			ps.setString(1, member.getName());
			ps.setString(2, member.getUserid());
			ps.setString(3, member.getPwd());
			ps.setString(4, member.getEmail());
			ps.setString(5, member.getPhone());
			ps.setInt(6, member.getAdmin());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps, ps, null);
		}
		
	}

	@Override
	public ArrayList<SMemberDTO> getMember() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void memberDelete(String userid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void memberUpdate(SMemberDTO member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SMemberDTO findById(StringBuilder userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int memberCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String memberIdCheck(String userid) {
		// TODO Auto-generated method stub
		return null;
	}
 
	//로그인
	@Override
	public SMemberDTO memberLoginCheck(String userid, String pwd) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		SMemberDTO member = new SMemberDTO();
		//비회원
		member.setAdmin(-1);
		
		try {
			con = DBConnection.getConnection();
			String sql = "select * from memberdb where userid = '"+userid+"'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) { //id는 맞음(회원은 맞음)
				if(rs.getString("pwd").equals(pwd)) {//비번맞음
					member.setAdmin(rs.getInt("admin"));
					member.setEmail(rs.getString("email"));
					member.setName(rs.getString("name"));
					member.setPhone(rs.getString("phone"));
					member.setPwd(rs.getString("pwd"));
					member.setUserid(rs.getString("userid"));
				}else {//비번틀림
					member.setAdmin(2);
				}
				
			}
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return member;
	}
	//닫기
			private void closeConnection(Connection con, 
					PreparedStatement ps, 	Statement st, ResultSet rs) {
				try {
					 if(rs!=null) rs.close();
					 if(st!=null) st.close();
					 if(ps!=null) ps.close();
					 if(con!=null) con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
			}

}
