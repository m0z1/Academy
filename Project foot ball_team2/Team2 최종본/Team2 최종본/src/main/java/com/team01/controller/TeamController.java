package com.team01.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team01.config.auth.PrincipalUser;
import com.team01.model.Board;
import com.team01.model.Comment;
import com.team01.model.Team;
import com.team01.service.BoardService;
import com.team01.service.CommentService;
import com.team01.service.TeamService;
import com.team01.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TeamController {

	private final TeamService teamService;
	private final CommentService commentService;
	private final BoardService boardService;
	private final UserService userService;

	@GetMapping("/team/team_insert")
	public String team_insert() {
		return "/team/team_insert";
	}

	@PostMapping("/team/team_insert")
	@ResponseBody
	public String team_insert(@RequestBody Team team, @AuthenticationPrincipal PrincipalUser principal) {
		
		System.out.println(principal.getUsername());
		team.setTeamManager(principal.getUser().getUserId());
		teamService.team_insert(team);
		
		return "success";
	}

	@GetMapping("/team/team_list")
	public String team_list(Model model, @PageableDefault(size = 5) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field,
			@RequestParam(required = false, defaultValue = "") String word) {
		Page<Team> teams = teamService.team_list(pageable, field, word);
		model.addAttribute("teams", teams);
		
		return "/team/team_list";
	}

	@GetMapping("/team/team_view/{teamId}")
	public String team_view(@PathVariable Long teamId, Model model, @AuthenticationPrincipal PrincipalUser puser, Comment comment) {
		
		List<Comment> clist = commentService.team_commentList(teamId);
		model.addAttribute("team", teamService.team_view(teamId));
		model.addAttribute("clist", clist);
		System.out.println("clist : " + clist);
		return "/team/team_view";
	}
	
	@DeleteMapping("/team/delete/{teamId}")
	@ResponseBody
	public String team_delete(@PathVariable Long teamId) {
		teamService.team_delete(teamId);
		return "success";
	}
	
	
	
	@PostMapping("/team/reply/insert/{teamId}")
	public ResponseEntity<String> insert(@PathVariable Long teamId, @RequestBody Comment comment) {
		PrincipalUser p = (PrincipalUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		comment.setTeam(p.getUser().getTeam());
		comment.setUser(p.getUser());
		commentService.team_insert(comment);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@GetMapping("/team/commentdelete/{commentNum}")
	public String comment_delete(@PathVariable Long commentNum) {
		commentService.comment_delete(commentNum);
		return "/";
	}
	
	@GetMapping("/team/teamAd_insert")
	@PreAuthorize("isAuthenticated()")
	public String teamAd_insert() {
		return "/board/teamAd_insert";
	}
	
	@PostMapping("/team/teamAd_insert")
	@ResponseBody
	public String teamAd_insert(@RequestBody Board board, @AuthenticationPrincipal PrincipalUser puser) {
		board.setBoardWhere("team");
		boardService.teamAd_insert(board, puser.getUser());
		return "success";
	}
	
	@GetMapping("/team/team_join/{teamId}") // 팀 가입
	public String team_join(@PathVariable Long teamId, @AuthenticationPrincipal PrincipalUser puser) { //
		userService.teamjoin(puser.getUser(), teamService.team_view(teamId));
		return "redirect:/";
	}
	
	
	
	@GetMapping("/team/team_out/{teamId}")
	public String team_out(@PathVariable Long teamId, @AuthenticationPrincipal PrincipalUser puser) {
		userService.teamout(puser.getUser(), teamService.team_view(teamId));
		return "redirect:/";
	}
	
}
