package com.myboard.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myboard.dto.MemberDTO;
import com.myboard.mapper.MemberMapper;
@Service
public class MemberServiceImpl implements MemberService{
  @Autowired
  private MemberMapper mMapper;
  
	@Override
	public void join(MemberDTO member) {
		mMapper.join(member);
	}

	@Override
	public int idCheck(String id) {
		return mMapper.idCheck(id);
	}

	@Override
	public MemberDTO loginCheck(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(MemberDTO member) {
		// TODO Auto-generated method stub
		
	}

}
