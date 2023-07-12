package com.test.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test.dto.Board;

public interface TestBoardMapper {
	@Insert("insert into board(title, writer, content) values(#{title},#{writer}, #{content})")
	public void insert(Board board);
	
	
	@Select("select * from board where num = #{num}")
	public Board findByNum(int num);
	
	
	@Select("select * from board")
	public List<Board> list(HashMap<String, Object> hm); 
	
	@Select("select count(*) from board")
	public int count( HashMap<String, Object> hm);
	
	@Delete("delete from board where num =#{num}")
	public int delete(int num);
	
	@Update("update board set title=#{title}, content =#{content}, regdate=now() where num=#{num}")
	public void update(Board board);
}
