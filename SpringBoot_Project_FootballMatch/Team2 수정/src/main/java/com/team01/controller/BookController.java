package com.team01.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team01.config.auth.PrincipalUser;
import com.team01.model.Book;
import com.team01.model.Field;
import com.team01.model.SessionUser;
import com.team01.model.Team;
import com.team01.model.User;
import com.team01.service.BookService;
import com.team01.service.FieldService;
import com.team01.service.TeamService;
import com.team01.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/book/*")
public class BookController {
	
	private final TeamService teamService;
	private final BookService bookService;
	private final FieldService fieldService;
	private final UserService userService;
	private final HttpSession httpSession;
	
	@CrossOrigin
	@ResponseBody
	@GetMapping("list/{fieldNum}/{bookDate}")
	public List<Integer> book_list(@PathVariable String fieldNum,
			@PathVariable String bookDate,
			Model model) {
		
		System.out.println(">>> Book_List");
		List<Book> bookList = bookService.bookListByFieldAndDate(Long.parseLong(fieldNum), bookDate);
		List<Integer> bookStart = new ArrayList<>();
		for(int i = 0; i < bookList.size(); i++)
			 bookStart.add(bookList.get(i).getBookStart());
		
		return bookStart;
	}
	

	@GetMapping("book_view/{bookNum}")
	public String book_view(@PathVariable Long bookNum, Model model, 
			@AuthenticationPrincipal PrincipalUser principal) {
		
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
		
		Long team_id = userService.user_view(sessionUser.getEmail()).get().getTeam().getTeamId();
		model.addAttribute("team", teamService.team_view(team_id));
		model.addAttribute("book", bookService.book_view(bookNum));
		return "/field/book_view";
	}

	@PostMapping("book_insert/{fieldNum}")
	@PreAuthorize("isAuthenticated()")
	public String book_insert(@PathVariable Long fieldNum, Book book,
			@AuthenticationPrincipal PrincipalUser principal) {
		
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
		
		Field field = fieldService.field_view(fieldNum);
		
		book.setField(field);
		book.setTeam(userService.user_view(sessionUser.getEmail()).get().getTeam());
		
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
