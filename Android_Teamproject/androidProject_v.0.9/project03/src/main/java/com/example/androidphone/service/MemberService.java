package com.example.androidphone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.androidphone.model.Member;
import com.example.androidphone.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	private BCryptPasswordEncoder encoder;

	
	// 회원 가입
	public Member join(Member member) {
		
		// 비밀번호 암호화
		String encPassword = encoder.encode(member.getPassword());
		member.setPassword(encPassword);
		
		// 아이디가 "Admin"이면 관리자 권한 부여
		if(member.getUsername().equals("Admin")) {
			member.setAdmin("ROLE_ADMIN");
		}else if(!member.getUsername().equals("Admin")){
			member.setAdmin("ROLE_member");
		}
		
		// DB 저장
		return memberRepository.save(member);
	}
	
	
	// 회원 리스트
	public List<Member> list(){
	      return memberRepository.findAll();
	}
	
	
	// 회원 리스트 (페이징)
	/*
	 * public Page<Member> list(Pageable pageable){ return
	 * userRepository.findAll(pageable); }
	 */
	
	
	// 회원 조회
	public Member view(Long memberId) {
		return memberRepository.findById(memberId).get();
	}
	
	
	// 회원 업데이트
	
	
	// 회원 탈퇴
	public void delete(Long memberId) {
		memberRepository.deleteById(memberId);
	}
}
