package com.example.demo01.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo01.dto.BoardDTO;
import com.example.demo01.dto.MemberDTO;
import com.example.demo01.service.BoardService;

@Controller
public class HomeCotroller {
	@Autowired
	private BoardService bservice;
	@GetMapping("/")
	public String home() {
		return "home";
	}
	@GetMapping("/insert")
	public String insert() {
		return "insert";
	}

	@PostMapping("/insert")
	public String insert(BoardDTO board) {
		bservice.insert(board);
		return "redirect:list";
	}
	@GetMapping("/list")
	public String list(Model  model) {
		model.addAttribute("boards", bservice.list());
		model.addAttribute("count", bservice.getCount());
		return "list";
	}
	@GetMapping("view/{num}")
	public String view(@PathVariable int num, Model  model) {
		model.addAttribute("board", bservice.findByNum(num));
	
		return "view";
	}
	@DeleteMapping("delete/{num}")
	@ResponseBody
	public int delete(@PathVariable int num) {
		bservice.delete(num);
		return num;
	}
	//수정폼
	@GetMapping("update/{num}")
    public String update(@PathVariable int num, Model model) {
		model.addAttribute("board", bservice.findByNum(num));
		return "update";
	}
	//수정
	@PutMapping("update")
	@ResponseBody
	public int update(@RequestBody BoardDTO board) {
		 bservice.update(board);
		return board.getNum();
	}
	//로그인
	@GetMapping("login")
	public void login() {}
	
	
	@PostMapping("login")
	@ResponseBody
	public String login(@RequestBody MemberDTO user, HttpSession session) {
		MemberDTO member = bservice.loginCheck(user.getId());
		if(member == null) { //회원이 아님
			return "no";
		}
		if(user.getPass().equals(member.getPass())) { //회원(비번 맞음)
			session.setAttribute("sMember", member);
			return "success";
		}else { //비번 틀림
			return "pError";
		}
		
	}
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "/login";
	}
	
	

}
