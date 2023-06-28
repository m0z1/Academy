package com.myspring.app07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	

}
