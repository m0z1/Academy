package com.example.demo05.service;

import java.lang.reflect.Member;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo05.dto.BoardDTO;
import com.example.demo05.dto.MemberDTO;
import com.example.demo05.mapper.BoardMapper;
import com.example.demo05.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private  final MemberMapper memberMapper;
	
	
	public void join(MemberDTO member) {
		memberMapper.join(member);
	}


	public int idCheck(String id) {
		return memberMapper.idCheck(id);
	}


	public MemberDTO loginCheck(String id) {
			return memberMapper.loginCheck(id);
	}


	public void update(MemberDTO member) {
		memberMapper.update(member);		
	}
	
	
	public List<MemberDTO> list(){
		return memberMapper.list();
	}
	
 
}
