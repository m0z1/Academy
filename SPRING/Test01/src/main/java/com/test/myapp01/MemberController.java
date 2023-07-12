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
		//id �� �����ϴ� ���� �˻��ϰ�
		int cnt = tmemberService.idCheck(member.getId());
		if(cnt !=0) return "fail";  // ���̵� ����
		tmemberService.join(member);
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
	public String loginSubmit(Member member, HttpSession session) {
		Member m = tmemberService.loginCheck(member.getId());
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
	public String login(@RequestBody Member member, 
			  HttpSession session) {
		Member m = tmemberService.loginCheck(member.getId());
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
	
	

