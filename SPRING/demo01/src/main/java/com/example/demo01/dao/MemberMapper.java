package com.example.demo01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo01.dto.BoardDTO;
import com.example.demo01.dto.MemberDTO;

@Mapper
public interface MemberMapper {
	//로그인체크
		@Select("select * from member where  id=#{id}")
		public MemberDTO loginCheck(String id);

}
