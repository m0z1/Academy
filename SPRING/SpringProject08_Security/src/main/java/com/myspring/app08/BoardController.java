package com.myspring.app08;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myspring.dto.BoardDTO;
import com.myspring.service.BoardService;

@RequestMapping("/board/*")
@Controller
public class BoardController {
	@Autowired
	private BoardService bservice;
	
	@GetMapping("insert")
	@PreAuthorize("isAuthenticated()")
	public void insert() {}
	
	@PostMapping("insert")
	public String insert(BoardDTO board) {
		bservice.insert(board);
		return "redirect:list";
		
	}
	@GetMapping("list")
	public String list(Model model) {
	  List<BoardDTO>	 arr = bservice.list();
	  model.addAttribute("boards", arr);
		return "board/list";
	}
	@GetMapping("view/{num}")
	public String view(@PathVariable int num,  Model model) {
      BoardDTO board = bservice.view(num);
	  model.addAttribute("board", board);
		return "board/view";
	}
	

}
