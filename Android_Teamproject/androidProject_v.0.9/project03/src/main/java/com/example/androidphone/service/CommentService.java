package com.example.androidphone.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.androidphone.model.Comment;
import com.example.androidphone.model.FindBoard;
import com.example.androidphone.model.MissingBoard;
import com.example.androidphone.model.StoryBoard;
import com.example.androidphone.repository.CommentRepository;
import com.example.androidphone.repository.FindBoardRepository;
import com.example.androidphone.repository.MissingBoardRepository;
import com.example.androidphone.repository.StoryBoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	
	private final CommentRepository commentRepository;
	private final FindBoardRepository boardRepository;
	private final MissingBoardRepository missingBoardRepository;
	private final StoryBoardRepository storyBoardRepository;
	
	//공통//
	//상세보기
	public Comment findById(Long commentId) {
		return commentRepository.findById(commentId).get();
	}
	
	//수정
	@Transactional
	public Comment update(Comment comment) {
		Comment c = commentRepository.findById(comment.getCommentId()).get();
		c.setContent(comment.getContent());
		return c;
	}
	
	//삭제
	public void deleteById(Long commentId) {
		commentRepository.deleteById(commentId);
	}
	
	
	
	
	//목격 게시판//
	//전체보기
	public List<Comment> list(Long find_id){
		return commentRepository.findAllByFind_id(find_id);
	}
	
	//추가
	@Transactional
	public Comment insert(Comment comment, Long findId) {
		FindBoard  findBoard = boardRepository.findById(findId).get();
		comment.setFindboard(findBoard);
		
		return commentRepository.save(comment);
	}
	
	//갯수
	public Integer countAll(Long find_id) {
		return commentRepository.countAllByFind_id(find_id);
	}
	
	
	
	//실종 게시판//
	//전체보기
	public List<Comment> list2(Long missing_id){
		return commentRepository.findAllByMissing_id(missing_id);
	}
	
	//추가
	@Transactional
	public Comment insert2(Comment comment, Long missingId) {
		MissingBoard missingBoard = missingBoardRepository.findById(missingId).get();
		comment.setMissingboard(missingBoard);
		
		return commentRepository.save(comment);
	}
	
	//갯수
	public Integer countAll2(Long missing_id) {
		return commentRepository.countAllByMissing_id(missing_id);
	}
	
	
	
	//스토리 게시판//
		//전체보기
		public List<Comment> list3(Long story_id){
			return commentRepository.findAllByStory_id(story_id);
		}
		
		//추가
		@Transactional
		public Comment insert3(Comment comment, Long storyId) {
			StoryBoard sb = storyBoardRepository.findById(storyId).get();
			comment.setStoryboard(sb);
			
			return commentRepository.save(comment);
		}
		
		//갯수
		public Integer countAll3(Long story_id) {
			return commentRepository.countAllByStory_id(story_id);
		}
	
	


}
