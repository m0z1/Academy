package com.address;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class AddressDAO {
	String url, user, pwd;
		//디비연결
//	private static AddressDAO instance = new AddressDAO();
//	public static AddressDAO getInstance() {
//		return instance;
//	}
	private static AddressDAO instance;
	public static AddressDAO getInstance() {
		if(instance == null) {
			instance = new AddressDAO();
		}
		return instance;
	}
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}
	
//	public AddressDAO() {
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			 url = "jdbc:oracle:thin:@localhost:1521:xe";
//			 user="scott";
//			 pwd="tiger";
//		} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//		}
//		
//	}
	//수정
	public void update(Address ad) {
		//우편번호, 이름, 전화번호, 주소 변경
		//update address set name='이순신',tel='555' where num=1;
		Connection con = null;
		PreparedStatement ps= null;
		
		try {
		//	con = DriverManager.getConnection(url, user, pwd);
			con = getConnection();
			String sql="update address set name"=?, "
					+ "zipcode=?,tel=?, addr=? where num=?";
			ps= con.prepareStatement(sql);
			ps.setString(1, ad.getName());
			ps.setString(2, ad.getZipcode());
			ps.setString(3, ad.getTel());
			ps.setString(4, ad.getAddr());
			ps.setInt(5, ad.getNum());
			ps.executeUpdate();
		} catch (Exception e) {
     		e.printStackTrace();
		}finally {
			closeConnection(con, ps, null, null);
		}
	}
	//삭제
	public void delete(int num) {
		Connection con = null;
		Statement st = null;
		try {
		//	con = DriverManager.getConnection(url, user, pwd);
			con = getConnection();
			String sql ="delete from address where num="+num;
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			closeConnection(con, null, st, null);
		}
	}
	
	//추가 insert ~~~
	public void insert(Address ad) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
		//	con = DriverManager.getConnection(url, user, pwd);
			con = getConnection();
			String sql="insert into address values(address_seq.nextval,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, ad.getName());
			ps.setString(2, ad.getZipcode());
			ps.setString(3, ad.getAddr());
			ps.setString(4, ad.getTel());
			ps.executeUpdate();
		
		} catch (Exception e) {
				e.printStackTrace();
		}
		finally {
			closeConnection(con, ps, null, null);
		}
	
	}
	//개수
	public int getCount() {
		int count = 0;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
		//	con = DriverManager.getConnection(url, user, pwd);
			con = getConnection();
			String sql ="select count(*) from address";
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
	public Address detail(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Address ad = null;
		
		try {
		//	con=DriverManager.getConnection(url, user, pwd);
			con =getConnection();
			String sql = "select * from address where num="+num;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
			    ad = new Address();
			   ad.setAddr(rs.getString("addr"));
			   ad.setName(rs.getString("name"));
			   ad.setNum(rs.getInt("num"));
			   ad.setTel(rs.getString("tel"));
			   ad.setZipcode(rs.getString("zipcode"));
				
			}
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		return ad;
		
	}
	// 검색포함 전체보기(검색인지 아닌지)
	public ArrayList<Address> list(String field, String word){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Address> arr  = new ArrayList<>();
		String sql="";
		
		try {
			//con = DriverManager.getConnection(url, user, pwd);
			con = getConnection();
			st = con.createStatement();
			if(word.equals("")) { //검색아님
				 sql = "select * from address";
			}else {  //검색
				 sql = "select * from address where "+field+" like '%"+word+"%'"; // 수정하기
			}                                                         
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Address ad = new Address();
				ad.setAddr(rs.getString("addr"));
				ad.setName(rs.getString("name"));
				ad.setNum(rs.getInt("num"));
				ad.setTel(rs.getString("tel"));
				ad.setZipcode(rs.getString("zipcode"));
				arr.add(ad);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
	 return arr;
		
	}
	//검색포함 갯수(검색인지 아닌지)
	public int getCount(String field, String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		int count=0;
		
		try {
			//con = DriverManager.getConnection(url, user, pwd);
			con =getConnection();
			if(word.equals("")) {
				sql = "select count(*) from address";
			}else {
				sql = "select count(*) from address where "+field +" like '%"+word+"%'";
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
	
	//////////////////
	
	//전체보기 : 검색없음
	 public ArrayList<Address> list() {
		 Connection con = null;
		 Statement st = null;
		 ResultSet rs = null;
		 ArrayList<Address > arr = new ArrayList<>();
		 
		 try {
		//	con = DriverManager.getConnection(url, user, pwd);
			 con =getConnection();
			st = con.createStatement();
			 String sql = "select *  from address order by num desc";
			rs =  st.executeQuery(sql);
			while(rs.next()) {
				Address ad = new Address();
				ad.setAddr(rs.getString("addr"));
				ad.setName(rs.getString("name"));
				ad.setNum(rs.getInt("num"));
				ad.setTel(rs.getString("tel"));
				ad.setZipcode(rs.getString("zipcode"));
				arr.add(ad);
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
	 return arr;
	 }
	 /////////////
	 //우편번호 찾기
	 public ArrayList<ZipCode>  zipcodeRead(String dong) {
		 //select * from zipcode where dong like '%부전동%';
		 Connection con = null;
		 Statement st = null;
		 ResultSet rs = null;
		 ArrayList<ZipCode> zarr = new ArrayList<>(); 
		 
		 try {
		//	con = DriverManager.getConnection(url, user, pwd);
			 con =getConnection();
			 String sql = "select * from zipcode where dong like '%"+dong+"%'";
			 st = con.createStatement();
			 rs = st.executeQuery(sql);
			 while(rs.next()) {
				 ZipCode z= new ZipCode();
				 z.setBunji(rs.getString("bunji"));
				 z.setDong(rs.getString("dong"));
				 z.setGugun(rs.getString("gugun"));
				 z.setSido(rs.getString("sido"));
				 z.setZipcode(rs.getString("zipcode"));
				 zarr.add(z);
			 }
	
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			closeConnection(con, null, st, rs);
		}
		 return zarr;
		
		 
	 }
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






