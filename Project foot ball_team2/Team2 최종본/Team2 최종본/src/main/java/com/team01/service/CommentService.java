package com.team01.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.team01.model.Comment;
import com.team01.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	
	public void team_insert(Comment comment) {
		commentRepository.save(comment);
	}

	public List<Comment> team_commentList(Long teamId) {
		return commentRepository.findByTeam_TeamId(teamId);
	}

	public void comment_delete(Long commentNum) {
		commentRepository.deleteById(commentNum);
		
	}

	
}
