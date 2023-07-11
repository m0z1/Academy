package com.example.demo01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo01.dto.BoardDTO;

@Mapper
public interface BoardMapper {
	//추가
	@Insert("insert into board(title, writer, content )"
			+ " values(#{title}, ${writer}, #{content})")
	public void insert(BoardDTO board);
	//전체보기
	@Select("select * from board")
	public List<BoardDTO> list();
	//상세보기
	@Select("select * from board where num=#{num}")
	public BoardDTO findByNum(int num);
	//수정
	@Update("update board set title=#{title}, content=#{content}, regdate=now() where num=#{num}")
	public void update(BoardDTO board);
	//삭제
	@Delete("delete from board where num=#{num}")
	public void delete(int num);
	//개수
	@Select("select count(*) from board")
	public int getCount();

}
