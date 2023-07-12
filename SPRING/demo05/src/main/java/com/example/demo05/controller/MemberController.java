package com.example.demo05.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo05.dto.BoardDTO;
import com.example.demo05.dto.MemberDTO;
import com.example.demo05.dto.PageVO;
import com.example.demo05.service.BoardServcie;
import com.example.demo05.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;

	// 회원가입폼
	@GetMapping("join")
	public String join() {
		return "member/join";
	}

	// 회원가입
	@PostMapping("join")
	@ResponseBody
	public String join(@RequestBody MemberDTO member) {
		int cnt = memberService.idCheck(member.getId());
		if (cnt != 0)
			return "fail"; // 아이디 존재
		memberService.join(member);
		return "success";
	}
	

	// 로그인폼
	@GetMapping("login")
	public String login() {
		return "member/login";
	}

	@PostMapping("login")
	public String login(MemberDTO member, HttpSession session) {
		MemberDTO m = memberService.loginCheck(member.getId());
		
		if (m.getPass().equals(member.getPass())) { // 회원(비번맞음)
			session.setAttribute("sMember", m);
		}
		return "redirect:/";
		
	}
	
	@GetMapping("memberList")
	public String list(Model model) {
		model.addAttribute("members",memberService.list());
		
		return "member/list";
	}

	// 로그아웃
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "member/login";
	}

}
