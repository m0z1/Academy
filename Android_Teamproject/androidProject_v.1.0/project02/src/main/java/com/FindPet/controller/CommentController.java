package com.FindPet.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FindPet.model.Comment;
import com.FindPet.model.Member;
import com.FindPet.service.CommentService;
import com.FindPet.service.FindBoardService;
import com.FindPet.service.MemberService;
import com.FindPet.service.MissingBoardService;
import com.FindPet.service.StoryBoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment/*")
public class CommentController {

	private final CommentService commentService;
	private final FindBoardService findboardService;
	private final MemberService memberService;
	private final MissingBoardService missingBoardService;
	private final StoryBoardService storyBoardService;

	// 공통//
	// 상세보기
	@PostMapping("/view/{commentId}")
	public Comment view(@PathVariable Long commentId) {
		return commentService.findById(commentId);
	}

	// 수정
	@PostMapping("/update")
	public Comment update(@RequestBody Comment comment) {
		return commentService.update(comment);
	}

	// 삭제
	@DeleteMapping("/delete/{commentId}")
	public void delete(@PathVariable Long commentId) {
		commentService.deleteById(commentId);
	}

	// 목격 게시판(Find Board)//
	// 전체보기
	@GetMapping("/list/{find_id}")
	public List<Comment> list(@PathVariable Long find_id) {
		return commentService.list(find_id);
	}

	// 추가
	@PostMapping("/insert/{find_id}")
	public Comment insert(@RequestBody Comment comment, @PathVariable Long find_id, String username) {
		System.out.println("findId :" + find_id);
		comment.setFindboard(findboardService.view(find_id));
		Member member = memberService.findmember(username);
		comment.setMember(member);
		return commentService.insert(comment, find_id);
	}

	// 갯수
	@GetMapping("/count/{find_id}")
	public Integer count(@PathVariable Long find_id) {
		return commentService.countAll(find_id);
	}

	// 실종 게시판(MissingBoard)//
	// 전체보기
	@GetMapping("/list2/{missing_id}")
	public List<Comment> list2(@PathVariable Long missing_id) {
		return commentService.list2(missing_id);
	}

	// 추가
	@PostMapping("/insert2/{missing_id}")
	public Comment insert2(@RequestBody Comment comment, @PathVariable Long missing_id, String username) {
		comment.setMissingboard(missingBoardService.view(missing_id));
		Member member = memberService.findmember(username);
		comment.setMember(member);
		return commentService.insert2(comment, missing_id);
	}

	// 갯수
	@GetMapping("/count2/{missing_id}")
	public Integer count2(@PathVariable Long missing_id) {
		return commentService.countAll2(missing_id);
	}

	// 스토리 게시판(StoryBoard)//
	// 전체보기
	@GetMapping("/list3/{story_id}")
	public List<Comment> list3(@PathVariable Long story_id) {
		return commentService.list3(story_id);
	}

	// 추가
	@PostMapping("/insert3/{story_id}")
	public Comment insert3(@RequestBody Comment comment, @PathVariable Long story_id, String username) {
		comment.setStoryboard(storyBoardService.view(story_id));
		Member member = memberService.findmember(username);
		comment.setMember(member);
		return commentService.insert3(comment, story_id);
	}

	// 갯수
	@GetMapping("/count3/{story_id}")
	public Integer count3(@PathVariable Long story_id) {
		return commentService.countAll3(story_id);
	}
}
