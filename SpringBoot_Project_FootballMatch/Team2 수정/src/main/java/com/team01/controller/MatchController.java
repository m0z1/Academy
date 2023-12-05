package com.team01.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team01.config.auth.PrincipalUser;
import com.team01.model.Book;
import com.team01.model.Field;
import com.team01.model.Match;
import com.team01.model.SessionUser;
import com.team01.model.Team;
import com.team01.model.User;
import com.team01.model.Wait;
import com.team01.service.BookService;
import com.team01.service.FieldService;
import com.team01.service.MatchService;
import com.team01.service.TeamService;
import com.team01.service.UserService;
import com.team01.service.WaitService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/match/*")
public class MatchController {

	private final MatchService matchService;
	private final FieldService fieldService;
	private final BookService bookService;
	private final TeamService teamService;
	private final WaitService waitService;
	private final UserService userService;
	private final HttpSession httpSession;

	/* -----------------------match --------------------------- */
	/*
	 * @GetMapping("match_list/{fieldNum}") public String match_list(@PathVariable
	 * Long fieldNum, Model model) { List<Match> lists = matchService.list();
	 * List<Field> fields = fieldService.list(); model.addAttribute("field",
	 * fieldService.view(fieldNum)); model.addAttribute("match", lists);
	 * model.addAttribute("fields", fields); return "/match/match_list"; }
	 */

	
	@GetMapping("match_list")
	   public String match_list(Model model, @PageableDefault(size = 10) Pageable pageable) {
	      Page<Match> matchList = matchService.pagelist(pageable);
	      
	      model.addAttribute("matchList", matchList);

	      return "/match/match_list";
	   }

	
	@GetMapping("match_insert/{bookNum}")
	public String match_insert(@PathVariable Long bookNum, Model model) {
		List<Book> books = bookService.list();
		Book book1 = bookService.book_view(bookNum);
		model.addAttribute("book1", book1);
		model.addAttribute("book", books);
		model.addAttribute("field", book1.getField());

		return "/match/match_insert";
	}

	
	@PostMapping("match_insert/{bookNum}")
	public String match_insert(@PathVariable Long bookNum, 
			Book book, Model model, Match match) {
		
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
		
		User user = userService.user_view(sessionUser.getEmail()).get();
		String team_name = user.getTeam().getTeamName();
		match.setMatchHome1(team_name);
		match.setBook(bookService.book_view(bookNum));
		match.setUser(user);
		matchService.insert(match);

		return "redirect:/match/match_list";
	}

	
	@GetMapping("match_view/{bookNum}")
	public String match_view(@PathVariable Long bookNum, Model model,
			@AuthenticationPrincipal PrincipalUser principal) {
		
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
		User user = userService.user_view(sessionUser.getEmail()).get(); 
		Match match = matchService.view_booknum(bookNum);
		
		model.addAttribute("match", match);
		model.addAttribute("view", bookService.book_view(bookNum));
		model.addAttribute("user", user);
		return "/match/match_view";

	}

	
	@GetMapping("match_apply/{matchNum}") // flag 값 가져오기 if 신청버튼 => flag +1
	public String match_apply(@PathVariable Long matchNum, Model model) {
		Wait wait = new Wait();
		Match match = matchService.view(matchNum);
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
		
		User user = userService.user_view(sessionUser.getEmail()).get();
		String team_name = user.getTeam().getTeamName();
		match.setFlag(1);
		wait.setTeamId(user.getTeam().getTeamId());
		wait.setMatchNum(matchNum);
		waitService.insert(wait);

		if (match.getFlag() == 2) {
			if (match.getMatchHome1() != null) {
				if (match.getMatchAway1() != null) {
					match.setMatchAway2(team_name);
				} else {
					match.setMatchAway1(team_name);
				}
			}
		}

		matchService.update(match);

		return "redirect:/match/match_list";
	}

	
	@GetMapping("match_apply2/{matchNum}") // flag 값 가져오기 if 신청버튼 => flag +1
	public String match_apply2(@PathVariable Long matchNum, Model model) {
		Match match = matchService.view(matchNum);
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
		
		User user = userService.user_view(sessionUser.getEmail()).get();
		String team_name = user.getTeam().getTeamName();
		match.setFlag(2);
		if (match.getFlag() == 2) {
			if (match.getMatchHome1() != null) {
				if (match.getMatchAway1() != null) {
					match.setMatchAway2(team_name);
				} else {
					match.setMatchAway1(team_name);

				}
			}
		}

		matchService.update(match);

		return "redirect:/match/match_list";
	}

	/* -----------------------match --------------------------- */
}
