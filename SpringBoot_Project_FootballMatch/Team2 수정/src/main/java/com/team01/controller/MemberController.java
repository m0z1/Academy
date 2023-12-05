package com.team01.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.ResolvableType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
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
import com.team01.model.Role;
import com.team01.model.SessionUser;
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
@Transactional
@RequestMapping("/member/*")
public class MemberController {

	private final UserRepository userRepository;
	private final UserService userService;
	private final FieldService fieldService;
	private final MatchService matchService;
	private final WaitService waitService;
	private final HttpSession httpSession;

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
		if (userService.user_view(user.getEmail()) != null) {
			return "fail";
		}
		userService.join(user);
		return "success";
	}

	@GetMapping("login")
	public String login() {
		
		System.out.println("memlogin");
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
	
	@GetMapping("member_update/{userId}")
	public String member_update(@PathVariable Long userId, Model model) {
		model.addAttribute("member", userService.member_view(userId));
		return "/member/member_update";
	}

	@PutMapping("member_update")
	@ResponseBody
	@Transactional
	public String member_update(@RequestBody User user) {
		User u = userService.member_view(user.getUserId());
		if(user.getRole()==Role.ROLE_USER)
			u.setRole(Role.ROLE_USER);
		else
			u.setRole(Role.ROLE_MANAGER);
		u.setName(user.getName());
		u.setPassword(user.getPassword());
		u.setUsername(user.getUsername());
		u.setTel(user.getTel());
		// userService.member_update(user);
		return "redirect:/member/memberlist";
	}
	
	private static final String authorizationRequestBaseUri = "oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();
    private final ClientRegistrationRepository clientRegistrationRepository;
    // Lombok 아닌 경우 (@RequiredArgsConstructor 없는 경우)
    // @Autowired private ClientRegistrationRepository clientRegistrationRepository;
    @SuppressWarnings("unchecked")
    @GetMapping("/login222")
    public String getLoginPage(Model model) throws Exception {
        Iterable<ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
                .as(Iterable.class);
        if (type != ResolvableType.NONE &&
                ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }
        assert clientRegistrations != null;
        clientRegistrations.forEach(registration ->
                oauth2AuthenticationUrls.put(registration.getClientName(),
                        authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
        System.out.println(oauth2AuthenticationUrls);
        model.addAttribute("urls", oauth2AuthenticationUrls);
 
        return "/login";
    }
    
    @GetMapping("/login/{oauth2}")
    public String loginGoogle(@PathVariable String oauth2
    		, HttpServletResponse httpServletResponse
    		, Model model) {
    	
    	System.out.println("================");
    	System.out.println("  " + oauth2 + " login  ");
    	System.out.println("================");
    	
    	return "redirect:/oauth2/authorization/" + oauth2;
    }
	
	

	@GetMapping("user_view/{username}")
	public String user_view(Model model) {
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
		User user = userService.user_view(sessionUser.getEmail()).get(); 
		model.addAttribute("user", user);
		return "/member/user_view";
	}

	@GetMapping("manager")
	public String manager(Model model, @AuthenticationPrincipal PrincipalUser principal) {
		
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
		User user = userService.user_view(sessionUser.getEmail()).get(); 

		model.addAttribute("user", user);
		model.addAttribute("fieldList", fieldService.field_list_num(user.getUserId()));

		return "/member/manager";
	}

	@GetMapping("user_info")
	public String user_info(Model model) {
		
		SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
		User user = userService.user_view(sessionUser.getEmail()).get();
		
		if(user.getTeam() != null) {
		Long TeamId = user.getTeam().getTeamId();
		List<Match> matchlist = new ArrayList<>();
		List<Book> list = userService.booklist(TeamId);
		List<Wait> wlist = waitService.waitlist(TeamId);
		System.out.println("wait : "+wlist.size());
		for(int i=0; i < wlist.size(); i++) {
			matchlist.add(matchService.view(wlist.get(i).getMatchNum()));
		}
		List<Match> mlist = matchService.match_list(user.getUserId());
		model.addAttribute("booklists", list);
		model.addAttribute("matchlists", mlist);
		model.addAttribute("user", user);
		model.addAttribute("waitlists", wlist);
		model.addAttribute("mlists", matchlist);
		} else {
			model.addAttribute("user", user);
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
