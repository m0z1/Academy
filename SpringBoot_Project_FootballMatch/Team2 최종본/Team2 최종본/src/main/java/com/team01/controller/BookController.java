package com.team01.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team01.config.auth.PrincipalUser;
import com.team01.model.Book;
import com.team01.model.Field;
import com.team01.service.BookService;
import com.team01.service.FieldService;
import com.team01.service.TeamService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/book/*")
public class BookController {
	
	private final TeamService teamService;
	private final BookService bookService;
	private final FieldService fieldService;

	@ResponseBody
	@GetMapping("book_view/{bookNum}")
	public String book_view(@PathVariable Long bookNum, Model model, 
			@AuthenticationPrincipal PrincipalUser principal) {
		
	String message="";
		
		if(principal.getUser().getTeam() == null) {
			message = "<script>alert('팀 가입자 전용입니다 팀에 가입하세요.');location.href='/board/team';</script>";
			return message;
		}
		
		Long team_id = principal.getUser().getTeam().getTeamId();
		model.addAttribute("team", teamService.team_view(team_id));
		model.addAttribute("book", bookService.book_view(bookNum));
		return "/field/book_view";
	}

	@PostMapping("book_insert/{fieldNum}")
	@PreAuthorize("isAuthenticated()")
	public String book_insert(@PathVariable Long fieldNum, Book book,
			@AuthenticationPrincipal PrincipalUser principal) {
		
		System.out.println("1");
		Field field = fieldService.field_view(fieldNum);
		
		book.setField(field);
		book.setTeam(principal.getUser().getTeam());
		
		book.setBookEnd(book.getBookStart()+field.getFieldUnit());
		bookService.book_insert(book);

		return "redirect:/book/book_view/" + Long.toString(book.getBookNum());
	}
	
	@GetMapping("book_date/{bookDate}")
	@ResponseBody
	public String book_date(@PathVariable String bookDate, Model model) {
		
		System.out.println(bookDate);
		model.addAttribute("selectDate",bookDate);
		
		return "success";
	}
}
