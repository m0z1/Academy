package com.example.androidphone.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.androidphone.model.Comment;
import com.example.androidphone.model.FindBoard;
import com.example.androidphone.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment/*")
public class CommentController {
	
	private final CommentService commentService;
	
	//공통//
	//상세보기
	@PostMapping("/view/{commentId}")
	public Comment view(@PathVariable Long commentId) {
		return commentService.findById(commentId);
	}
	
//수정
	@PostMapping("/update")
	public Comment update(@RequestBody Comment comment) {
		return commentService.update(comment);
	}
	
//삭제
	@DeleteMapping("/delete/{commentId}")
	public void delete(@PathVariable Long commentId) {
		commentService.deleteById(commentId);
	}
	
	
	
	
	//목격 게시판(Find Board)//
	//전체보기
	@GetMapping("/list/{find_id}")
	public List<Comment> list(@PathVariable Long find_id){
		return commentService.list(find_id);
	}
	
	//추가
	@PostMapping("/insert/{find_id}")
	public Comment insert(@RequestBody Comment comment,@PathVariable Long find_id) {
		System.out.println("findId :"+ find_id);

		return commentService.insert(comment,find_id);
	}
	
	//갯수
		@GetMapping("/count/{find_id}")
		public Integer count(@PathVariable Long find_id) {
			return commentService.countAll(find_id);
		}
	
		
		
		
		//실종 게시판(MissingBoard)//
		//전체보기
		@GetMapping("/list2/{missing_id}")
		public List<Comment> list2(@PathVariable Long missing_id){
			return commentService.list2(missing_id);
		}
		
		//추가
		@PostMapping("/insert2/{missing_id}")
		public Comment insert2(@RequestBody Comment comment,@PathVariable Long missing_id) {

			return commentService.insert2(comment, missing_id);
		}
		
		//갯수
			@GetMapping("/count2/{missing_id}")
			public Integer count2(@PathVariable Long missing_id) {
				return commentService.countAll2(missing_id);
			}
			
			
			
			//스토리 게시판(StoryBoard)//
			//전체보기
			@GetMapping("/list3/{story_id}")
			public List<Comment> list3(@PathVariable Long story_id){
				return commentService.list3(story_id);
			}
			
			//추가
			@PostMapping("/insert3/{story_id}")
			public Comment insert3(@RequestBody Comment comment,@PathVariable Long story_id) {

				return commentService.insert3(comment, story_id);
			}
			
			//갯수
				@GetMapping("/count3/{story_id}")
				public Integer count3(@PathVariable Long story_id) {
					return commentService.countAll3(story_id);
				}
		
	
	
	

}
