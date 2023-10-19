package com.FindPet.controller;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.FindPet.model.Member;
import com.FindPet.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/*")
public class MemberController {
	
	private final MemberService memberService;
	private final BCryptPasswordEncoder encoder;
	
	@PostMapping("/join")
	public Member join(@RequestBody Member member) {
		System.out.println("join");
		return memberService.join(member);
	}
	
	@GetMapping("/findusername/{username}")
	public int findusername(@PathVariable String username) {
		int user = memberService.findusername(username);
		System.out.println(user);
		return user;
		
	}
	
	@GetMapping("/findnick/{nickname}")
	public int findnick(@PathVariable String nickname) {
		int nick = memberService.findnickname(nickname);
		return nick;
	}
	
	@DeleteMapping("/delete/{username}")
	public String delete(@PathVariable String username) {
		System.out.println("삭제");
		memberService.delete(username);
		return "success";
	}
	
	@GetMapping("/login")
	public Integer login(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password) {
		int result = memberService.login(username, password);
		System.out.println("result2 : " + result);
		return result;
	}
	
	@GetMapping("/findmember/{username}")
	public Member findmember(@PathVariable String username) {
		System.out.println(username);
		return memberService.findmember(username);
	}
	
	@PutMapping("/update/{username}")
	@Transactional
	public Member update(@PathVariable String username, @RequestBody Member member) {
		Member m = memberService.findmember(username);
		String encPassword = encoder.encode(member.getPassword());
		m.setPassword(encPassword);
		m.setName(member.getName());
		m.setNickname(member.getNickname());
		m.setTel(member.getTel());
		
		System.out.println("username : " + m.getUsername());
		System.out.println("name : " + m.getName());
		System.out.println("nickname : " + m.getNickname());
		System.out.println("tel : " + m.getTel());
		return m;
	}

}
