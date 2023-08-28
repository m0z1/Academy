package com.example.androidphone.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.androidphone.model.Member;
import com.example.androidphone.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/*")
public class MemberController {

	private final MemberService memberService;
	
	// 회원 가입
	@PostMapping("/join")
	public Member join(@RequestBody Member member) {
		
		return memberService.join(member);
	}
	
	
	// 회원 리스트
	@GetMapping("/list")
	public List<Member> list() {
		
		return memberService.list();
	}
	
	
	// 회원 조회
	@GetMapping("/view/{memberId}")
	public Member view(@PathVariable Long memberId) {
		
		return memberService.view(memberId);
	}
	
	
	// 회원 업데이트
	
	
	// 회원 탈퇴
	@DeleteMapping("delete/{memberId}")
	public String delete(@PathVariable Long memberId) {
		memberService.delete(memberId);
		return "success";
	}
	
}
