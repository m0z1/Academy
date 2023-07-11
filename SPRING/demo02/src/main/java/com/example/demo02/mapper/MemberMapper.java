package com.example.demo02.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo02.dto.MemberDTO;

@Mapper
public interface MemberMapper {
	public MemberDTO read(String username);

}
