package com.myboard.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.myboard.dto.MemberDTO;

public interface MemberMapper {
	//추가
	@Insert("insert into member values(#{id}, #{pass},#{name},#{addr}, now())")
	public void join(MemberDTO member);
	//아이디중복확인
	@Select("select count(*)  from member where id=#{id}")
	public int idCheck(String id);
	//로그인체크
	public MemberDTO loginCheck(String id);
	//수정
	public void update(MemberDTO member);

}
