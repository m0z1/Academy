package com.test.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test.dto.Member;

public interface TestMemberMapper {
	@Insert("insert into member values(#{id},#{pass},#{name},#{addr},now())")
	public void join(Member member);
	
	@Select("select count(*) from member where id=#{id}")
	public int idCheck(String id);
	
	@Select("select * from member where id=#{id}")
	public Member loginCheck(String id);

	@Update("update member set name=#{name}, pass=#{pass}, addr=#{addr} where id=#{id}")
	public void update(Member member);

	@Delete("delete from member where id =#{id}")
	public String delete(String id);
}
