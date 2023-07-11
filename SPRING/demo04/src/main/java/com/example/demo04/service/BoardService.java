package com.example.demo04.service;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo04.model.Board;
import com.example.demo04.model.User;
import com.example.demo04.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

//@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	
	@Transactional
	public void insert(Board board,User user) {
		board.setUser(user);
		boardRepository.save(board);
	}
	//전체보기
	public List<Board> list(){
		return boardRepository.findAll();
	}
	//전체보기 (페이징)
	public Page<Board> findAll(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	//전체보기(페이징+검색)
	public  Page<Board> findAll(String field, String word, Pageable pageable){
		Page<Board> lists = boardRepository.findAll(pageable);
		if(field.equals("title")) {
			lists  = boardRepository.findByTitleContaining(word, pageable);
		}else if(field.equals("content")) {
			lists  = boardRepository.findByContentContaining(word, pageable);
		}
		return lists;
	}
	//개수(검색)
	public Long count(String field, String word) {
		Long count = boardRepository.count();
		if(field.equals("title")) {
			count = boardRepository.cntTitleSearch(word);
		}else if(field.equals("content")) {
			count = boardRepository.cntContnetSearch(word);
		}
		return count;
	}
	
	
	//개수
	public long getCount() {
		return boardRepository.count();
	}
	//상세보기
	@Transactional
	public Board view(long num) {
		Board board = boardRepository.findById(num).get();
		//조회수 증가
		board.setHitcount(board.getHitcount()+1);
		
		return board;
	}
	
	//삭제
	public void delete(long num) {
		boardRepository.deleteById(num);
	}
	//수정 ==>더티체킹
	@Transactional
	public void update(Board board) {
		Board b = boardRepository.findById(board.getNum()).get();
		b.setTitle(board.getTitle());
		b.setContent(board.getContent());
		b.setRegdate(new Date());
		
	}
	

}
