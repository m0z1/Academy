package com.myboard.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.myboard.dto.MemberDTO;
@Mapper
public interface MemberMapper {
	//�߰�
	@Insert("insert into member values(#{id}, #{pass},#{name},#{addr}, now())")
	public void join(MemberDTO member);
	//���̵��ߺ�Ȯ��
	@Select("select count(*)  from member where id=#{id}")
	public int idCheck(String id);
	//�α���üũ
	@Select("select * from member where id=#{id}")
	public MemberDTO loginCheck(String id);
	//����
	@Update("update member set name=#{name}, pass=#{pass}, "
			+ " addr=#{addr} where id=#{id}")
	public void update(MemberDTO member);

}










