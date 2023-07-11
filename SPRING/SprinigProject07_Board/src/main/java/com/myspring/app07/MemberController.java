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
		//id �� �����ϴ� ���� �˻��ϰ�
		int cnt = mservice.idCheck(member.getId());
		if(cnt !=0) return "fail";  // ���̵� ����
		mservice.join(member);
		return "success";
	}
	//�α�����
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	//loginSubmit
	@PostMapping("loginSubmit")
	@ResponseBody
	public String loginSubmit(MemberDTO member, HttpSession session) {
		MemberDTO m = mservice.loginCheck(member.getId());
		if(m==null) { //  ȸ���ƴ�
			return "no";
		}
		if(m.getPass().equals(member.getPass())) { //ȸ��(�������)
			session.setAttribute("sMember", m);
			return "success";
		}else { //�������
			return "error";
		}
	}
	/////////////////////
	//�α���
	@PostMapping("login")
	@ResponseBody
	public String login(@RequestBody MemberDTO member, 
			  HttpSession session) {
		MemberDTO m = mservice.loginCheck(member.getId());
		if(m==null) { //  ȸ���ƴ�
			return "no";
		}
		if(m.getPass().equals(member.getPass())) { //ȸ��(�������)
			session.setAttribute("sMember", m);
			return "success";
		}else { //�������
			return "error";
		}
	}
	
	//�α׾ƿ�
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "member/login";
	}
	//ȸ��������
	@GetMapping("update")
	public void update() {	}
	
	//ȸ������
	@PutMapping("update")
	@ResponseBody
	public String update(@RequestBody MemberDTO member, HttpSession session) {
		mservice.update(member);
		session.invalidate();
		return "success";
	}
	
}






