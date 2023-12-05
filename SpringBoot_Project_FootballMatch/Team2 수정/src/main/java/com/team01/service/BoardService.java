package com.team01.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team01.model.Board;
import com.team01.model.User;
import com.team01.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	
	@Transactional
	public void teamAd_insert(Board board,User user) {
		board.setUser(user);
		boardRepository.save(board);
	}

	/*
	 * public Page<Board> Adlist(Pageable pageable, String field, String word, Board
	 * board) { Page<Board> lists = boardRepository.findAllByBoardCategory(pageable,
	 * board); if (field.equals("team_name")) { lists =
	 * boardRepository.findByUser_Team_teamNameContaining(word, pageable); } else if
	 * (field.equals("title")) { lists =
	 * boardRepository.findByBoardTitleContaining(word, pageable); } return lists; }
	 */
	
	public Page<Board> teamAd_list(Pageable pageable, String field, String word) {
		
		
		Page<Board> lists = boardRepository.findAllByBoardCategory(pageable, "팀 홍보");
		
		if (field.equals("team_name")) {
			lists = boardRepository.findByBoardWhereAndUser_Team_TeamNameContaining("team", word, pageable);
		} else if (field.equals("title")) {
			lists = boardRepository.findByBoardWhereAndBoardTitleContaining("team", word, pageable);
		}
		return lists;
	}

	public Page<Board> notice_list(Pageable pageable) {
		Page<Board> lists = boardRepository.findByBoardWhere(pageable, "notice");
		return lists;
	}
	
	@Transactional
	public void notice_insert(Board board, User user) {
		board.setUser(user);
		boardRepository.save(board);
		
	}
	
	public Page<Board> guestAd_list(Pageable pageable, String field, String word, Board board) {
		
		Page<Board> lists = boardRepository.findByBoardWhere(pageable, "guest");
		
		if (field.equals("username")) {
			lists = boardRepository.findByBoardWhereAndUser_UsernameContaining("guest", word, pageable);
		} else if (field.equals("title")) {
			lists = boardRepository.findByBoardWhereAndBoardTitleContaining("guset", word, pageable);
		}
		return lists;
	}

	public Board member_view(Long boardNum) {
		
		return boardRepository.findById(boardNum).get(); 
	}

	
}
