package com.example.demo01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo01.dto.CommentDTO;

@Mapper
public interface CommentMapper {
	//추가
		@Insert("insert into commentboard(userid, content,bnum) values(#{userid},#{content}, #{bnum})")
		public void insert(CommentDTO comment);
		//전체보기
		@Select("select * from commentboard where bnum=#{bnum}")
		public List<CommentDTO> getList(int bnum);
		//삭제
		@Delete("delete from commentboard where cnum=#{cnum}")
		public void delete(int cnum);
		//개수
		@Select("select count(*) from commentboard where bnum=#{bnum}")
		public int getReplyCount(int bnum);
	  //read
		@Select("select * from commentboard where cnum=#{cnum}")
		public CommentDTO read(int cnum);
}
