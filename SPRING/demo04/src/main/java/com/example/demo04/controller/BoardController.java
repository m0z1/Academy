package com.example.demo04.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo04.config.auth.PrincipalUser;
import com.example.demo04.model.Board;
import com.example.demo04.service.BoardService;

import lombok.RequiredArgsConstructor;


@RequestMapping("/board/*")
@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	@GetMapping("insert")
	@PreAuthorize("isAuthenticated()")
	public String insert() {
		return "board/insert";
	}
	
	@PostMapping("insert")
	public String insert(Board board,
			@AuthenticationPrincipal	PrincipalUser principal) {
		boardService.insert(board, principal.getUser());
		return "redirect:list";
	}
	//전체보기
//	@GetMapping("list")
//	public String list(Model model) {
//		model.addAttribute("count", boardService.getCount());
//		model.addAttribute("boards", boardService.list());
//		return "board/list";
//	}
	
	//전체보기(페이징) ===>페이징정보 +data(Board 정보)
//	@GetMapping("list")
//	public String list(Model model,
//			@PageableDefault(size = 5, sort = "num",direction = Direction.DESC) Pageable pageable) {
//		 Page<Board> lists =  boardService.findAll(pageable);
//		 model.addAttribute("count", boardService.getCount());
//		 model.addAttribute("boards", lists);
//		return "board/list";
//	}
	
	//전체보기(페이징+검색) ===>페이징정보 +data(Board 정보)
	@GetMapping("list")
	public String liset(Model model, 
			@PageableDefault(size=5, sort="num", 
			direction = Direction.DESC)Pageable pageable,
			@RequestParam(required = false, defaultValue = "")String field,
			@RequestParam(required = false, defaultValue = "")String word) {
		Page<Board>  lists=boardService.findAll(field, word,pageable);
		Long count = boardService.count(field, word);
		model.addAttribute("boards", lists);
		model.addAttribute("count", count);
		return "board/list";
	}
	
	@GetMapping("view/{num}")
   public String view(@PathVariable long num, Model model) {
		model.addAttribute("board", boardService.view(num));
		return "board/view";
		
	}
	//삭제
	@DeleteMapping("delete/{num}")
	@ResponseBody
	public String delete(@PathVariable long num) {
		boardService.delete(num);
		return "success";
	}
	//수정폼
	@GetMapping("update/{num}")
	 public String update(@PathVariable long num, Model model) {
		model.addAttribute("board", boardService.view(num));
		return "board/update";
	}
	//수정
	@PutMapping("update")
	@ResponseBody
	public String update(@RequestBody Board board) {
		boardService.update(board);
		return "success";
	}

}


