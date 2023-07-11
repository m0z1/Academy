package com.myspring.app07;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myboard.dto.MemberDTO;
import com.myboard.model.MemberService;

@RequestMapping("/member/*")
@Controller
public class MemberController {
	@Autowired
	private MemberService mservice;
	@GetMapping("join")
	public void join() {
	
	}
	@PostMapping("join")
	@ResponseBody
	public String join(@RequestBody MemberDTO member) {
		//id 가 존재하는 먼저 검사하고
		int cnt = mservice.idCheck(member.getId());
		if(cnt !=0) return "fail";  // 아이디 존재
		mservice.join(member);
		return "success";
	}
	//로그인폼
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	//loginSubmit
	@PostMapping("loginSubmit")
	@ResponseBody
	public String loginSubmit(MemberDTO member, HttpSession session) {
		MemberDTO m = mservice.loginCheck(member.getId());
		if(m==null) { //  회원아님
			return "no";
		}
		if(m.getPass().equals(member.getPass())) { //회원(비번맞음)
			session.setAttribute("sMember", m);
			return "success";
		}else { //비번오류
			return "error";
		}
	}
	/////////////////////
	//로그인
	@PostMapping("login")
	@ResponseBody
	public String login(@RequestBody MemberDTO member, 
			  HttpSession session) {
		MemberDTO m = mservice.loginCheck(member.getId());
		if(m==null) { //  회원아님
			return "no";
		}
		if(m.getPass().equals(member.getPass())) { //회원(비번맞음)
			session.setAttribute("sMember", m);
			return "success";
		}else { //비번오류
			return "error";
		}
	}
	
	//로그아웃
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "member/login";
	}
	//회원변경폼
	@GetMapping("update")
	public void update() {	}
	
	//회원변경
	@PutMapping("update")
	@ResponseBody
	public String update(@RequestBody MemberDTO member, HttpSession session) {
		mservice.update(member);
		session.invalidate();
		return "success";
	}
	
}






