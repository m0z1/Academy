package com.myspring.app07;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myboard.dto.CommentDTO;
import com.myboard.dto.MemberDTO;
import com.myboard.model.CommentService;
@RequestMapping("/reply/*")
 @RestController //@Controller +@ResponseBody
public class CommentController {
	@Autowired
	private CommentService cservice;
	
	//추가
	@PostMapping("commentInsert")
	//@ResponseBody
	public String insert(@RequestBody CommentDTO comment,
			HttpSession session) {
		//MemberDTO member = (MemberDTO)	session.getAttribute("sMember");
		//comment.setUserid(member.getId());
		String id = ((MemberDTO)session.getAttribute("sMember")).getId();
		comment.setUserid(id);
		cservice.insert(comment);
		return "success";
	}
	//전체보기
	@GetMapping("commentList/{num}")
	//@ResponseBody
	public List<CommentDTO> getList(@PathVariable int num) {
		return cservice.getList(num);
	}
	
	//삭제
	@DeleteMapping("delete/{cnum}")
	//@ResponseBody
    public int delete(@PathVariable int cnum) {
		cservice.delete(cnum);
		return cnum;
	}
}


