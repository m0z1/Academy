package com.myboard.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.myboard.dto.BoardDTO;
@Mapper
public interface BoardMapper {
	//추가
	@Insert("insert into board(title, writer, content) values(#{title}, #{writer},#{content})")
	public void insert(BoardDTO board);
	//전체보기
	//@Select("select * from board")
	public List<BoardDTO> findAll(HashMap<String, Object> hm);
	//상세보기
	@Select("select * from board where num=#{num}")
	public BoardDTO findByNum(int num);
	//수정
	@Update("update board set title=#{title}, content=#{content}, regdate=now() where num=#{num}")
	public void update(BoardDTO board);
	//삭제
	@Delete("delete from board where num =#{num}")
	public void delete(int num);
	//개수
	//@Select("select count(*) from board")
	public int getCount(HashMap<String, Object> hm);
  //조회수 증가
	@Update("update board set hitcount = hitcount+1 where num=#{num}")
	public void upReadCount(int num);
	//replyCnt 증감
	@Update("update board set "
			+ " replyCnt = replyCnt+#{amount} where num=#{bnum}")
	public void replyCnt(@Param("bnum") int  bnum, 
			@Param("amount") int amount);
	
}
