package com.team01.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.team01.model.Match;


public interface MatchRepositorty extends JpaRepository<Match, Long>{
	
	Optional<Match> findByBook_BookNum(Long matchNum);

	List<Match> findByUser_UserId(Long userId);
	
	Page<Match> findAll(Pageable pageable);
}
