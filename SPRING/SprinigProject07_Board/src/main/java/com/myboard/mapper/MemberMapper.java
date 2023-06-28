package com.myboard.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.myboard.dto.MemberDTO;

public interface MemberMapper {
	//�߰�
	@Insert("insert into member values(#{id}, #{pass},#{name},#{addr}, now())")
	public void join(MemberDTO member);
	//���̵��ߺ�Ȯ��
	@Select("select count(*)  from member where id=#{id}")
	public int idCheck(String id);
	//�α���üũ
	public MemberDTO loginCheck(String id);
	//����
	public void update(MemberDTO member);

}
