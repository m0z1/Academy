package com.test.myapp01;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.dto.Member;
import com.test.service.TestMemberService;


@RequestMapping("/member/*")
@Controller
public class MemberController {
	
	
	@Autowired
	private TestMemberService tmemberService;
	

	@GetMapping("join")
	public void join() {
	
	}
	@PostMapping("join")
	@ResponseBody
	public String join(@RequestBody Member member) {
		//id 가 존재하는 먼저 검사하고
		int cnt = tmemberService.idCheck(member.getId());
		if(cnt !=0) return "fail";  // 아이디 존재
		tmemberService.join(member);
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
	public String loginSubmit(Member member, HttpSession session) {
		Member m = tmemberService.loginCheck(member.getId());
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
	public String login(@RequestBody Member member, 
			  HttpSession session) {
		Member m = tmemberService.loginCheck(member.getId());
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
	public String update(@RequestBody Member member, HttpSession session) {
		tmemberService.update(member);
		session.invalidate();
		return "success";
	}
	
	@DeleteMapping("delete/{id}")
	@ResponseBody
	public String delete(@PathVariable String id) {
		tmemberService.delete(id);
		return "redirect:member/login";
	}
	
}
	
	

