package com.team01.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.team01.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

	

	Page<Board> findByBoardTitleContaining(String word, Pageable pageable);


	/*
	 * @Query(value = "select * from project_board where board_category = '팀 홍보'",
	 * nativeQuery = true) Page<Board> findAllByBoardCategory(Pageable pageable,
	 * Board board);
	 * 
	 * @Query(value = "select * from project_board where board_category = '공지사항'",
	 * nativeQuery = true) Page<Board> findAllByBoardCategory1(Pageable pageable);
	 */

	Page<Board> findAllByBoardCategory(Pageable pageable, String boardCategory);

	Page<Board> findByUser_UsernameContaining(String word, Pageable pageable);

	Page<Board> findByBoardWhere(Pageable pageable, String string);

	Page<Board> findByBoardWhereAndUser_UsernameContaining(String BoardWhere, String word, Pageable pageable);

	Page<Board> findByBoardWhereAndBoardTitleContaining(String BoardWhere, String word, Pageable pageable);

	Page<Board> findByUser_Team_TeamNameContaining(String word, Pageable pageable);


	Page<Board> findByBoardWhereAndUser_Team_TeamNameContaining(String string, String word, Pageable pageable);

	
	

	
}
