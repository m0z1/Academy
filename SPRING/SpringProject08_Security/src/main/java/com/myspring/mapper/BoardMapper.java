package com.myspring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.myspring.dto.BoardDTO;

public interface BoardMapper {
//추가
	@Insert("insert into board(title, writer, content) "
			+ " values(#{title}, #{writer},#{content})")
	public void insert(BoardDTO  board);
//전체보기
	@Select("select * from board")
	public List<BoardDTO> list();
	//상세보기
	@Select("select * from board where num=#{num}")
	public BoardDTO view(int num);
}
