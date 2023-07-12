package com.example.demo05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo05.dto.BoardDTO;
import com.example.demo05.dto.PageVO;
import com.example.demo05.service.BoardServcie;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	private final BoardServcie boardServcie;
	//전체보기
	 @GetMapping({"/", "list"})
	  public String list(Model model,String pageNum) {
		 int currentPage = pageNum ==null ? 1 : Integer.parseInt(pageNum);
		 int pageSize=3;
		 int pageStart = (currentPage-1) *pageSize;
		 int count= boardServcie.getCount();
		 PageVO page = new PageVO(count,pageSize,currentPage);
		 
		//  model.addAttribute("boards", boardServcie.list());
		 model.addAttribute("count", count);
		 model.addAttribute("p", page);
		 model.addAttribute("boards", boardServcie.pagelist(pageStart, pageSize));
		  return "board/list";
	  }
//  @GetMapping("/")
//  public String home() {
//	  return "home";
//  }
  //board 추가 폼
  @GetMapping("insert")
  public String insert() {
	  return "board/insert";
  }
  //board 추가
  @PostMapping("insert")
  public String insert(BoardDTO board) {
	  boardServcie.insert(board);
	  return "redirect:list";
  }
  

  //상세보기
  @GetMapping("view/{num}")
  public String view(@PathVariable int num, Model model) {
	  model.addAttribute("board", boardServcie.view(num));
	  return "board/view";
  }
}






