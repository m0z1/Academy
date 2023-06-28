package com.product.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConnection;

public class ProductDAOImpl  implements ProductDAO{
	private static ProductDAO instance  =  new ProductDAOImpl();
	public static ProductDAO getInstance() {
		return instance;
	}

	@Override
	public void productInsert(Product product) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBConnection.getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("insert into product");
			sb.append("(p_productId,p_pname,p_unitPrice, p_description,");
			sb.append("p_category,p_manufacturer, p_unitsInStock,");
			sb.append("p_condition,p_fileName) ");
			sb.append("values (product_seq.nextval, ?,?,?,?,?,?,?,?)");
			System.out.println(sb.toString());
			ps= con.prepareStatement(sb.toString());
			 ps.setString(1, product.getPname());
			 ps.setInt(2, product.getUnitPrice());
			 ps.setString(3, product.getDescription());
			 ps.setString(4, product.getCategory());
			 ps.setString(5, product.getManufacturer());
			 ps.setLong(6, product.getUnitsInStock());
			 ps.setString(7, product.getCondition());
			 ps.setString(8, product.getFilename());
			 ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<Product> productList() {
		Connection con = null;
	     Statement st = null;
	     ResultSet rs = null;
	     ArrayList<Product> parr = new ArrayList<>();
	     
	     try {
			con =DBConnection.getConnection();
			String sql="select * from product";
			st = con.createStatement();
			rs= st.executeQuery(sql);
			while(rs.next()) {
				Product product = new Product();
				product.setCategory(rs.getString("p_category"));
				product.setCondition(rs.getString("p_condition"));
				product.setDescription(rs.getString("p_description"));
				product.setFilename(rs.getString("p_fileName"));
				product.setManufacturer(rs.getString("p_manufacturer"));
				product.setPname(rs.getString("p_pname"));
				product.setProductId(rs.getInt("p_productId"));
				product.setUnitPrice(rs.getInt("p_unitPrice"));
				product.setUnitsInStock(rs.getInt("p_unitsInStock"));
				parr.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return parr;
	}

	@Override
	public Product findById(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
