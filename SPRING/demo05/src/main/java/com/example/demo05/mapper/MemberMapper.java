package com.example.demo05.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo05.dto.BoardDTO;
import com.example.demo05.dto.MemberDTO;

@Mapper
public interface MemberMapper {
	//추가
	@Insert("insert into member values(#{id}, #{pass},#{name},#{addr}, now())")
	public void join(MemberDTO member);
	//아이디중복확인
	@Select("select count(*)  from member where id=#{id}")
	public int idCheck(String id);
	//로그인체크
	@Select("select * from member where id=#{id}")
	public MemberDTO loginCheck(String id);
	//수정
	@Update("update member set name=#{name}, pass=#{pass}, "
			+ " addr=#{addr} where id=#{id}")
	public void update(MemberDTO member);
	
	@Select("select * from member")
	public List<MemberDTO> list();
	
}
