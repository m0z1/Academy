package com.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.member.dto.Member;

public class MemberDAOImpl  implements MemberDAO{
	
	private static MemberDAO instance = new MemberDAOImpl();
	public static MemberDAO getInstance() {
		return instance;
	}
	private Connection getConnection()  throws Exception { 
		Context initCtx = new InitialContext();  // java:comp/env/jdbc/jsp
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}
 //추가
	@Override
	public void memberInsert(Member member) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    
	    try {
			con =getConnection();
			String sql = "insert into memberdb values(?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getName());
			ps.setString(2,  member.getUserid());
			ps.setString(3, member.getPwd());
			ps.setString(4, member.getEmail());
			ps.setString(5, member.getPhone());
			ps.setInt(6, member.getAdmin());
			ps.executeUpdate();
		} catch (Exception e) {
					e.printStackTrace();
		}finally {
			closeConnection(con, ps, null, null);
		}
	}

	@Override
	public ArrayList<Member> memberList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Member> arr = new ArrayList<>();
		
		try {
			con =getConnection();
			String sql = "select * from memberdb";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Member m = new Member();
				m.setAdmin(rs.getInt("admin"));
				m.setEmail(rs.getString("email"));
				m.setName(rs.getString("name"));
				m.setPhone(rs.getString("phone"));
				m.setPwd(rs.getString("pwd"));
				m.setUserid(rs.getString("userid"));
				arr.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return arr;
	}

	@Override
	public void memberUpdate(Member member) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con =getConnection();
			String sql = "update memberdb set name =?, pwd=?, phone=?,"
					+ " email=?, admin=?  where userid = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getName());
			ps.setString(2, member.getPwd());
			ps.setString(3, member.getPhone());
			ps.setString(4, member.getEmail());
			ps.setInt(5, member.getAdmin());
			ps.setString(6, member.getUserid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps, ps, null);
		}
		
	}

	@Override
	public void memberDelete(String userid) {
		Connection con = null;
		Statement st = null;
		
		try {
			con=getConnection();
			String sql = "delete from memberdb where userid ='"+userid+"'";
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			closeConnection(con, null, st, null);
		}
		
	}

	@Override
	public Member findById(String userid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs= null;
		Member m = null;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select * from memberdb where userid ='"+userid+"'";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				m = new Member();
				m.setAdmin(rs.getInt("admin"));
				m.setEmail(rs.getString("email"));
				m.setName(rs.getString("name"));
				m.setPhone(rs.getString("phone"));
				m.setPwd(rs.getString("pwd"));
				m.setUserid(rs.getString("userid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		
		return m;
	}

	@Override
	public String idCheck(String userid) {
       Connection con = null;
       Statement st = null;
       ResultSet rs = null;
       String flag = "yes"; //아이디 없음(사용가능)
         try {
		con =getConnection();
		String sql = "select * from memberdb where userid='"+userid+"'";
		st = con.createStatement();
		rs = st.executeQuery(sql);
		if(rs.next()) {
			flag="no"; //아이디가 존재(사용불가능)
		}
	} catch (Exception e) {
			e.printStackTrace();
	}finally {
		closeConnection(con, null, st, rs);
	}
		return flag;
	}

	@Override
	public int loginCheck(String userid, String pwd) {
		//회원아님: -1  , /  일반회원: 0, 관리자:1  /   비번오류: 2
		int flag  = -1 ; // 회원아님
		String sql = "select pwd , admin from memberdb where userid = '"+userid+"'";
		try(Connection con = getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)	){
			if(rs.next()) { //userid  맞음 (회원은 맞지만 비번 검사 안함)
				if(rs.getString("pwd").equals(pwd)) {//비번 맞음
					flag = rs.getInt("admin");
				}else { //비번 오류
					flag=2;
				}
			}
		} catch (SQLException e) {
				e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int getCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs= null;
		int count = 0;
		
		try {
			con =getConnection();
			String sql = "select count(*) from memberdb";
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
