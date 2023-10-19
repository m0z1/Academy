package com.team01.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team01.config.auth.PrincipalUser;
import com.team01.model.Book;
import com.team01.model.Match;
import com.team01.model.Team;
import com.team01.model.User;
import com.team01.model.Wait;
import com.team01.repository.UserRepository;
import com.team01.service.FieldService;
import com.team01.service.MatchService;
import com.team01.service.UserService;
import com.team01.service.WaitService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/*")
public class MemberController {

	private final UserRepository userRepository;
	private final UserService userService;
	private final FieldService fieldService;
	private final MatchService matchService;
	private final WaitService waitService;

	@GetMapping("/join")
	public String join() {
		return "/member/join";
	}

	/*
	 * @PostMapping("/user/idcheck")
	 * 
	 * @ResponseBody public String idcheck(String username) { int result =
	 * userService.idCheck(username); if(result != 0) {
	 * 
	 * return "fail"; // 중복 아이디가 존재
	 * 
	 * } else {
	 * 
	 * return "success"; // 중복 아이디 x
	 * 
	 * } }
	 */

	@PostMapping("/join")
	@ResponseBody
	public String join(@RequestBody User user, Team team) {
		// username이 있으면 return fail
		if (userRepository.findByUsername(user.getUsername()) != null) {
			return "fail";
		}
		userService.join(user);
		return "success";
	}

	@GetMapping("login")
	public String login() {
		return "/member/login";
	}

	@GetMapping("memberlist")
	   public String member_list(Model model, @PageableDefault(size = 5) Pageable pageable) {
	      Page<User> ulist =  userService.member_list(pageable);
	      model.addAttribute("member", ulist);
	      return "/member/member_list";
	   }

	@GetMapping("member_view/{userId}")
	public String member_view(@PathVariable Long userId, Model model) {
		model.addAttribute("member", userService.member_view(userId));
		return "/member/member_view";
	}

	@PutMapping("member_update")
	@ResponseBody
	@Transactional
	public String member_update(@RequestBody User user) {
		User u = userService.member_view(user.getUserId());
		u.setAdmin(user.getAdmin());
		u.setName(user.getName());
		u.setPassword(user.getPassword());
		u.setUsername(user.getUsername());
		u.setTel(user.getTel());
		// userService.member_update(user);
		return "redirect:/member/memberlist";
	}

	@GetMapping("user_view/{username}")
	public String user_view(@AuthenticationPrincipal PrincipalUser puser, Model model) {
		String username = puser.getUsername();
		model.addAttribute("user", userService.user_view(username));
		return "/member/user_view";
	}

	@GetMapping("manager")
	public String manager(Model model, @AuthenticationPrincipal PrincipalUser principal) {

		model.addAttribute("user", principal.getUser());
		model.addAttribute("fieldList", fieldService.field_list_num(principal.getUser().getUserId()));

		return "/member/manager";
	}

	@GetMapping("user_info/{username}")
	public String user_info(@AuthenticationPrincipal PrincipalUser puser, Model model) {
		
		
		if(puser.getUser().getTeam() != null) {
		Long TeamId = puser.getUser().getTeam().getTeamId();
		List<Match> matchlist = new ArrayList<>();
		List<Book> list = userService.booklist(TeamId);
		List<Wait> wlist = waitService.waitlist(TeamId);
		System.out.println("wait : "+wlist.size());
		for(int i=0; i < wlist.size(); i++) {
			matchlist.add(matchService.view(wlist.get(i).getMatchNum()));
		}
		List<Match> mlist = matchService.match_list(puser.getUser().getUserId());
		model.addAttribute("booklists", list);
		model.addAttribute("matchlists", mlist);
		model.addAttribute("user", puser.getUser());
		model.addAttribute("waitlists", wlist);
		model.addAttribute("mlists", matchlist);
		} else {
			model.addAttribute("user", puser.getUser());
		}
		/*
		 * if (bookNum) { String username = puser.getUsername(); List<Match> lists =
		 * matchService.matchlist(bookNum); model.addAttribute("user",
		 * userService.user_view(username)); model.addAttribute("match", lists);
		 * System.out.println("lists : " + lists); } else if (bookNum == 0) { String
		 * username = puser.getUsername(); model.addAttribute("user",
		 * userService.user_view(username)); }
		 */

		return "/member/user_info";
	}
}
