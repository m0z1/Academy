package com.test.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.test.dto.Member;
import com.test.mapper.TestMemberMapper;

@Service
public class TestMemberService {

	@Autowired
	private TestMemberMapper tmemberMapper;
	
	public void join(Member member) {
		tmemberMapper.join(member);
	}
	
	public int idCheck(String id) {
		return tmemberMapper.idCheck(id);
	}
	

	public Member loginCheck(String id) {
			return tmemberMapper.loginCheck(id);
	}

	public void update(Member member) {
		tmemberMapper.update(member);		
	}
	
	public String delete(String id) {
		return tmemberMapper.delete(id);
	}
}
