package com.example.demo04.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo04.model.Board;
import com.example.demo04.model.Comment;
import com.example.demo04.repository.BoardRepository;
import com.example.demo04.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final BoardRepository boardRepository;
	
	//댓글 전체보기
	public List<Comment> list(Long bnum){
	  return	commentRepository.findByBnum(bnum);
	}
	//댓글추가
	@Transactional
	public void insert(Comment comment) {
		//commentRepository.save(comment);  	//댓글 추가
		
		//replycnt +1(update)
	 Board b=	boardRepository.findById(comment.getBoard().getNum()).get();
	 b.setReplycnt(b.getReplycnt()+1); //update
	 
	 //SQL
	 commentRepository.insert(comment.getContent(),
			 comment.getBoard().getNum(),
			 comment.getUser().getId());
		
	}
	//댓글삭제
	@Transactional 
	public void delete(Long cnum) {
		//댓글개수 -1
	 Optional<Comment> c = commentRepository.findById(cnum);
	 //board 에 있는 replycnt 값을 1 감소로 수정
	 Board b = c.get().getBoard();
	 b.setReplycnt(b.getReplycnt()-1);
	 
	 commentRepository.deleteById(cnum);
	}

}
