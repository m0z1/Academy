package com.team01.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team01.config.auth.PrincipalUser;
import com.team01.model.Board;
import com.team01.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class boardController {

	private final BoardService boardService;

	@GetMapping("about")
	public String about() {
		return "/board/about";
	}

	@GetMapping("guest")
	public String guest( Model model, @PageableDefault(size = 5) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String word, Board board) {
		Page<Board> Adlist = boardService.guestAd_list(pageable, field, word, board);
		model.addAttribute("ad", Adlist);
		return "/board/guest";
	}
	
	@GetMapping("guestAd_insert")
	@PreAuthorize("isAuthenticated()")
	public String guestboard() {
		return "/board/guestAd_insert";
	}

	
	@PostMapping("guestAd_insert")
	@ResponseBody
	public String guestAd_insert(@RequestBody Board board, @AuthenticationPrincipal PrincipalUser puser) {
		board.setBoardWhere("guest");
		boardService.teamAd_insert(board, puser.getUser());
		return "success";
	}
	
	@GetMapping("guestCheck/{boardNum}")
	public String guestCheck(@PathVariable Long boardNum, Model model, @PageableDefault(size = 5) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String word, Board board) {
		Page<Board> Adlist = boardService.guestAd_list(pageable, field, word, board);
		model.addAttribute("ad", Adlist);
		Board board1 = boardService.member_view(boardNum);
		model.addAttribute("users",board1);
		return "/board/guestCheck";
	}

	@GetMapping("notice")
	public String notice(Model model, Pageable pageable) {
		Page<Board> noticelist = boardService.notice_list(pageable);
		model.addAttribute("notice", noticelist);
		return "/board/notice";
	}

	@GetMapping("notice_insert")
	public String notice_insert() {
		return "/board/notice_insert";
	}

	@PostMapping("notice_insert")
	@ResponseBody
	public String notice_insert(@RequestBody Board board, @AuthenticationPrincipal PrincipalUser puser) {
		board.setBoardWhere("notice");
		boardService.notice_insert(board, puser.getUser());
		return "success";
	}

	@GetMapping("team")
	public String login(Model model, @PageableDefault(size = 5) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String word, Board board) {
		Page<Board> Adlist = boardService.teamAd_list(pageable, field, word);
		model.addAttribute("ad", Adlist);
		return "/board/team";
	}

}
