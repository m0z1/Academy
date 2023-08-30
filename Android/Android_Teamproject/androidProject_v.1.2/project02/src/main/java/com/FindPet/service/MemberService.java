package com.FindPet.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.FindPet.model.Member;
import com.FindPet.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder encoder;

	public Member join(Member member) {
		// 비밀번호 암호화
		String encPassword = encoder.encode(member.getPassword());
		member.setPassword(encPassword);

		// 아이디가 "Admin"이면 관리자 권한 부여
		if (member.getUsername().equals("Admin@Admin.Admin")) {
			member.setAdmin("ROLE_ADMIN");
		} else if (!member.getUsername().equals("Admin@Admin.Admin")) {
			member.setAdmin("ROLE_member");
		}

		return memberRepository.save(member);
	}

	public int findusername(String username) {
		return memberRepository.findByUser(username);
		
	}

	public void delete(String username) {
		memberRepository.deleteByUsername(username);
	}

	public int findnickname(String nickname) {
		return memberRepository.findByNickname(nickname);
	}

	public int login(String username, String password) {
		String result = memberRepository.login(username);
		System.out.println("result1 : " + result);
		if(encoder.matches(password, result)) {
			return 1;
		} else if(!encoder.matches(password, result)) {
			return 0;
		} else {
			return 2;
		}
	}

	public Member findmember(String username) {
		return memberRepository.findByUsername(username);
	}

	

	


}
