package com.person.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class PersonDAOImpl  implements PersonDAO{
	private JdbcTemplate template;
	//setter;
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	//�߰�
	@Override
	public void personInsert(Person person) {
		String sql ="insert into person values(?,?,?,?,?)";
		Object[] param = new Object[] {
				person.getId(),
				person.getName(),
				person.getPassword(),
				person.getGender(),
				person.getJob()
		};
		template.update(sql,param);
	}

	
//��ü����
	@Override
	public List<Person> personList() {
		String sql = "select * from person";
		List<Person> personlist = template.query(sql, new RowMapper<Person>() {
			@Override
			public Person mapRow(ResultSet rs, int arg1) throws SQLException {
				Person p = new Person();
				p.setGender(rs.getString("gender"));
				p.setId(rs.getString("id"));
				p.setJob(rs.getString("job"));
				p.setName(rs.getString("name"));
				p.setPassword(rs.getString("password"));
				return p;
			}
		});
		return personlist;
	}

	@Override
	public Person personView(String id) {
		String sql = "select * from person where id ='"+id+"'";
		 Person person = template.queryForObject(sql, new RowMapper<Person>() {
			@Override
			public Person mapRow(ResultSet rs, int arg1) throws SQLException {
				Person p = new Person();
				p.setGender(rs.getString("gender"));
				p.setId(rs.getString("id"));
				p.setJob(rs.getString("job"));
				p.setName(rs.getString("name"));
				p.setPassword(rs.getString("password"));
				return p;
			}
		});
		return person;
	}

	@Override
	public void personUpdate(Person person) {
		String sql = "update person set name=?, "
				+ "password=?, gender=?, job=? where id=?";
		Object[] param = new Object[] {
				person.getName(), person.getPassword(),
				person.getGender(), person.getJob(),
				person.getId()
		};
		template.update(sql,param);
	}

	@Override
	public void personDelete(String id) {
		String sql ="delete from person where id='"+id+"'";
		template.update(sql); 
				
		
	}

}
