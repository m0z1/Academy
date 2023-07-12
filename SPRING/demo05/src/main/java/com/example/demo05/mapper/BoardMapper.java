package com.example.demo05.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo05.dto.BoardDTO;

@Mapper
public interface BoardMapper {
//추가
	@Insert("insert into board(title, writer, content)"
			+ "  values(#{title}, #{writer},#{content})")
	public void insert(BoardDTO board);
	//전체보기
	@Select("select * from board")
	public List<BoardDTO> list();
	//전체보기(페이징)
	@Select("select * from board order by num desc limit #{pageStart} , #{pageSize}")
	public List<BoardDTO>pagelist(int pageStart,int pageSize );
	//상세보기
	@Select("select * from board where num=#{num}")
	public BoardDTO view(int num);
	//개수
	@Select("select count(*) from board")
	public int count();
	
	
	
	
	
}
