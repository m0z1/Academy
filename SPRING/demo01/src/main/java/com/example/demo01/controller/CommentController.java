package com.example.demo01.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo01.dto.CommentDTO;
import com.example.demo01.dto.MemberDTO;
import com.example.demo01.service.CommentService;

@RequestMapping("/reply/*")
@RestController//@Controller +@ResponseBody
public class CommentController {
	@Autowired
	private CommentService cservice;
	
	//추가
	@PostMapping("commentInsert")
	public String  commentInsert(@RequestBody CommentDTO comment,
			HttpSession session) {
		String id =((MemberDTO)session.getAttribute("sMember")).getId();
		comment.setUserid(id);
		cservice.insert(comment);
		return "success";
	}
	@GetMapping("commentList/{bnum}")
	public List<CommentDTO> commentList(@PathVariable int bnum){
		return cservice.getList(bnum);
	}

}
